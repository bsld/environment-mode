package com.company.environmentmodel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.company.environmentmodel.environment.Cell;
import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animals.Animal;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class MainWindowController {
    private Environment environment;
    private int environmentSize;
    private int cellSize;

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
    private AnchorPane canvasContainer;
    @FXML
    private CheckBox cbDrawOrientation;

    private ResizableCanvas canvas;

    public MainWindowController() {
        this.canvas = new ResizableCanvas();
        this.environmentSize = 30;
        this.cellSize = 30;
        this.environment = new Environment(environmentSize, environmentSize);
    }

    @FXML
    public void initialize() {
        tfEnvironmentSize.setText(String.valueOf(environmentSize));
        tfCellSize.setText(String.valueOf(cellSize));

        canvasContainer.getChildren().add(canvas);

        // bind canvas's width and height to its container
        canvas.widthProperty().bind(canvasContainer.widthProperty());
        canvas.heightProperty().bind(canvasContainer.heightProperty());

        // by resizing the container we will resize the canvas
        canvasContainer.setPrefSize(environmentSize * cellSize, environmentSize * cellSize);

        canvasContainer.setOnMouseClicked(event -> {
            int x = (int) event.getX() / cellSize;
            int y = (int) event.getY() / cellSize;

            System.out.printf("X: %d, Y: %d\n", x, y);
        });

        btnSimulationControl.setOnAction(event -> {
            
        });

        btnRedraw.setOnAction(event -> {
            drawEnvironment(canvas);
        });

        cbDrawOrientation.setOnAction(event -> {
            drawEnvironment(canvas);
        });

        btnCellSize.setOnAction(event -> {
            int newSize;
            try {
                newSize = Integer.parseInt(tfCellSize.getText());
            } catch (NumberFormatException e) {
                System.out.println("unable to parse string, setting the default value");
                newSize = 30;
            }

            cellSize = newSize;

            canvasContainer.setPrefSize(environmentSize * cellSize, environmentSize * cellSize);
            drawEnvironment(canvas);
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

            environment.setSize(environmentSize, environmentSize);

            canvasContainer.setPrefSize(environmentSize * cellSize, environmentSize * cellSize);
            drawEnvironment(canvas);
        });

        btnAdd.setOnAction(event -> {
            environment.addRandomMembers(10);
            drawEnvironment(canvas);
        });

        btnClear.setOnAction(event -> {
            environment.reset();
            drawEnvironment(canvas);
        });

    }

    private void drawEnvironment(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int i = 0; i < environment.getWidth(); i++) {
            for (int j = 0; j < environment.getHeight(); j++) {
                Cell c = environment.getCell(i, j);
                if (c != null) {
                    int x = i * cellSize;
                    int y = j * cellSize;

                    EnvironmentMember m = c.getMember();
                    // if (m == null) continue;

                    String filename = String.format("src/main/resources/com/company/environmentmodel/images/%s.png",
                            m.getName());

                    DrawingUtility.drawImage(gc, filename, x, y, cellSize, cellSize);

                    // TODO try visitor pattern
                    if (cbDrawOrientation.isSelected() && (m instanceof Animal)) {
                        filename = String.format(
                                "src/main/resources/com/company/environmentmodel/images/Direction%s.png",
                                ((Animal) m).getOrientation().name());

                        gc.setGlobalAlpha(0.8);
                        DrawingUtility.drawImage(gc, filename, x, y, cellSize, cellSize);
                        gc.setGlobalAlpha(1.0);
                    }
                }
            }
        }
    }
}

class DrawingUtility {
    public static void drawImage(GraphicsContext gc, String filename, double x, double y, double w, double h) {
        Image image = null;
        try {
            FileInputStream input = new FileInputStream(filename);
            image = new Image(input);
        } catch (FileNotFoundException e1) {
            try {
                FileInputStream input = new FileInputStream(
                        "src/main/resources/com/company/environmentmodel/images/Unknown.png");
                image = new Image(input);
            } catch (Exception e) {
            }
        }

        gc.drawImage(image, x, y, w, h);
    }
}