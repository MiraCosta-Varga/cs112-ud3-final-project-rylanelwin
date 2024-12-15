package cs112.ud3.controllers;

import cs112.ud3.MainApplication;
import cs112.ud3.models.Blackjack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class BlackjackController {
    private double money;

    protected void initData(double money){
        this.money = money;
        blackjackMoneyLabel.setText("Money: $" + money);
    }
    @FXML
    private Label blackjackWinLabel;
    @FXML
    private Label blackjackMoneyLabel;
    @FXML
    private Button blackjackBetButton;
    @FXML
    private Button blackjackPlayButton;
    @FXML
    private Button blackjackExitButton;
    @FXML
    private TextField blackjackBetTextField;
    @FXML
    private Button blackjackHitButton;
    @FXML
    private Button blackjackStandButton;
    @FXML
    private Label blackjackCardLabel1;
    @FXML
    private Label blackjackCardLabel2;
    @FXML
    private Label blackjackOtherCardLabel1;
    @FXML
    private Label blackjackOtherCardLabel2;
    @FXML
    private Rectangle blackjackCardRectangle1;
    @FXML
    private Rectangle blackjackCardRectangle2;
    @FXML
    private Rectangle blackjackOtherCardRectangle1;
    @FXML
    private Rectangle blackjackOtherCardRectangle2;
    @FXML
    private StackPane blackjackHandStackPane;
    @FXML
    private StackPane blackjackOtherHandStackPane;
    @FXML
    private StackPane blackjackCardStackPane1;
    @FXML
    private StackPane blackjackCardStackPane2;
    @FXML
    private StackPane blackjackOtherCardStackPane1;
    @FXML
    private StackPane blackjackOtherCardStackPane2;
    @FXML
    protected void onBlackjackBetButtonClick(){

        blackjackWinLabel.setText("blackjack bet button clicked");
    }
    @FXML
    protected void onBlackjackPlayButtonClick(){

        blackjackWinLabel.setText("blackjack play button clicked");
    }
    @FXML
    protected void onBlackjackExitButtonClick(ActionEvent actionEvent) throws IOException {

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
    @FXML
    protected void onBlackjackBetTextFieldAction(){
        blackjackWinLabel.setText(blackjackBetTextField.getText());
        blackjackBetTextField.setText("");
    }
    @FXML
    protected void onBlackjackHitButtonClick(){

        blackjackWinLabel.setText("blackjack hit button clicked");
    }
    @FXML
    protected void onBlackjackStandButtonClick(){

        blackjackWinLabel.setText("blackjack stand button clicked");
    }
}
