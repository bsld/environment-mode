package com.company.environmentmodel;

import com.company.environmentmodel.environment.Direction;
import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.Tuple;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class MainWindowController {
    private Environment environment;
    private int environmentSize;
    private int cellSize;

    private boolean running = false;
    private long lastUpdate = 0;
    private long frames = 0;

    private DisplayUtility displayUtility;
    private AnimationTimer timer;

    @FXML
    private TextField tfEnvironmentSize;
    @FXML
    private Button btnEnvironmentSize;
    @FXML
    private TextField tfCellSize;
    @FXML
    private Button btnCellSize;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnRedraw;
    @FXML
    private Button btnSimulationControl;
    @FXML
    private Pane container;
    @FXML
    private CheckBox cbDrawOrientation;

    @FXML
    private Slider sldAnimationSpeed;
    @FXML
    private Label lblFrames;

    public MainWindowController() {
        this.environmentSize = 20;
        this.cellSize = 20;
        this.environment = new Environment(environmentSize, environmentSize);
    }

    @FXML
    public void initialize() {
        tfEnvironmentSize.setText(String.valueOf(environmentSize));
        tfCellSize.setText(String.valueOf(cellSize));
        lastUpdate = System.nanoTime();
        sldAnimationSpeed.setValue(1);

        // sldAnimationSpeed.valueProperty().addListener(new ChangeListener<Number>() {

        //     @Override
        //     public void changed(
        //             ObservableValue<? extends Number> observableValue,
        //             Number oldValue,
        //             Number newValue) {
        //             System.out.println(newValue);
        //     }
        // });

        this.timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0; // one second in nanos

                if (elapsedSeconds >= (1.0 / sldAnimationSpeed.getValue())) {
                    lastUpdate = now;
                    environment.update();
                    displayUtility.updateViews();
                    lblFrames.setText(Long.toString(++frames));
                }
            }
        };

        btnSimulationControl.setOnAction(event -> {
            this.running = !this.running;

            if (this.running) {
                this.timer.start();
            } else {
                this.timer.stop();
            }

            this.tfEnvironmentSize.setDisable(this.running);
            this.btnEnvironmentSize.setDisable(this.running);
            this.tfCellSize.setDisable(this.running);
            this.btnCellSize.setDisable(this.running);
            this.btnAdd.setDisable(this.running);
            this.btnClear.setDisable(this.running);
            this.btnRedraw.setDisable(this.running);
            this.cbDrawOrientation.setDisable(this.running);
        });

        btnRedraw.setOnAction(event -> {
            // displayUtility.updateViews();
        });

        cbDrawOrientation.setOnAction(event -> {
            displayUtility.setDrawOrientation(cbDrawOrientation.isSelected());
        });

        btnCellSize.setOnAction(event -> {
            int newSize;
            try {
                newSize = Integer.parseInt(tfCellSize.getText());
            } catch (NumberFormatException e) {
                System.out.println("unable to parse string, setting the default value");
                newSize = 30;
            }

            displayUtility.setCellSize(newSize);
        });

        btnEnvironmentSize.setOnAction(event -> {
            int newSize;
            try {
                newSize = Integer.parseInt(tfEnvironmentSize.getText());
            } catch (NumberFormatException e) {
                System.out.println("unable to parse string, setting the default value");
                newSize = 30;
            }
            environmentSize = newSize;

            displayUtility.setEnvironmentSize(newSize);
        });

        btnAdd.setOnAction(event -> {
            // environment.addTestLayout2(new Tuple(7, 7), Direction.NORTH);
            environment.addRandomMembers(10);
            // environment.addTestLayout(new Tuple(0, 0), Direction.NORTH);
            // environment.addTestLayout(new Tuple(7, 7), Direction.NORTH);
            // environment.addTestLayout(new Tuple(13, 7), Direction.EAST);
            // environment.addTestLayout(new Tuple(7, 13), Direction.WEST);
            // environment.addTestLayout(new Tuple(13, 13), Direction.SOUTH);
        });

        btnClear.setOnAction(event -> {
            environment.reset();
        });

        this.displayUtility = new DisplayUtility(environment, container, cellSize);
    }
}