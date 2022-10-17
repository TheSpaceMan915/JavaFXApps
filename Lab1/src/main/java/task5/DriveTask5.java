package task5;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class DriveTask5 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static final TableView<ProgrammingBook> m_table_books = new TableView<>();

    private static ObservableList<ProgrammingBook> m_list_books = null;

    @Override
    public void start(Stage primaryStage)
    {
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane,700,700);


        ProgrammingBook book1 = new ProgrammingBook("C","Dennis Ritchie",1972);
        ProgrammingBook book2 = new ProgrammingBook("C++","Bjarne Stroustrup",1983);
        ProgrammingBook book3 = new ProgrammingBook("Python","Guido van Rossum",1991);
        ProgrammingBook book4 = new ProgrammingBook("Java","James Gosling",1995);
        ProgrammingBook book5 = new ProgrammingBook("JavaScript","Brendan Eich",1995);
        ProgrammingBook book6 = new ProgrammingBook("C#","Anders Hejlsberg",2001);
        ProgrammingBook book7 = new ProgrammingBook("Scala","Martin Odersky",2003);

        m_list_books = FXCollections.observableArrayList(book1,book2,book3,book4,book5,book6,book7);
        m_table_books.setItems(m_list_books);
        m_table_books.setTableMenuButtonVisible(true);

        TableColumn<ProgrammingBook,String> col1 = new TableColumn<>("Language");
        TableColumn<ProgrammingBook,String> col2 = new TableColumn<>("Author");
        TableColumn<ProgrammingBook,Integer> col3 = new TableColumn<>("Year");

        col1.setCellValueFactory(x -> x.getValue().languageProperty());
        col2.setCellValueFactory(x -> x.getValue().authorProperty());
        col3.setCellValueFactory(x -> x.getValue().yearProperty().asObject());

        m_table_books.setEditable(true);
        col1.setCellFactory(TextFieldTableCell.forTableColumn());
        col2.setCellFactory(TextFieldTableCell.forTableColumn());
        col3.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        m_table_books.getColumns().addAll(col1,col2,col3);

        //setting up add new items panel
        GridPane gridPane = new GridPane();

        Button button_add = new Button("Add");
        Button button_hide = new Button("Hide");

        ObservableList<String> list_column_names = FXCollections.observableArrayList(col1.getText(),col2.getText(),col3.getText());
        ComboBox<String> box_column_names = new ComboBox<>(list_column_names);
        box_column_names.setValue(col3.getText());

        Label label_language = new Label("Language");
        Label label_author = new Label("Author");
        Label label_year = new Label("Year");

        TextField field_language = new TextField();
        field_language.setPrefColumnCount(7);
        TextField field_author = new TextField();
        field_author.setPrefColumnCount(7);
        TextField field_year = new TextField();
        field_year.setPrefColumnCount(5);

        //adding ActionListeners for the buttons
        button_add.setOnAction( e ->
        {
            String language = field_language.getText();
            String author = field_author.getText();
            int year = Integer.parseInt(field_year.getText());

            m_list_books.add(new ProgrammingBook(language,author,year));
        });

        button_hide.setOnAction(e ->
        {
            String choice = box_column_names.getValue();
            String column_name = null;

            for (TableColumn<ProgrammingBook,?> column : m_table_books.getColumns())
            {
                column_name = column.getText();
                if (choice.equals(column_name))
                {
                    if (column.isVisible())
                    { column.setVisible(false); }
                    else
                    { column.setVisible(true); }
                    break;
                }
            }
        });

        gridPane.add(label_language,0,0,1,1);
        gridPane.add(field_language,0,1,1,1);
        gridPane.add(label_author,1,0,1,1);
        gridPane.add(field_author,1,1,1,1);
        gridPane.add(label_year,2,0,1,1);
        gridPane.add(field_year,2,1,1,1);
        gridPane.add(button_add,3,1,1,1);
        gridPane.add(box_column_names,4,0,1,1);
        gridPane.add(button_hide,4,1,1,1);

        gridPane.setHgap(30);
        gridPane.setPadding(new Insets(10,20,10,20));

        borderPane.setCenter(m_table_books);
        borderPane.setBottom(gridPane);


        primaryStage.setTitle("Table");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
