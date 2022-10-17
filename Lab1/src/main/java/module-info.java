module Lab1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires javafx.swing;
    requires javafx.media;


    exports task5;
    opens task5 to javafx.fxml;
    exports task7;
    opens task7 to javafx.fxml;
    exports tasks;
    opens tasks to javafx.fxml;
}