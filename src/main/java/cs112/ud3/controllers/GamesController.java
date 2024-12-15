package cs112.ud3.controllers;

import cs112.ud3.MainApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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

import java.io.IOException;

public class GamesController {
    private double money;

    protected void initData(double money){
        this.money = money;
        gamesMoneyLabel.setText("Money: $" + money);
        if ((money * 100) % 10 == 0){
            gamesMoneyLabel.setText(gamesMoneyLabel.getText() + "0");
        }

        Image slots = new Image(getClass().getResourceAsStream("/images/slots.png"));
        Image rps = new Image(getClass().getResourceAsStream("/images/rps.png"));
        Image blackjack = new Image(getClass().getResourceAsStream("/images/blackjack.png"));

        gamesSlotsImageView.setImage(slots);
        gamesRpsImageView.setImage(rps);
        gamesBlackjackImageView.setImage(blackjack);

        gamesBlackjackPlayButton.setDisable(true);
    }

    private void changeWindow(String view, ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(view));
        Parent gameViewParent = loader.load();
        String stageName;

        if (view.equals("slots-view.fxml")){
            SlotsController slotsController = loader.getController();
            slotsController.initData(money);
            stageName = "Slots";
        } else if (view.equals("rps-view.fxml")){
            RpsController rpsController = loader.getController();
            rpsController.initData(money);
            stageName = "Rock Paper Scissors";
        } else {
            BlackjackController blackjackController = loader.getController();
            blackjackController.initData(money);
            stageName = "Blackjack";
        }

        Scene gamesViewScene = new Scene(gameViewParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setTitle(stageName);

        window.setScene(gamesViewScene);

        window.show();
    }
    @FXML
    private Label gamesMoneyLabel;
    @FXML
    private Label gamesSlotsLabel;
    @FXML
    private Label gamesRpsLabel;
    @FXML
    private Label gamesBlackjackLabel;
    @FXML
    private ImageView gamesSlotsImageView;
    @FXML
    private ImageView gamesRpsImageView;
    @FXML
    private ImageView gamesBlackjackImageView;
    @FXML
    private Button gamesSlotsPlayButton;
    @FXML
    private Button gamesRpsPlayButton;
    @FXML
    private Button gamesBlackjackPlayButton;
    @FXML
    private Button gamesExitButton;
    @FXML
    protected void onGamesSlotsPlayButtonClick(ActionEvent actionEvent) throws IOException{
        changeWindow("slots-view.fxml", actionEvent);
    }
    @FXML
    protected void onGamesRpsPlayButtonClick(ActionEvent actionEvent) throws IOException{
        changeWindow("rps-view.fxml", actionEvent);
    }
    @FXML
    protected void onGamesBlackjackPlayButtonClick(ActionEvent actionEvent) throws IOException{
        changeWindow("blackjack-view.fxml", actionEvent);
    }
    @FXML
    protected void onGamesExitButtonClick(){
        Platform.exit();
    }
}
