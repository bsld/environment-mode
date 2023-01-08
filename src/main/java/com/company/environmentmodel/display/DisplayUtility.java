package com.company.environmentmodel.display;

import java.util.ArrayList;
import java.util.List;

import com.company.environmentmodel.environment.Environment;
import com.company.environmentmodel.environment.member.EnvironmentMember;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

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
        for (MemberView v : views) {
            v.update();
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