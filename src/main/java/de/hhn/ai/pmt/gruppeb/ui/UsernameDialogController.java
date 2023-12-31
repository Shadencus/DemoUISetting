package de.hhn.ai.pmt.gruppeb.ui;

import de.hhn.ai.pmt.gruppeb.model.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UsernameDialogController {
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
    private TextField newUsername;

    @FXML
    void onUsernameCancelClick(ActionEvent event) {
        try {
            switchToSettings(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onUsernameOkClick(ActionEvent event) {
        try {
            String usernameData = newUsername.getCharacters().toString();
            if(!usernameData.isBlank()){
                SettingsController.testUser.setName(usernameData);
                UserDAO.save(SettingsController.testUser);
                switchToSettings(event);
            }else{
                newUsername.setText("");
                newUsername.setPromptText("Please enter valid input");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
