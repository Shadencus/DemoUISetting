package de.hhn.ai.pmt.gruppeb.ui;

import de.hhn.ai.pmt.gruppeb.model.Recipe;
import de.hhn.ai.pmt.gruppeb.model.User;
import de.hhn.ai.pmt.gruppeb.model.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.orm.PersistentException;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

public class SettingsController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public static User testUser;


    @FXML
    public void initialize(){
        try {
            User[] users = UserDAO.listUserByQuery("email = 'ok@42.com'", "email");
            if(users.length == 0){
                testUser = UserDAO.createUser();
                testUser.setEmail("ok@42.com");
                testUser.setName("Behemoth");
                testUser.setIsAdmin(false);
                testUser.setPassword(BCrypt.hashpw("1234", BCrypt.gensalt()));
                testUser.setProfilPicture("https://pascalpex.ddns.net/img/logo.png");
                testUser.setTimestamp(Timestamp.from(Instant.now()));
                UserDAO.save(testUser);
            }else{
                testUser = users[0];
                System.out.println(testUser.getName());
                System.out.println(testUser.getProfilPicture());
                System.out.println(testUser.getPassword());
                System.out.println();
            }
        } catch (PersistentException e) {
            throw new RuntimeException(e);
        }
    }





    public void switchToPasswordDialog(ActionEvent event) throws IOException {
        root = FXMLLoader.load((HelloApplication.class.getResource("PasswordDialog.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 270, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToUsernameDialog(ActionEvent event) throws IOException {
        root = FXMLLoader.load((HelloApplication.class.getResource("UsernameDialog.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 270, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDeleteAccountDialog(ActionEvent event) throws IOException {
        root = FXMLLoader.load((HelloApplication.class.getResource("DeleteAccountDialog.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 270, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToProfilPictureDialog(ActionEvent event) throws IOException {
        root = FXMLLoader.load((HelloApplication.class.getResource("ProfilPictureDialog.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 270, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Label settingInfoLAbel;

    @FXML
    private VBox buttons;


    @FXML
    void onChangePasswordClick(ActionEvent event) {
        try {
            switchToPasswordDialog(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void onChangeProfilPictureClick(ActionEvent event) {
        try {

            switchToProfilPictureDialog(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onDeleteAccountClick(ActionEvent event) {
        try {
            switchToDeleteAccountDialog(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onChangeUsernameClick(ActionEvent event) {
        try {
            switchToUsernameDialog(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onLogOutClick(ActionEvent event) {
        settingInfoLAbel.setText("Logged out");
        buttons.setDisable(true);
    }
}