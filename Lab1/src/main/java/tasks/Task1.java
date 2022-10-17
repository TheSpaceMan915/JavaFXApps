package tasks;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Task1 extends Application
{
    private List<ToggleButton> m_arr_toggles;

    public static ToggleButton createToggle(String file_name)
    {
        Image image_obj = null;
        ImageView imageview_obj = null;
        try
        {
            FileInputStream stream = new FileInputStream(file_name);
            image_obj = new Image(stream);
            imageview_obj = new ImageView(image_obj);
        }
        catch(FileNotFoundException exep)
        { exep.printStackTrace(); }

        ToggleButton button =  new ToggleButton("Picture",imageview_obj);
        button.setUserData(image_obj);

        return button;
    }

    @Override
    public void start(Stage stage)
    {

        //create my scene
        BorderPane pane_main = new BorderPane();
        Scene scene_obj = new Scene(pane_main,600,600);
        Button button_obj = new Button("Save");
        Pane pane_pictures = new Pane();

        //create a panel with toggles
        m_arr_toggles = new ArrayList<>();
        ToggleButton toggle_temp = createToggle("/Users/jackyokov/IdeaProjects/JavaFX/Lab1/src/main/resources/files/pictures/Pic1.jpg");
        m_arr_toggles.add(toggle_temp);
        toggle_temp = createToggle("/Users/jackyokov/IdeaProjects/JavaFX/Lab1/src/main/resources/files/pictures/Pic2.jpg");
        m_arr_toggles.add(toggle_temp);
        toggle_temp = createToggle("/Users/jackyokov/IdeaProjects/JavaFX/Lab1/src/main/resources/files/pictures/Pic3.jpg");
        m_arr_toggles.add(toggle_temp);
        toggle_temp = createToggle("/Users/jackyokov/IdeaProjects/JavaFX/Lab1/src/main/resources/files/pictures/Pic4.png");
        m_arr_toggles.add(toggle_temp);

        VBox box_toggles = new VBox();
        m_arr_toggles.forEach(temp -> box_toggles.getChildren().add(temp));
        box_toggles.setSpacing(100);

        //make the button fill all available space
        button_obj.setMaxWidth(Double.MAX_VALUE);

        //set an ActionListener for the button
        button_obj.setOnAction(new EventHandler<ActionEvent>()
        {
            FileChooser chooser_obj = new FileChooser();
            @Override
            public void handle(ActionEvent e)
            {
                try
                {
                    //make a screenshot of the panel
                    WritableImage image_obj = new WritableImage((int) pane_pictures.getWidth(),(int) pane_pictures.getHeight());
                    pane_pictures.snapshot(null,image_obj);

                    //save the screenshot
                    File save_file = chooser_obj.showSaveDialog(stage);
                    chooser_obj.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("Image files","*.png"));

                    //take a snapshot
                    ImageIO.write(SwingFXUtils.fromFXImage(image_obj,null),"png",save_file);

                }
                catch (Exception exep)
                { exep.printStackTrace(); }
            }
        });

        //set an ActionListener for the pane
        pane_pictures.setOnMouseClicked((MouseEvent e) ->
        {
            //check if it is the right button
            if (e.getButton() == MouseButton.SECONDARY)
            {
                for (ToggleButton temp : m_arr_toggles)
                {
                    if (temp.isSelected())
                    {
                        //get the image from the button
                        Image image_obj = (Image) temp.getUserData();

                        //every time create a new ImageView object and add it to the pane
                        ImageView imageview_obj = new ImageView(image_obj);
                        imageview_obj.setLayoutX(e.getSceneX());
                        imageview_obj.setLayoutY(e.getSceneY());
                        pane_pictures.getChildren().add(imageview_obj);
                        break;
                    }
                }
            }
            else
            {System.out.println("The button is not down");}
        });

        pane_main.setBottom(button_obj);
        pane_main.setRight(box_toggles);
        pane_main.setCenter(pane_pictures);

        stage.setTitle("FxApp");
        stage.setScene(scene_obj);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}