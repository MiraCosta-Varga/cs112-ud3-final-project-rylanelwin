package cs112.ud3;

import cs112.ud3.controllers.GamesController;
import cs112.ud3.controllers.TitleController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("title-view.fxml"));
        Parent titleViewParent = loader.load();

        TitleController titleController = loader.getController();
        titleController.initData();

        Scene scene = new Scene(titleViewParent);

        stage.setScene(scene);

        stage.setTitle("Games");

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}