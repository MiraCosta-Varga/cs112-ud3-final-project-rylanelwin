package cs112.ud3.controllers;

import cs112.ud3.MainApplication;
import cs112.ud3.models.RockPaperScissors;
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

public class RpsController {

    private double money;
    private boolean bankrupt = false;
    protected void initData(double money){
        this.money = money;
        rpsMoneyLabel.setText("Money: $" + money);
        if ((money * 100) % 10 == 0){
            rpsMoneyLabel.setText(rpsMoneyLabel.getText() + "0");
        }

        Image rock = new Image(getClass().getResourceAsStream("/images/rock.png"));
        Image paper = new Image(getClass().getResourceAsStream("/images/paper.png"));
        Image scissors = new Image(getClass().getResourceAsStream("/images/scissors.png"));

        rpsRockImageView.setImage(rock);
        rpsOtherRockImageView.setImage(rock);
        rpsPaperImageView.setImage(paper);
        rpsOtherPaperImageView.setImage(paper);
        rpsScissorsImageView.setImage(scissors);
        rpsOtherScissorsImageView.setImage(scissors);

        rpsWinLabel.setText("Bet: $" + rps.getBet());
        if ((rps.getBet() * 100) % 10 == 0){
            rpsWinLabel.setText(rpsWinLabel.getText() + "0");
        }
    }
    protected void changeBet(){
        try {
            double bet = Double.parseDouble(rpsBetTextField.getText());
            if (bet <= money){
                rps.setBet(bet);
                rpsWinLabel.setText("Bet: $" + rps.getBet());
                if ((rps.getBet() * 100) % 10 == 0){
                    rpsWinLabel.setText(rpsWinLabel.getText() + "0");
                }
            } else {
                rpsWinLabel.setText("Bet Over Limit");
            }
        } catch (IllegalArgumentException iae){
            rpsWinLabel.setText("Invalid Bet");
        }
        rpsBetTextField.setText("");
    }
    RockPaperScissors rps = new RockPaperScissors();

    @FXML
    private Label rpsWinLabel;
    @FXML
    private Label rpsMoneyLabel;
    @FXML
    private Label rpsChoiceLabel;
    @FXML
    private Label rpsOtherChoiceLabel;
    @FXML
    private Button rpsBetButton;
    @FXML
    private Button rpsPlayButton;
    @FXML
    private Button rpsExitButton;
    @FXML
    private TextField rpsBetTextField;
    @FXML
    private ImageView rpsRockImageView;
    @FXML
    private ImageView rpsOtherRockImageView;
    @FXML
    private ImageView rpsPaperImageView;
    @FXML
    private ImageView rpsOtherPaperImageView;
    @FXML
    private ImageView rpsScissorsImageView;
    @FXML
    private ImageView rpsOtherScissorsImageView;
    @FXML
    protected void onRpsBetButtonClick(){
        changeBet();
    }
    @FXML
    protected void onRpsPlayButtonClick(){
        if (rps.getBet() > money){
            rpsWinLabel.setText("Bet Over Limit");
        } else {

            money -= rps.getBet();
            String gameResults = rps.play();
            int i = gameResults.indexOf(",");
            int j = gameResults.indexOf("!") + 1;
            String otherChoice = gameResults.substring(0, i);
            String message = "";
            double reward = Double.parseDouble(gameResults.substring(j));
            double round = (int) ((reward + 0.001) * 100);

            money += (round / 100);
            rpsMoneyLabel.setText("Money: $" + money);
            if ((money * 100) % 10 == 0){
                rpsMoneyLabel.setText(rpsMoneyLabel.getText() + "0");
            }

            if (otherChoice.equals("Rock")){
                rpsOtherRockImageView.setOpacity(1);
                rpsOtherPaperImageView.setOpacity(0.2);
                rpsOtherScissorsImageView.setOpacity(0.2);
            } else if (otherChoice.equals("Paper")){
                rpsOtherRockImageView.setOpacity(0.2);
                rpsOtherPaperImageView.setOpacity(1);
                rpsOtherScissorsImageView.setOpacity(0.2);
            } else {
                rpsOtherRockImageView.setOpacity(0.2);
                rpsOtherPaperImageView.setOpacity(0.2);
                rpsOtherScissorsImageView.setOpacity(1);
            }

            if (money == 0){
                rpsPlayButton.setDisable(true);
                rpsBetButton.setDisable(true);
                rpsBetTextField.setDisable(true);
                rpsRockImageView.setDisable(true);
                rpsPaperImageView.setDisable(true);
                rpsScissorsImageView.setDisable(true);
                message = "Lost All Money!\nClick Exit To Shut Down";
                rpsWinLabel.setText(message);
                bankrupt = true;
            } else {

                message = gameResults.substring(i+1,j);
                message += "\n(Bet: $" + rps.getBet();
                if ((rps.getBet() * 100) % 10 == 0){
                    message += "0";
                }
                rpsWinLabel.setText(message + ")");
            }
        }
    }
    @FXML
    protected void onRpsExitButtonClick(ActionEvent actionEvent) throws IOException {

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
    protected void onRpsBetTextFieldAction(){
        changeBet();
    }
    @FXML
    protected void onRpsRockImageViewClick(){
        rps.setChoice("Rock");
        rpsChoiceLabel.setText("Your Choice: Rock");
        rpsRockImageView.setOpacity(1);
        rpsPaperImageView.setOpacity(0.2);
        rpsScissorsImageView.setOpacity(0.2);
    }
    @FXML
    protected void onRpsPaperImageViewClick(){
        rps.setChoice("Paper");
        rpsChoiceLabel.setText("Your Choice: Paper");
        rpsRockImageView.setOpacity(0.2);
        rpsPaperImageView.setOpacity(1);
        rpsScissorsImageView.setOpacity(0.2);
    }
    @FXML
    protected void onRpsScissorsImageViewClick(){
        rps.setChoice("Scissors");
        rpsChoiceLabel.setText("Your Choice: Scissors");
        rpsRockImageView.setOpacity(0.2);
        rpsPaperImageView.setOpacity(0.2);
        rpsScissorsImageView.setOpacity(1);
    }
}
