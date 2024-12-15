package cs112.ud3.controllers;

import cs112.ud3.MainApplication;
import cs112.ud3.models.Slots;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SlotsController {
    private double money;
    private boolean bankrupt = false;
    Image[] images;

    protected void initData(double money){

        this.money = money;
        slotsMoneyLabel.setText("Money: $" + money);

        slotsWinLabel.setText("Bet: $" + slots.getBet());
        if ((slots.getBet() * 100) % 10 == 0){
            slotsWinLabel.setText(slotsWinLabel.getText() + "0");
        }

        images = new Image[]{
                new Image (getClass().getResourceAsStream("/images/cherry.png")),
                new Image(getClass().getResourceAsStream("/images/grape.png")),
                new Image(getClass().getResourceAsStream("/images/diamond.png")),
                new Image(getClass().getResourceAsStream("/images/bar.png")),
                new Image(getClass().getResourceAsStream("/images/seven.png"))
        };

        slotsLeftImageView.setImage(images[0]);
        slotsCenterImageView.setImage(images[0]);
        slotsRightImageView.setImage(images[0]);
    }
    protected void changeBet(){
        try {
            double bet = Double.parseDouble(slotsBetTextField.getText());
            if (bet <= money){
                slots.setBet(bet);
                slotsWinLabel.setText("Bet: $" + slots.getBet());
                if ((slots.getBet() * 100) % 10 == 0){
                    slotsWinLabel.setText(slotsWinLabel.getText() + "0");
                }
            } else {
                slotsWinLabel.setText("Bet Over Limit");
            }
        } catch (IllegalArgumentException iae){
            slotsWinLabel.setText("Invalid Bet");
        }
        slotsBetTextField.setText("");
    }
    Slots slots = new Slots();

    @FXML
    private Label slotsWinLabel;
    @FXML
    private Label slotsMoneyLabel;
    @FXML
    private Button slotsBetButton;
    @FXML
    private Button slotsPlayButton;
    @FXML
    private Button slotsExitButton;
    @FXML
    private TextField slotsBetTextField;
    @FXML
    private ImageView slotsLeftImageView;
    @FXML
    private ImageView slotsCenterImageView;
    @FXML
    private ImageView slotsRightImageView;
    @FXML
    protected void onSlotsBetButtonClick(){
        changeBet();
    }
    @FXML
    protected void onSlotsPlayButtonClick(){
        if (slots.getBet() > money){
            slotsWinLabel.setText("Bet Over Limit");
        } else {

            money -= slots.getBet();
            String gameResults = slots.play();
            int i = gameResults.indexOf("!") + 1;
            int slot1 = Integer.parseInt(gameResults.substring(0,1));
            int slot2 = Integer.parseInt(gameResults.substring(1,2));
            int slot3 = Integer.parseInt(gameResults.substring(2,3));

            String message = "";

            double reward = Double.parseDouble(gameResults.substring(i));
            double round = (int) ((reward + 0.001) * 100);

            money += (round / 100);
            slotsMoneyLabel.setText("Money: $" + money);
            if ((money * 100) % 10 == 0){
                slotsMoneyLabel.setText(slotsMoneyLabel.getText() + "0");
            }

            slotsLeftImageView.setImage(images[slot1]);
            slotsCenterImageView.setImage(images[slot2]);
            slotsRightImageView.setImage(images[slot3]);

            if (money == 0){
                slotsPlayButton.setDisable(true);
                slotsBetButton.setDisable(true);
                slotsBetTextField.setDisable(true);
                message = "Lost All Money!\nClick Exit To Shut Down";
                slotsWinLabel.setText(message);
                bankrupt = true;
            } else {

                message = gameResults.substring(3,i);
                message += "\n(Bet: $" + slots.getBet();
                if ((slots.getBet() * 100) % 10 == 0){
                    message += "0";
                }
                slotsWinLabel.setText(message + ")");
            }
        }
    }
    @FXML
    protected void onSlotsExitButtonClick(ActionEvent actionEvent) throws IOException {

        if (bankrupt){
            Platform.exit();
        } else {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("games-view.fxml"));
            Parent gameViewParent = loader.load();

            GamesController gamesController = loader.getController();
            gamesController.initData(money);

            Scene gamesViewScene = new Scene(gameViewParent);

            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

            window.setTitle("Games");

            window.setScene(gamesViewScene);

            window.show();
        }
    }
    @FXML
    protected void onSlotsBetTextFieldAction(){
        changeBet();
    }
}
