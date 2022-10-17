package task5;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import task5.classes_table.Metal;
import task5.classes_table.Val;
import task5.classes_table.ValPeriod;
import task5.classes_xml.currency_one_day.Valute;
import task5.classes_xml.currency_period.ValutePeriod;
import task5.classes_xml.metals_period.PreciousMetal;

import java.util.ArrayList;
import java.util.List;

public class ClientTask5 extends Application
{
    private final BorderPane m_pane_main = new BorderPane();
    private final TextField m_field_data1 = new TextField("01/03/2001");
    private final TextField m_field_data2 = new TextField("30/03/2001");
    private final TextField m_field_id = new TextField();


    public static void main(String[] args) { launch(args); }

    public void buildTable(String str_choice)
    {
        switch (str_choice)
        {
            case "Currency one day" ->
            {
                //getting date from the field
                String input_date = m_field_data1.getText();

                //getting the list of currency from DataGetter
                List<Valute> list_temp = DataGetter.getCurrencyOneDay(input_date);
                List<Val> list_main = new ArrayList<>();

                for (Valute temp : list_temp)
                { list_main.add(new Val(temp.getName(),temp.getValue())); }


                ObservableList<Val> list_currency = FXCollections.observableArrayList(list_main);
                TableView<Val> table_valute_one_day = new TableView<>(list_currency);
                table_valute_one_day.setTableMenuButtonVisible(true);
                //table_valute_one_day.setEditable(true);

                TableColumn<Val,String> col1 = new TableColumn<>("Name");
                TableColumn<Val,String> col2 = new TableColumn<>("Value");

                col1.setCellValueFactory(valute -> valute.getValue().getNameProperty());
                col2.setCellValueFactory(valute -> valute.getValue().getValueProperty());

                table_valute_one_day.getColumns().addAll(col1,col2);
                m_pane_main.setCenter(table_valute_one_day);
            }

            case "Currency period" ->
            {
                String input_date1 = m_field_data1.getText();
                String input_date2 = m_field_data2.getText();
                String input_id = m_field_id.getText();

                List<ValutePeriod> list_temp = DataGetter.getCurrencyPeriod(input_date1,input_date2,input_id);
                List<ValPeriod> list_main = new ArrayList<>();

                for (ValutePeriod temp : list_temp)
                { list_main.add(new ValPeriod(temp.getDate(),temp.getValue())); }


                ObservableList<ValPeriod> list_currency = FXCollections.observableArrayList(list_main);
                TableView<ValPeriod> table_valute_one_day = new TableView<>(list_currency);
                table_valute_one_day.setTableMenuButtonVisible(true);
                //table_valute_one_day.setEditable(true);

                TableColumn<ValPeriod,String> col1 = new TableColumn<>("Date");
                TableColumn<ValPeriod,String> col2 = new TableColumn<>("Value");

                col1.setCellValueFactory(valute -> valute.getValue().getDateProperty());
                col2.setCellValueFactory(valute -> valute.getValue().getValueProperty());

                table_valute_one_day.getColumns().addAll(col1,col2);
                m_pane_main.setCenter(table_valute_one_day);
            }

            case "Metal period" ->
            {
                String input_date1 = m_field_data1.getText();
                String input_date2 = m_field_data2.getText();

                List<PreciousMetal> list_temp = DataGetter.getMetallsPeriod(input_date1,input_date2);
                List<Metal> list_main = new ArrayList<>();

                for (PreciousMetal temp : list_temp)
                { list_main.add(new Metal(temp.toString(),temp.getDate(),temp.getBuyPrice(),temp.getSellPrice())); }


                ObservableList<Metal> list_metals = FXCollections.observableArrayList(list_main);
                TableView<Metal> table_metals = new TableView<>(list_metals);
                table_metals.setTableMenuButtonVisible(true);
                //table_metals.setEditable(true);

                TableColumn<Metal,String> col1 = new TableColumn<>("Type");
                TableColumn<Metal,String> col2 = new TableColumn<>("Date");
                TableColumn<Metal,String> col3 = new TableColumn<>("Buy");
                TableColumn<Metal,String> col4 = new TableColumn<>("Sell");

                col1.setCellValueFactory(valute -> valute.getValue().getTypeProperty());
                col2.setCellValueFactory(valute -> valute.getValue().getDateProperty());
                col3.setCellValueFactory(valute -> valute.getValue().getBuyProperty());
                col4.setCellValueFactory(valute -> valute.getValue().getSellProperty());

                table_metals.getColumns().addAll(col1,col2,col3,col4);
                m_pane_main.setCenter(table_metals);
            }
        }
    }


    public int extractDate(String date)
    {
        String[] arr_str = date.split("\\.");
        int res = Integer.parseInt(arr_str[0]);

        return res;
    }

