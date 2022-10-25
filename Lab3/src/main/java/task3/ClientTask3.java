package task3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ClientTask3 extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage)
    {
        BorderPane pane_main = new BorderPane();
        Scene scene = new Scene(pane_main,600,600);


        //using WebView to load the main jsp page
        WebView viewer = new WebView();
        viewer.getEngine().load("http://localhost:8080/Lab3-1.0-SNAPSHOT");

        pane_main.setCenter(viewer);

        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
