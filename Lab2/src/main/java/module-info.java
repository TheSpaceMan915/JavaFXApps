module Lab2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.xml.bind;
    requires java.desktop;
    requires javafx.web;

    opens task3 to javafx.fxml;
    exports task3;

    opens task5 to javafx.fxml;
    exports task5;

    opens task5.classes_xml.currency_one_day to jakarta.xml.bind;
    exports task5.classes_xml.currency_one_day;

    opens task5.classes_xml.currency_period to jakarta.xml.bind;
    exports task5.classes_xml.currency_period;

    opens task5.classes_xml.metals_period to jakarta.xml.bind;
    exports task5.classes_xml.metals_period;
}