    public double extractValue(String value)
    {
        String[] arr_str = value.split(",");
        String temp = arr_str[0] + "." + arr_str[1];
        double res = Double.parseDouble(temp);

        return res;
    }

    public void buildChart(String str_choice)
    {
        switch (str_choice)
        {
            case "Currency period" ->
            {
                String input_date1 = m_field_data1.getText();
                String input_date2 = m_field_data2.getText();
                String input_id = m_field_id.getText();


                //defining the axes
                final NumberAxis axis_x = new NumberAxis() ;
                final NumberAxis axis_y = new NumberAxis();
                final LineChart<Number,Number> chart = new LineChart<>(axis_x,axis_y);

                //preparing the data for the chart
                List<ValutePeriod> list_temp = DataGetter.getCurrencyPeriod(input_date1,input_date2,input_id);
                XYChart.Series<Number,Number> series = new XYChart.Series<>();
                series.setName("Price");

                for (ValutePeriod temp : list_temp)
                {
                    int date = extractDate(temp.getDate());
                    double value = extractValue(temp.getValue());
                    series.getData().add(new XYChart.Data<>(date,value));
                }

                //putting data in the chart
                chart.getData().add(series);
                m_pane_main.setCenter(chart);
            }

            case "Metal period" ->
            {
                String input_date1 = m_field_data1.getText();
                String input_date2 = m_field_data2.getText();

                //defining the axes
                final NumberAxis axis_x = new NumberAxis() ;
                final NumberAxis axis_y = new NumberAxis();
                final LineChart<Number,Number> chart = new LineChart<>(axis_x,axis_y);

                //preparing the data for the chart
                List<PreciousMetal> list_temp = DataGetter.getMetallsPeriod(input_date1,input_date2);
                XYChart.Series<Number,Number> series_gold = new XYChart.Series<>();
                XYChart.Series<Number,Number> series_silver = new XYChart.Series<>();
                XYChart.Series<Number,Number> series_platinum = new XYChart.Series<>();
                XYChart.Series<Number,Number> series_palladium = new XYChart.Series<>();

                series_gold.setName("Gold");
                series_silver.setName("Silver");
                series_platinum.setName("Platinum");
                series_palladium.setName("Palladium");


                String type = null;
                int date = 0;
                double value = 0;
                for (PreciousMetal temp : list_temp)
                {
                    //getting the data from a metal object
                    type = temp.toString();
                    date = extractDate(temp.getDate());
                    value = extractValue(temp.getBuyPrice());

                    switch (type)
                    {
                        case "Gold" -> series_gold.getData().add(new XYChart.Data<>(date,value));

                        case "Silver" -> series_silver.getData().add(new XYChart.Data<>(date,value));

                        case "Platinum" -> series_platinum.getData().add(new XYChart.Data<>(date,value));

                        case "Palladium" -> series_palladium.getData().add(new XYChart.Data<>(date,value));
                    }
                }

                //putting data in the chart
                chart.getData().addAll(series_gold,series_silver,series_platinum,series_palladium);
                m_pane_main.setCenter(chart);
            }
        }
    }


    @Override
    public void start(Stage primaryStage)
    {
        Scene scene = new Scene(m_pane_main,700,700);

        //setting up the GridPane
        GridPane gridpane = new GridPane();
        Label label_data1 = new Label("Date1");
        Label label_data2 = new Label("Date2");
        Label label_id = new Label("Id");
        Label label_box = new Label("Type of info");

        m_field_data1.setPrefColumnCount(6);
        m_field_data2.setPrefColumnCount(6);
        m_field_id.setPrefColumnCount(6);

        ObservableList<String> list_types = FXCollections.observableArrayList("Currency one day","Currency period", "Metal period");
        ComboBox<String> box_types = new ComboBox<>(list_types);
        box_types.setValue("Metal period");

        Button button_send = new Button("Show table");
        button_send.setPrefWidth(80);

        Button button_chart = new Button("Show chart");
        button_chart.setPrefWidth(80);

        button_send.setOnAction(e ->
        {
            String choice = box_types.getValue();
            buildTable(choice);
        });

        button_chart.setOnAction(e ->
        {
            String choice = box_types.getValue();
            buildChart(choice);
        });

        gridpane.add(label_data1,0,0,1,1);
        gridpane.add(m_field_data1,0,1,1,1);
        gridpane.add(label_data2,1,0,2,1);
        gridpane.add(m_field_data2,1,1,1,1);
        gridpane.add(label_id,2,0,1,1);
        gridpane.add(m_field_id,2,1,1,1);
        gridpane.add(label_box,3,0,1,1);
        gridpane.add(box_types,3,1,1,1);
        gridpane.add(button_send,4,1,1,1);
        gridpane.add(button_chart,5,1,1,1);
        gridpane.setHgap(25);

        m_pane_main.setBottom(gridpane);

        primaryStage.setTitle("Chart and Table");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

