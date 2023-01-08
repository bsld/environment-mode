package com.company.environmentmodel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/main-window.fxml"));

        MainWindowController controller = new MainWindowController();
        fxmlLoader.setController(controller);

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);

        primaryStage.setTitle("Environment Model");
        primaryStage.getIcons().clear();
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(535.0);
        primaryStage.setMinHeight(344.0);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
        System.exit(0);
    }
}