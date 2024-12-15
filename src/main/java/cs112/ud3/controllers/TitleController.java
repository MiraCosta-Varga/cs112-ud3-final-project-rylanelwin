package cs112.ud3.controllers;

import cs112.ud3.MainApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class TitleController {
    public void initData(){
        Image monopolyMan = new Image(getClass().getResourceAsStream("/images/monopoly man.png"));
        titleImageView.setImage(monopolyMan);
    }
    @FXML
    private Label titleLabel;
    @FXML
    private ImageView titleImageView;
    @FXML
    private Button enterButton;
    @FXML
    private Button exitButton;
    @FXML
    protected void onEnterButtonClick(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("games-view.fxml"));
        Parent gameViewParent = loader.load();

        GamesController gamesController = loader.getController();
        gamesController.initData(1000);

        Scene gamesViewScene = new Scene(gameViewParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setTitle("Games");

        window.setScene(gamesViewScene);

        window.show();
    }
    @FXML
    protected void onExitButtonClick(){
        Platform.exit();
    }
}