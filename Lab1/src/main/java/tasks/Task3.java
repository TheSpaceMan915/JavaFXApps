package tasks;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task3 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static final List<ToggleButton> m_arr_toggles = new ArrayList<>();
    private static Shape m_shape = null;
    private static StackPane m_pane = null;

    public static Shape getPaneShape(int number)
    {

        Shape rand_shape = switch (number) {
            case 0 -> new Line(0,0,100,100);
            case 1 -> new Rectangle(100,100);
            case 2 -> new Circle(60);
            case 3 -> new Ellipse(60,100);
            default -> null;
        };

        return rand_shape;
    }

    public static void setOutlineStyle(String str)
    {
        switch (str) {
            case "Dashed" -> m_shape.getStrokeDashArray().addAll(2d);
            //case "Solid" -> BorderStrokeStyle.SOLID;
            case "Dotted" ->m_shape.getStrokeDashArray().addAll(2d,21d);
        };
    }

    public static ToggleButton createToggle(String name)
    {
        ToggleButton temp = new ToggleButton();

        //the stream will automatically close
        try (FileInputStream stream = new FileInputStream(name))
        {
            Image image_obj = new Image(stream);
            ImageView image = new ImageView(image_obj);
            temp.setGraphic(image);
        }
        catch (IOException exep)
        {exep.printStackTrace(); }

        return temp;
    }

    public static void keySwitcher(KeyEvent e)
    {
        KeyCode code = e.getCode();
        double x_pane_figures = m_pane.getLayoutX();
        double y_pane_figures = m_pane.getLayoutY();

        /*
        System.out.println(code);
        System.out.println("x: " + x_pane_figures);
        System.out.println("y: " + y_pane_figures);
         */

        switch (code)
        {
            case A -> m_pane.setLayoutX(x_pane_figures - 10);

            case D -> m_pane.setLayoutX(x_pane_figures + 10);

            case W -> m_pane.setLayoutY(y_pane_figures - 10);

            case S -> m_pane.setLayoutY(y_pane_figures + 10);

            case MINUS ->
            {
                if (m_shape instanceof Line shape_line)
                {
                    double end_y = shape_line.getEndY();
                    shape_line.setEndY(end_y - 5);
                }
                else if (m_shape instanceof Rectangle shape_rectangle)
                {
                    double height_rectangle = shape_rectangle.getHeight();
                    shape_rectangle.setHeight(height_rectangle - 5);
                }
                else if (m_shape instanceof Circle shape_circle)
                {
                    double radius_circle = shape_circle.getRadius();
                    shape_circle.setRadius(radius_circle - 5);
                }
                else
                {
                    Ellipse shape_ellipse = (Ellipse) m_shape;
                    double radius_y_ellipse = shape_ellipse.getRadiusY();
                    shape_ellipse.setRadiusY(radius_y_ellipse - 5);
                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage)
    {
        BorderPane pain_main = new BorderPane();
        final int width_scene = 700;
        final int height_scene = 700;
        Scene scene = new Scene(pain_main,width_scene,height_scene);

        //create components for the tools pane
        ToggleButton toggle1 = createToggle("Pictures/Line.jpg");
        ToggleButton toggle2 = createToggle("Pictures/Rectangle.jpg");
        ToggleButton toggle3 = createToggle("Pictures/Circle.jpg");
        ToggleButton toggle4 = createToggle("Pictures/Ellipse.jpg");

        m_arr_toggles.add(toggle1);
        m_arr_toggles.add(toggle2);
        m_arr_toggles.add(toggle3);
        m_arr_toggles.add(toggle4);


        Label label1 = new Label("Shapes");
        Label label2 = new Label("Outline colour");
        Label label3 = new Label("Shape colour");
        Label label4 = new Label("Outline");
        Label label5 = new Label("Thickness");
        Label label6 = new Label("Type");
        Label label7 = new Label("Size, pxl");
        Label label8 = new Label("Picture height");
        Label label9 = new Label("Picture width");

        //get values from the pickers
        ColorPicker picker_outline = new ColorPicker();
        ColorPicker picker_shape = new ColorPicker();
        picker_outline.setValue(Color.BLACK);


        ObservableList<String> types_str = FXCollections.observableArrayList("Solid","Dotted","Dashed");
        ComboBox<String> box_types_str = new ComboBox<>(types_str);
        box_types_str.setValue("Dashed");


        TextField field_outline = new TextField();
        TextField field_height = new TextField();
        TextField field_width = new TextField();

        field_outline.setPrefColumnCount(10);
        field_height.setPrefColumnCount(10);
        field_width.setPrefColumnCount(10);

        field_outline.setText("4");
        field_width.setText(Integer.toString(width_scene));
        field_height.setText(Integer.toString(height_scene));


        //set up the tools pane
        GridPane pain_tools = new GridPane();
        pain_tools.add(label1,0,0,1,1);
        pain_tools.add(toggle1,0,1,1,1);
        pain_tools.add(toggle2,0,2,1,1);
        pain_tools.add(toggle3,0,3,1,1);
        pain_tools.add(toggle4,0,4,1,1);

        pain_tools.add(label2,1,0,1,1);
        pain_tools.add(picker_outline,1,1,1,1);
        pain_tools.add(label3,1,2,1,1);
        pain_tools.add(picker_shape,1,3,1,1);

        pain_tools.add(label4,2,0,1,1);
        pain_tools.add(label5,2,1,1,1);
        pain_tools.add(field_outline,3,1,1,1);
        pain_tools.add(label6,2,2,1,1);
        pain_tools.add(box_types_str,3,2,1,1);

        pain_tools.add(label7,4,0,1,1);
        pain_tools.add(label8,4,1,1,1);
        pain_tools.add(field_height,5,1,1,1);
        pain_tools.add(label9,4,2,1,1);
        pain_tools.add(field_width,5,2,1,1);


        //set up the pictures pane
        Pane pane_images = new Pane();
        Border border_obj = new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,new BorderWidths(3)));

        pane_images.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                if (mouseEvent.getButton() == MouseButton.SECONDARY)
                {
                    for (int i = 0; i < m_arr_toggles.size(); i++)
                    {
                        if (m_arr_toggles.get(i).isSelected())
                        {
                            m_shape = getPaneShape(i);
                            Color color_shape = picker_shape.getValue();

                            Color outline_color = picker_outline.getValue();
                            double outline_thickness = Double.parseDouble(field_outline.getText());
                            String str_choice = box_types_str.getValue();
                            setOutlineStyle(str_choice);

                            m_pane = new StackPane();
                            m_pane.setOpacity(0.5);

                            m_shape.setFill(color_shape);
                            m_shape.setStroke(outline_color);
                            m_shape.setStrokeWidth(outline_thickness);

                            m_pane.getChildren().add(m_shape);
                            m_pane.setLayoutX(mouseEvent.getX());
                            m_pane.setLayoutY(mouseEvent.getY());
                            m_pane.setBorder(border_obj);

                            pane_images.getChildren().add(m_pane);

                        }
                    }
                }
                else if (mouseEvent.getButton() == MouseButton.PRIMARY)
                { m_pane.setBorder(null); }
            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                keySwitcher(event);

                final KeyCombination plus = new KeyCodeCombination(KeyCode.EQUALS, KeyCombination.SHIFT_DOWN);
                if (plus.match(event))
                {
                    if (m_shape instanceof Line shape_line)
                    {
                        double end_y = shape_line.getEndY();
                        shape_line.setEndY(end_y + 5);
                    }
                    else if (m_shape instanceof Rectangle shape_rectangle)
                    {
                        double height_rectangle = shape_rectangle.getHeight();
                        shape_rectangle.setHeight(height_rectangle + 5);
                    }
                    else if (m_shape instanceof Circle shape_circle)
                    {
                        double radius_circle = shape_circle.getRadius();
                        shape_circle.setRadius(radius_circle + 5);
                    }
                    else
                    {
                        Ellipse shape_ellipse = (Ellipse) m_shape;
                        double radius_y_ellipse = shape_ellipse.getRadiusY();
                        shape_ellipse.setRadiusY(radius_y_ellipse + 5);
                    }
                }

                final KeyCombination greater = new KeyCodeCombination(KeyCode.PERIOD, KeyCombination.SHIFT_DOWN);
                if (greater.match(event))
                {
                    if (m_shape instanceof Line shape_line)
                    {
                        double end_x = shape_line.getEndX();
                        shape_line.setEndX(end_x + 5);
                    }
                    else if (m_shape instanceof Rectangle shape_rectangle)
                    {
                        double width_rectangle = shape_rectangle.getWidth();
                        shape_rectangle.setWidth(width_rectangle + 5);
                    }
                    else if (m_shape instanceof Circle shape_circle)
                    {
                        double radius_circle = shape_circle.getRadius();
                        shape_circle.setRadius(radius_circle + 5);
                    }
                    else
                    {
                        Ellipse shape_ellipse = (Ellipse) m_shape;
                        double radius_x_ellipse = shape_ellipse.getRadiusX();
                        shape_ellipse.setRadiusX(radius_x_ellipse + 5);
                    }
                }

                final KeyCombination less = new KeyCodeCombination(KeyCode.COMMA, KeyCombination.SHIFT_DOWN);
                if (less.match(event))
                {
                    if (m_shape instanceof Line shape_line)
                    {
                        double end_x = shape_line.getEndX();
                        shape_line.setEndX(end_x - 5);
                    }
                    else if (m_shape instanceof Rectangle shape_rectangle)
                    {
                        double width_rectangle = shape_rectangle.getWidth();
                        shape_rectangle.setWidth(width_rectangle - 5);
                    }
                    else if (m_shape instanceof Circle shape_circle)
                    {
                        double radius_circle = shape_circle.getRadius();
                        shape_circle.setRadius(radius_circle - 5);
                    }
                    else
                    {
                        Ellipse shape_ellipse = (Ellipse) m_shape;
                        double radius_x_ellipse = shape_ellipse.getRadiusX();
                        shape_ellipse.setRadiusX(radius_x_ellipse - 5);

                    }
                }
            }
        });


        //set up the menu bar
        Menu menu_file = new Menu("_File");
        Menu menu_help = new Menu("Help");
        menu_file.setMnemonicParsing(true);

        MenuItem item_save = new MenuItem("Save...");
        MenuItem item_exit = new MenuItem("Exit");


        item_save.setOnAction(e ->
        {
            FileChooser chooser_obj = new FileChooser();
            try
            {
                int width = Integer.parseInt(field_width.getText());
                int height = Integer.parseInt(field_height.getText());

                //make a screenshot of the panel
                WritableImage image_obj = new WritableImage(width,height);
                pane_images.snapshot(null,image_obj);

                //save the screenshot
                File save_file = chooser_obj.showSaveDialog(primaryStage);
                chooser_obj.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Image files","*.png"));

                //take a snapshot
                ImageIO.write(SwingFXUtils.fromFXImage(image_obj,null),"png",save_file);

            }
            catch (IOException exep)
            { exep.printStackTrace(); }
        });

        item_exit.setOnAction(e -> Platform.exit());


        menu_file.getItems().addAll(item_save,new SeparatorMenuItem(),item_exit);

        //add the menu bar
        MenuBar menubar = new MenuBar();
        menubar.getMenus().addAll(menu_file,menu_help);

        pain_tools.setHgap(10);
        pain_tools.setPadding(new Insets(20,15,20,15));
        pain_main.setTop(pain_tools);
        pain_main.setBottom(menubar);
        pain_main.setCenter(pane_images);

        primaryStage.setTitle("Graphics Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
