package com.company.environmentmodel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.company.environmentmodel.neuro.ActivationFunction;
import com.company.environmentmodel.neuro.MlDataSet;
import com.company.environmentmodel.neuro.NeuralNetwork;

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
        // System.out.println(event.getCode());
        // switch (event.getCode()) {
        // case UP:
        // break;
        // case RIGHT:
        // break;
        // case DOWN:
        // break;
        // case LEFT:
        // break;
        // default:
        // break;
        // }
        // ;
        // });

        primaryStage.setTitle("Environment Model");
        primaryStage.getIcons().clear();
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(535.0);
        primaryStage.setMinHeight(344.0);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
        // MainNN2.main(args);
        System.exit(0);
    }
}

class MainNN2 {

    // Plant, Herbivore, Carnivore
    // nearby, left, front, right
    private static final double[][] INPUT = {
            /// Move
            { 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            /* */
            /// Plant nearby
            /* left */
            { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 1.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            { 1.0, 0.0, 0.0, /* */ 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            { 1.0, 0.0, 0.0, /* */ 0.0, 1.0, 1.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            /*                                                                              */
            { 1.0, 0.0, 0.0, /* */ 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            { 1.0, 0.0, 0.0, /* */ 1.0, 0.0, 1.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            { 1.0, 0.0, 0.0, /* */ 1.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            { 1.0, 0.0, 0.0, /* */ 1.0, 1.0, 1.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            /* front */
            { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 1.0, /* */ 0.0, 0.0, 0.0 },
            /* right */
            { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, },
            { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 1.0, },
            { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 1.0, 0.0, },
            { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 1.0, 1.0, },
            /*                                                                               */
            { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 0.0, 0.0, },
            { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 0.0, 1.0, },
            { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 1.0, 0.0, },
            { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 1.0, 1.0, },
            // /*                                                                              */
            // /// Herbivore nearby
            // /* left */
            // { 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 1.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 0.0, 1.0, 0.0, /* */ 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 0.0, 1.0, 0.0, /* */ 0.0, 1.0, 1.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // /*                                                                              */
            // { 0.0, 1.0, 0.0, /* */ 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 0.0, 1.0, 0.0, /* */ 1.0, 0.0, 1.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 0.0, 1.0, 0.0, /* */ 1.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 0.0, 1.0, 0.0, /* */ 1.0, 1.0, 1.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // /* front */
            // { 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 1.0, /* */ 0.0, 0.0, 0.0 },
            // /* right */
            // { 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, },
            // { 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 1.0, },
            // { 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 1.0, 0.0, },
            // { 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 1.0, 1.0, },
            // /*                                                                               */
            // { 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 0.0, 0.0, },
            // { 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 0.0, 1.0, },
            // { 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 1.0, 0.0, },
            // { 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 1.0, 1.0, },
            // /*                                                                              */
            // /// Plant nearby
            // /* left */
            // { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 1.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 1.0, 0.0, 0.0, /* */ 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 1.0, 0.0, 0.0, /* */ 0.0, 1.0, 1.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // /*                                                                              */
            // { 1.0, 0.0, 0.0, /* */ 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 1.0, 0.0, 0.0, /* */ 1.0, 0.0, 1.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 1.0, 0.0, 0.0, /* */ 1.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 1.0, 0.0, 0.0, /* */ 1.0, 1.0, 1.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // /* front */
            // { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0 },
            // { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 1.0, /* */ 0.0, 0.0, 0.0 },
            // /* right */
            // { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, },
            // { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 1.0, },
            // { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 1.0, 0.0, },
            // { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 1.0, 1.0, },
            // /*                                                                               */
            // { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 0.0, 0.0, },
            // { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 0.0, 1.0, },
            // { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 1.0, 0.0, },
            // { 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 1.0, 1.0, },
            // /*                                                                              */
    };

    // move, eat, turn left, turn right
    private static final double[][] IDEAL = {
            // { 0.0, 1.0, 0.0, 0.0 },
            /// Move
            { 1.0, 0.0, 0.0, 0.0 },
            /* */
            /// Plant nearby
            /* left */
            { 0.0, 1.0, 0.0, 0.0 },
            { 0.0, 0.0, 0.0, 1.0 },
            { 0.0, 0.0, 0.0, 1.0 },
            { 0.0, 0.0, 0.0, 1.0 },
            /*                                                                              */
            { 0.0, 1.0, 0.0, 0.0 },
            { 0.0, 0.0, 0.0, 1.0 },
            { 0.0, 0.0, 0.0, 1.0 },
            { 0.0, 0.0, 0.0, 1.0 },
            /* front */
            { 0.0, 1.0, 0.0, 0.0 },
            { 0.0, 0.0, 0.0, 1.0 },
            { 0.0, 0.0, 0.0, 1.0 },
            /* right */
            { 0.0, 1.0, 0.0, 0.0 },
            { 0.0, 0.0, 1.0, 0.0 },
            { 0.0, 0.0, 1.0, 0.0 },
            { 0.0, 0.0, 1.0, 0.0 },
            /*                                                                               */
            { 0.0, 1.0, 0.0, 0.0 },
            { 0.0, 0.0, 1.0, 0.0 },
            { 0.0, 0.0, 1.0, 0.0 },
            { 0.0, 0.0, 1.0, 0.0 },
            /*                                                                              */

    };

    public static void main(String[] args) {

        NeuralNetwork net = new NeuralNetwork(INPUT[0].length, 13, IDEAL[0].length);
        net.init();
        net.setLearningRate(0.01);
        net.setMomentum(0.5);
        // net.setActivationFunction(ActivationFunction.leakyReLu());
        net.setActivationFunction(ActivationFunction.sigmoid());

        MlDataSet dataSet = new MlDataSet(INPUT, IDEAL);
        net.train(dataSet, 120000);

        net.predict(0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0);
        net.predict(1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0);
        net.predict(1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0);
        net.predict(1.0, 0.0, 0.0, /* */ 0.0, 0.0, 1.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0);
        net.predict(1.0, 0.0, 0.0, /* */ 0.0, 1.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0);
        net.predict(1.0, 0.0, 0.0, /* */ 0.0, 1.0, 1.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0);
        net.predict(1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 0.0, 1.0, /* */ 0.0, 0.0, 0.0);
        net.predict(1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 1.0, 1.0, 1.0, /* */ 0.0, 0.0, 0.0);

    }

    public static void main2(String[] args) {

        NeuralNetwork neuralNetwork = new NeuralNetwork(INPUT[0].length, 13, IDEAL[0].length);
        neuralNetwork.init();
        neuralNetwork.setLearningRate(0.01);
        neuralNetwork.setMomentum(0.4);
        neuralNetwork.setActivationFunction(ActivationFunction.leakyReLu());

        MlDataSet dataSet = new MlDataSet(INPUT, IDEAL);
        neuralNetwork.train(dataSet, 100000);

        neuralNetwork.predict(0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0);
        neuralNetwork.predict(1.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0, /* */ 0.0, 0.0, 0.0);

    }
}

class MainNN {

    private static final double[][] XOR_INPUT = {
            { 1, 1 },
            { 1, 0 },
            { 0, 1 },
            { 0, 0 }
    };

    private static final double[][] XOR_IDEAL = {
            { 0, 0 },
            { 1, 1 },
            { 1, 1 },
            { 0, 0 }
    };

    public static void main(String[] args) {

        NeuralNetwork neuralNetwork = new NeuralNetwork(2, 13, 2);
        neuralNetwork.init();
        neuralNetwork.setLearningRate(0.01);
        neuralNetwork.setMomentum(0.5);
        // neuralNetwork.setActivationFunction(ActivationFunction.leakyReLu());
        neuralNetwork.setActivationFunction(ActivationFunction.sigmoid());

        MlDataSet dataSet = new MlDataSet(XOR_INPUT, XOR_IDEAL);
        neuralNetwork.train(dataSet, 100000);

        neuralNetwork.predict(1, 1);
        neuralNetwork.predict(1, 0);
        neuralNetwork.predict(0, 1);
        neuralNetwork.predict(0, 0);

    }

    public static void main2(String[] args) {

        NeuralNetwork neuralNetwork = new NeuralNetwork(2, 59, 2);
        neuralNetwork.init();
        neuralNetwork.setLearningRate(0.01);
        neuralNetwork.setMomentum(0.4);
        neuralNetwork.setActivationFunction(ActivationFunction.leakyReLu());

        MlDataSet dataSet = new MlDataSet(XOR_INPUT, XOR_IDEAL);
        neuralNetwork.train(dataSet, 150000);

        neuralNetwork.predict(1, 1);
        neuralNetwork.predict(1, 0);
        neuralNetwork.predict(0, 1);
        neuralNetwork.predict(0, 0);

    }
}