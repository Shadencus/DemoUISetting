package de.hhn.ai.pmt.gruppeb.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    private Stage stage;
    private Scene scene;
    private Parent root;

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

    }
}