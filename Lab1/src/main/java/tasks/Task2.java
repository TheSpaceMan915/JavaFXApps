package tasks;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

public class Task2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static Shape getRandomShape()
    {
        int min = 0, max = 3;
        int rand = ThreadLocalRandom.current().nextInt(min,max + 1);

        Shape rand_shape = switch(rand) {
            case 0 -> new Line(0,0,100,100);
            case 1 -> new Rectangle(100,100);
            case 2 -> new Circle(60);
            case 3 -> new Ellipse(60,100);
            default -> null;
        };

        rand_shape.setFill(Color.LAVENDER);
        return rand_shape;
    }

    @Override
    public void start(Stage primaryStage)
    {
        //just place the components on this pane
        Pane pane_main = new Pane();
        Scene scene_obj = new Scene(pane_main,600,600);

        //get the shape
        Shape shape_obj = getRandomShape();

        //create a StackPane containing my components
        StackPane pane_figures = new StackPane();
        Border border_obj = new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.DASHED,null,new BorderWidths(2)));
        pane_figures.setBorder(border_obj);
        pane_figures.setLayoutX(275);
        pane_figures.setLayoutY(275);

        //set the key event
        scene_obj.setOnKeyPressed(e ->
        {
            KeyCode code = e.getCode();
            double x_pane_figures = pane_figures.getLayoutX();
            double y_pane_figures = pane_figures.getLayoutY();

            switch (code)
            {
                case LEFT -> pane_figures.setLayoutX(x_pane_figures - 10);

                case RIGHT -> pane_figures.setLayoutX(x_pane_figures + 10);

                case UP -> pane_figures.setLayoutY(y_pane_figures - 10);

                case DOWN -> pane_figures.setLayoutY(y_pane_figures + 10);

                case MINUS ->
                {
                    if (shape_obj instanceof Line shape_line)
                    {
                        double end_y = shape_line.getEndY();
                        shape_line.setEndY(end_y - 5);
                    }
                    else if (shape_obj instanceof Rectangle shape_rectangle)
                    {
                        double height_rectangle = shape_rectangle.getHeight();
                        shape_rectangle.setHeight(height_rectangle - 5);
                    }
                    else if (shape_obj instanceof Circle shape_circle)
                    {
                        double radius_circle = shape_circle.getRadius();
                        shape_circle.setRadius(radius_circle - 5);
                    }
                    else
                    {
                        Ellipse shape_ellipse = (Ellipse) shape_obj;
                        double radius_y_ellipse = shape_ellipse.getRadiusY();
                        shape_ellipse.setRadiusY(radius_y_ellipse - 5);
                    }
                }
            }

            final KeyCombination plus = new KeyCodeCombination(KeyCode.EQUALS,KeyCombination.SHIFT_DOWN);
            if (plus.match(e))
            {
                if (shape_obj instanceof Line shape_line)
                {
                    double end_y = shape_line.getEndY();
                    shape_line.setEndY(end_y + 5);
                }
                else if (shape_obj instanceof Rectangle shape_rectangle)
                {
                    double height_rectangle = shape_rectangle.getHeight();
                    shape_rectangle.setHeight(height_rectangle + 5);
                }
                else if (shape_obj instanceof Circle shape_circle)
                {
                    double radius_circle = shape_circle.getRadius();
                    shape_circle.setRadius(radius_circle + 5);
                }
                else
                {
                    Ellipse shape_ellipse = (Ellipse) shape_obj;
                    double radius_y_ellipse = shape_ellipse.getRadiusY();
                    shape_ellipse.setRadiusY(radius_y_ellipse + 5);
                }
            }

            final KeyCombination greater = new KeyCodeCombination(KeyCode.PERIOD,KeyCombination.SHIFT_DOWN);
            if (greater.match(e))
            {
                if (shape_obj instanceof Line shape_line)
                {
                    double end_x = shape_line.getEndX();
                    shape_line.setEndX(end_x + 5);
                }
                else if (shape_obj instanceof Rectangle shape_rectangle)
                {
                    double width_rectangle = shape_rectangle.getWidth();
                    shape_rectangle.setWidth(width_rectangle + 5);
                }
                else if (shape_obj instanceof Circle shape_circle)
                {
                    double radius_circle = shape_circle.getRadius();
                    shape_circle.setRadius(radius_circle + 5);
                }
                else
                {
                    Ellipse shape_ellipse = (Ellipse) shape_obj;
                    double radius_x_ellipse = shape_ellipse.getRadiusX();
                    shape_ellipse.setRadiusX(radius_x_ellipse + 5);
                }
            }

            final KeyCombination less = new KeyCodeCombination(KeyCode.COMMA,KeyCombination.SHIFT_DOWN);
            if (less.match(e))
            {
                if (shape_obj instanceof Line shape_line)
                {
                    double end_x = shape_line.getEndX();
                    shape_line.setEndX(end_x - 5);
                }
                else if (shape_obj instanceof Rectangle shape_rectangle)
                {
                    double width_rectangle = shape_rectangle.getWidth();
                    shape_rectangle.setWidth(width_rectangle - 5);
                }
                else if (shape_obj instanceof Circle shape_circle)
                {
                    double radius_circle = shape_circle.getRadius();
                    shape_circle.setRadius(radius_circle - 5);
                }
                else
                {
                    Ellipse shape_ellipse = (Ellipse) shape_obj;
                    double radius_x_ellipse = shape_ellipse.getRadiusX();
                    shape_ellipse.setRadiusX(radius_x_ellipse - 5);
                }
            }
        });


        pane_figures.getChildren().add(shape_obj);
        pane_main.getChildren().add(pane_figures);

        primaryStage.setTitle("FxApp");
        primaryStage.setScene(scene_obj);
        primaryStage.show();
    }
}
