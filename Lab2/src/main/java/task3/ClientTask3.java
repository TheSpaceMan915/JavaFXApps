package task3;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ClientTask3 extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage)
    {
        BorderPane pane_main = new BorderPane();
        Scene scene = new Scene(pane_main,600,600);

        /*
        TextField field_login = new TextField();
        PasswordField field_password = new PasswordField();

        field_login.setPrefColumnCount(10);
        field_password.setPrefColumnCount(10);
        field_login.setText("Eeeerooock");
        field_password.setText("H3PO");

        Label label_login = new Label("Enter your login");
        Label label_password = new Label("Enter your password");
        Label label_request = new Label("Choose the type of your request");

        ObservableList<String> list_requests = FXCollections.observableArrayList("GET","POST");
        ComboBox<String> box_requests = new ComboBox<>(list_requests);
        box_requests.setValue("POST");

        Button button_return = new Button("Go back");
        Button button_enter = new Button("Enter");


        //adding ActionListeners


        //setting up the GridPane
        GridPane gridpane = new GridPane();
        */

        //using WebView to load my html page
        WebView viewer = new WebView();
        viewer.getEngine().load("http://localhost:8080/Java2EE-1.0-SNAPSHOT");

        pane_main.setCenter(viewer);

        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
