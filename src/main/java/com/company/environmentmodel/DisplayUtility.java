package com.company.environmentmodel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animal.Animal;

import javafx.collections.ListChangeListener;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class DisplayUtility {
    private Environment environment;

    private int cellSize;
    private boolean drawOrientation;

    private Pane container;

    private ArrayList<MemberView> views;

    public DisplayUtility(Environment environment, Pane container, int cellSize) {
        this.environment = environment;
        this.container = container;

        this.views = new ArrayList<>();

        this.cellSize = cellSize;
        this.drawOrientation = false;

        // by resizing the container we will resize the canvas
        container.setPrefSize(environment.getWidth() * cellSize, environment.getHeight() * cellSize);

        container.setOnMouseClicked(event -> {
            int x = (int) event.getX() / cellSize;
            int y = (int) event.getY() / cellSize;

            System.out.printf("X: %d, Y: %d\n", x, y);
        });

        environment.getMembers().addListener((ListChangeListener<? super EnvironmentMember>) change -> {
            change.next();

            if (change.wasAdded()) {
                views.add(new MemberView(container, environment.getMembers().get(environment.getMembers().size() - 1),
                        this.cellSize, drawOrientation));
            }

            if (change.wasRemoved()) {
                System.out.printf("remove: %d, from: %d, to: %d\n", change.getRemovedSize(), change.getFrom(),
                        change.getTo());

                List<MemberView> subList = this.views.subList(change.getFrom(),
                        change.getFrom() + change.getRemovedSize());
                
                for (MemberView v : subList) {
                    v.removeFromParent();
                }
                
                subList.clear();
            }
        });
    }

    public void updateViews() {
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

        for (MemberView v : this.views) {
            v.setSize(cellSize);
        }
    }

    public void setEnvironmentSize(int environmentSize) {
        environment.setSize(environmentSize, environmentSize);

        container.setPrefSize(environment.getWidth() * cellSize, environment.getHeight() * cellSize);

        environment.reset();
    }

    public boolean isDrawOrientation() {
        return drawOrientation;
    }

    public void setDrawOrientation(boolean drawOrientation) {
        this.drawOrientation = drawOrientation;
        for (MemberView v : this.views) {
            v.setDrawOrientation(drawOrientation);
        }
    }

    public Pane getContainer() {
        return container;
    }
}

class MemberView extends StackPane {
    Pane parent; // container in the main window (the 'container' field in the `DisplayUtility` class)
    EnvironmentMember member;
    ImageView[] views;
    int size;
    boolean drawOrientation;

    public MemberView(Pane parent, EnvironmentMember member, int size, boolean drawOrientation) {
        super();
        this.parent = parent;
        this.member = member;
        this.size = size;
        this.drawOrientation = drawOrientation;
        this.views = new ImageView[3]; // 0 - the main picture, 1 - post mortem decoration, 2 - orientation
        
        this.setPrefSize(size, size);

        this.parent.getChildren().add(this);

        int x = member.getPosition().x * size;
        int y = member.getPosition().y * size;
    
        this.setLayoutX(x - this.getLayoutBounds().getMinX());
        this.setLayoutY(y - this.getLayoutBounds().getMinY());

        String filename = String.format("src/main/resources/com/company/environmentmodel/images/%s.png",
                member.getName());

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

        ImageView iv = new ImageView();
        iv.setImage(image);
        iv.setFitWidth(this.size);
        iv.setPreserveRatio(true);
        iv.setSmooth(false);

        this.views[0] = iv;

        this.getChildren().add(iv);

        this.updateOrientation();
    }

    public void updatePosition() {
    }

    public void removeFromParent() {
        this.parent.getChildren().remove(this);
    }

    public void setDrawOrientation(boolean flag) {
        this.drawOrientation = flag;
        this.updateOrientation();
    }

    private void updateOrientation() {
        this.updatePostMortemEffect();
        // TODO try visitor pattern
        if (drawOrientation && (this.member instanceof Animal)) {
            String filename = String.format(
                    "src/main/resources/com/company/environmentmodel/images/Direction%s.png",
                    ((Animal) this.member).getOrientation().name());

            Image image = null;
            try {
                FileInputStream input = new FileInputStream(filename);
                image = new Image(input);
            } catch (FileNotFoundException e) {
            }

            ImageView iv = new ImageView();
            iv.setImage(image);
            iv.setFitWidth(size);
            iv.setPreserveRatio(true);
            iv.setSmooth(false);

            this.views[2] = iv;

            this.getChildren().add(iv);
        } else {
            this.getChildren().remove(this.views[2]);
            this.views[2] = null;
        }
    }

    private void updatePostMortemEffect() {
        // TODO try visitor pattern
        if (drawOrientation && (this.member instanceof Animal)) {
            String filename = "src/main/resources/com/company/environmentmodel/images/Rip.png";

            Image image = null;
            try {
                FileInputStream input = new FileInputStream(filename);
                image = new Image(input);
            } catch (FileNotFoundException e1) {
            }

            ImageView iv = new ImageView();
            iv.setImage(image);
            iv.setFitWidth(size);
            iv.setPreserveRatio(true);
            iv.setSmooth(false);

            this.views[1] = iv;

            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-0.83);
            this.views[0].setEffect(colorAdjust);

            this.getChildren().add(iv);
        } else {
            this.getChildren().remove(this.views[1]);
            this.views[1] = null;

            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(0);
            this.views[0].setEffect(colorAdjust);
        }
    }

    public void setSize(int size) {
        this.size = size;

        int x = member.getPosition().x * size;
        int y = member.getPosition().y * size;

        this.setPrefSize(size, size);

        this.setLayoutX(x - this.getLayoutBounds().getMinX());
        this.setLayoutY(y - this.getLayoutBounds().getMinY());

        for (ImageView iv : this.views) {
            if (iv != null) {
                iv.setFitWidth(size);
            }
        }
    }
}