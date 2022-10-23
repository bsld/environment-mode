package com.company.environmentmodel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.company.environmentmodel.environment.Cell;
import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animals.Animal;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class DisplayUtility {
    private Environment environment;

    private int cellSize;
    private boolean drawOrientation;

    private Pane container;
    private ResizableCanvas canvas;

    public DisplayUtility(Environment environment, Pane container, int cellSize) {
        this.environment = environment;
        this.container = container;

        this.canvas = new ResizableCanvas();

        this.cellSize = cellSize;
        this.drawOrientation = false;

        container.getChildren().add(canvas);

        // bind canvas's width and height to its container
        canvas.widthProperty().bind(container.widthProperty());
        canvas.heightProperty().bind(container.heightProperty());

        // by resizing the container we will resize the canvas
        container.setPrefSize(environment.getWidth() * cellSize, environment.getHeight() * cellSize);

        container.setOnMouseClicked(event -> {
            int x = (int) event.getX() / cellSize;
            int y = (int) event.getY() / cellSize;

            System.out.printf("X: %d, Y: %d\n", x, y);
        });
    }

    public void updateView() {
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
                    if (drawOrientation && (m instanceof Animal)) {
                        filename = String.format(
                                "src/main/resources/com/company/environmentmodel/images/Direction%s.png",
                                ((Animal) m).getOrientation().name());

                        gc.setGlobalAlpha(0.83);
                        DrawingUtility.drawImage(gc, filename, x, y, cellSize, cellSize);
                        gc.setGlobalAlpha(1.0);
                    }
                }
            }
        }
    }

    public Environment getEnvironment() {
        return environment;
    }

    public int getCellSize() {
        return cellSize;
    }
    
    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
        
        container.setPrefSize(environment.getWidth() * cellSize, environment.getHeight() * cellSize);
        
        updateView();
    }
    
    public void setEnvironmentSize(int environmentSize) {
        environment.setSize(environmentSize, environmentSize);
        
        container.setPrefSize(environment.getWidth() * cellSize, environment.getHeight() * cellSize);
        
        updateView();
    }
    
    public boolean isDrawOrientation() {
        return drawOrientation;
    }

    public void setDrawOrientation(boolean drawOrientation) {
        this.drawOrientation = drawOrientation;
    }

    public Pane getContainer() {
        return container;
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