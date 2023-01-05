package com.company.display;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.company.environmentmodel.environment.member.EnvironmentMember;
import com.company.environmentmodel.environment.member.animal.Animal;
import com.company.environmentmodel.environment.member.plants.Plant;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

class MemberView extends StackPane {
    Pane parent; // container in the main window (the 'container' field in the `DisplayUtility`
                 // class)
    EnvironmentMember member;
    ImageView[] views; // 0 - the main picture, 1 - post mortem decoration, 2 - orientation
    int size;
    boolean drawOrientation;

    public MemberView(Pane parent, EnvironmentMember member, int size, boolean drawOrientation) {
        super();
        this.parent = parent;
        this.member = member;
        this.size = size;
        this.drawOrientation = drawOrientation;
        this.views = new ImageView[3];

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

    public void update() {
        updatePosition();
        updateOrientation();
        updatePostMortemEffect();
        updatePlant();
    }

    public void updatePosition() {
        int x = member.getPosition().x * size;
        int y = member.getPosition().y * size;

        this.setLayoutX(x - this.getLayoutBounds().getMinX());
        this.setLayoutY(y - this.getLayoutBounds().getMinY());
    }

    public void removeFromParent() {
        this.parent.getChildren().remove(this);
    }

    public void setDrawOrientation(boolean flag) {
        this.drawOrientation = flag;
        this.updateOrientation();
    }

    private void updateOrientation() {
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

            if (this.views[2] == null) {
                ImageView iv = new ImageView();
                this.views[2] = iv;
                this.getChildren().add(iv);
            }

            this.views[2].setImage(image);
            this.views[2].setFitWidth(size);
            this.views[2].setPreserveRatio(true);
            this.views[2].setSmooth(false);
        } else {
            this.getChildren().remove(this.views[2]);
            this.views[2] = null;
        }
    }

    private void updatePostMortemEffect() {
        if (this.views[1] != null) {
            this.views[1].setFitWidth(size);
            this.views[1].setPreserveRatio(true);
            this.views[1].setSmooth(false);

            return;
        }

        if ((this.member instanceof Animal) && !(((Animal) this.member).isAlive())) {
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
        }
    }

    private void updatePlant() {
        if (this.member instanceof Plant) {
            Plant p = (Plant) this.member;
            if (p.isRecovering()) {
                String filename = "src/main/resources/com/company/environmentmodel/images/Recovering.png";

                Image image = null;
                try {
                    FileInputStream input = new FileInputStream(filename);
                    image = new Image(input);
                } catch (FileNotFoundException e) {
                }

                if (this.views[1] == null) {
                    ImageView iv = new ImageView();
                    this.views[1] = iv;
                    this.getChildren().add(iv);
                }

                this.views[1].setImage(image);
                this.views[1].setFitWidth(size);
                this.views[1].setPreserveRatio(true);
                this.views[1].setSmooth(false);

                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setSaturation(-0.83);
                this.views[0].setEffect(colorAdjust);
            } else {
                this.getChildren().remove(this.views[1]);
                this.views[1] = null;

                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setSaturation(0);
                this.views[0].setEffect(colorAdjust);
            }
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