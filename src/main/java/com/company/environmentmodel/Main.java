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

        // scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
        //     System.out.println(event.getCode());
        //     switch (event.getCode()) {
        //             case UP:
        //                 break;
        //             case RIGHT:
        //                 break;
        //             case DOWN:
        //                 break;
        //             case LEFT:
        //                 break;
        //             default:
        //                 break;
        //         }
        //         ;
        // });

        primaryStage.setTitle("Environment Model");
        primaryStage.getIcons().clear();
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(535.0);
        primaryStage.setMinHeight(344.0);
        
        primaryStage.show();
        
        primaryStage.setAlwaysOnTop(true); // `Stage.toFront` doesn't work,
        primaryStage.setAlwaysOnTop(false); // so we do that like this
        primaryStage.requestFocus(); // switch the focus to our window (also doesn't work)
    }

    public static void main(String[] args) {
        launch();
    }
}