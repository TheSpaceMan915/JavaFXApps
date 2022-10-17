package task7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DriveTask7 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:///C:\\Users\\nikit\\IdeaProjects\\Programming_the_5th_semester\\src\\main\\resources\\com\\lab1\\Task7\\player.fxml"));

        Scene scene = new Scene(loader.load(), 950, 600);
        stage.setTitle("Video Player");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
