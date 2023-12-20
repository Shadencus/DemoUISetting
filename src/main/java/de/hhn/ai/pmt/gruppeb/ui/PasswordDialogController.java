package de.hhn.ai.pmt.gruppeb.ui;

import de.hhn.ai.pmt.gruppeb.model.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.orm.PersistentException;

import java.io.IOException;

public class PasswordDialogController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToSettings(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SettingsDialog.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,270,600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField newPassword;

    @FXML
    private TextField newPasswordCheck;

    @FXML
    private TextField oldPassword;

    @FXML
    private Label pwErrorLabel;


    @FXML
    void onPwCancelClick(ActionEvent event) {
        try {
            switchToSettings(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onPwOkClick(ActionEvent event) {
        String newPW = newPassword.getText();
        String oldPW = oldPassword.getText();
        String cNewPW = newPasswordCheck.getText();
        if(!oldPW.isBlank()){
            if (BCrypt.checkpw(oldPW, SettingsController.testUser.getPassword())){
               if (!newPW.isBlank() || !cNewPW.isBlank()){
                   if (newPW.equals(cNewPW)){
                       try {
                           SettingsController.testUser.setPassword(BCrypt.hashpw(newPW, BCrypt.gensalt()));
                           UserDAO.save(SettingsController.testUser);
                           switchToSettings(event);
                       } catch (PersistentException | IOException e) {
                           throw new RuntimeException(e);
                       }
                   }else{
                       pwErrorLabel.setText("The new password does not match the verification");
                   }
               }else{
                   pwErrorLabel.setText("");
                   newPassword.setPromptText("Input missing");
                   newPasswordCheck.setPromptText("Input missing");
                }
            }else{
                pwErrorLabel.setText("The password does not match with the current password");
            }
        }else {
           oldPassword.setPromptText("The old password input is missing");
        }
    }
}