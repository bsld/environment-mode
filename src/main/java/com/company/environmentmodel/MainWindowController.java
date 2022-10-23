package com.company.environmentmodel;

import com.company.environmentmodel.environment.Environment;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class MainWindowController {
    private Environment environment;
    private int environmentSize;
    private int cellSize;
    private boolean running = false;

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

    public MainWindowController() {
        this.environmentSize = 30;
        this.cellSize = 30;
        this.environment = new Environment(environmentSize, environmentSize);
    }

    @FXML
    public void initialize() {
        tfEnvironmentSize.setText(String.valueOf(environmentSize));
        tfCellSize.setText(String.valueOf(cellSize));

        this.timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                environment.update();
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
            environment.addRandomMembers(10);
        });

        btnClear.setOnAction(event -> {
            environment.reset();
        });

        this.displayUtility = new DisplayUtility(environment, container, cellSize);
    }
}