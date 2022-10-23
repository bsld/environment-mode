package com.company.environmentmodel;

import com.company.environmentmodel.environment.Environment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class MainWindowController {
    private Environment environment;
    private int environmentSize;
    private int cellSize;
    
    private DisplayUtility displayUtility;
    
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

        btnSimulationControl.setOnAction(event -> {
            
        });

        btnRedraw.setOnAction(event -> {
            displayUtility.updateView();
        });

        cbDrawOrientation.setOnAction(event -> {
            displayUtility.setDrawOrientation(cbDrawOrientation.isSelected());
            displayUtility.updateView();
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
                // unable to parse string, setting the default value
                newSize = 30;
            }
            environmentSize = newSize;

            displayUtility.setEnvironmentSize(newSize);
        });

        btnAdd.setOnAction(event -> {
            environment.addRandomMembers(10);
            displayUtility.updateView();
        });

        btnClear.setOnAction(event -> {
            environment.reset();
            displayUtility.updateView();
        });
        
        this.displayUtility = new DisplayUtility(environment, container, cellSize);
    }
}