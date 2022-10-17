package tasks;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Hashtable;
import java.util.Map;
import java.util.function.UnaryOperator;

public class Task4 extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    //add thickness set up

    private static LineChart<Number,Number> m_chart = null;
    private static ObservableList<XYChart.Series<Number,Number>> m_list_series = null;
    private static final Map<String,UnaryOperator<Double>> m_hashtable = new Hashtable<>();
    private static TextField m_field_start = null;
    private static TextField m_field_end = null;
    private static TextField m_field_step = null;
    private static TextField m_field_width = null;
    private static ComboBox<String> m_box_functions_str = null;

    public static void addSeries(double x_start, double x_end, double step_iter, UnaryOperator<Double> function, String name_series, int width)
    {
        double x = x_start;
        double res = 0.0;
        XYChart.Series<Number,Number> series = new XYChart.Series<>();
        series.setName(name_series);

        while (x <= x_end)
        {
            res = function.apply(x);
            series.getData().add(new XYChart.Data<>(x,res));
            x += step_iter;
        }

        m_list_series.add(series);
        m_chart.lookup(".default-color"+m_list_series.size()+".chart-series-line").setStyle("-fx-stroke-width: "+width+"px;");
    }

    @Override
    public void start(Stage primaryStage)
    {
        BorderPane border_pain = new BorderPane();
        final int width_scene = 800;
        final int height_scene = 800;
        Scene scene = new Scene(border_pain,width_scene,height_scene);

        //defining the axes
        final NumberAxis m_axis_x = new NumberAxis();
        final NumberAxis m_axis_y = new NumberAxis();
        m_chart = new LineChart<>(m_axis_x,m_axis_y);
        m_list_series = m_chart.getData();

        m_axis_x.setLabel("Value x");
        m_axis_y.setLabel("Value y");
        m_chart.setTitle("Functions");

        //defining the functions
        UnaryOperator<Double> func1 = x -> Math.log(x) * 5 + x * 5 + 10;
        UnaryOperator<Double> func2 = x -> Math.cos(x) + 1;
        UnaryOperator<Double> func3 = x -> Math.sin(x) - 2 + x * 2;
        String str_func1 = "log(x)*5+x*5+10              ";
        String str_func2 = "cos(x)+1";
        String str_func3 = "sin(x)-2+x*2";

        m_hashtable.put(str_func1,func1);
        m_hashtable.put(str_func2,func2);
        m_hashtable.put(str_func3,func3);

        //creating the bottom pane
        GridPane grid_pane = new GridPane();
        grid_pane.setHgap(10);

        m_field_width = new TextField("5");
        m_field_width.setPrefColumnCount(7);
        m_field_start = new TextField("1");
        m_field_start.setPrefColumnCount(7);
        m_field_end = new TextField("20");
        m_field_end.setPrefColumnCount(7);
        m_field_step = new TextField("2");
        m_field_step.setPrefColumnCount(7);

        Label label_thickness = new Label("Width");
        Label label_start = new Label("Start");
        Label label_end = new Label("End");
        Label label_step = new Label("Step");

        Button button = new Button("Draw");
        RadioButton button_show = new RadioButton("show");
        button_show.setSelected(true);
        RadioButton button_hide = new RadioButton("hide");

        ObservableList<String> list_str_functions = FXCollections.observableArrayList(str_func1,str_func2,str_func3);
        m_box_functions_str = new ComboBox<>(list_str_functions);
        m_box_functions_str.setValue(str_func1);


        //setting up ActionListeners
        button_show.setOnAction(e ->
        {
            if (button_show.isSelected())
            {
                button_hide.setSelected(false);

                String box_value = m_box_functions_str.getValue();
                UnaryOperator<Double> temp = m_hashtable.get(box_value);
                double start = Double.parseDouble(m_field_start.getText());
                double end = Double.parseDouble(m_field_end.getText());
                double step = Double.parseDouble(m_field_step.getText());
                int width_line = Integer.parseInt(m_field_width.getText());

                addSeries(start,end,step,temp,box_value,width_line);
            }
        });

        button_hide.setOnAction(e ->
        {
            if (button_hide.isSelected())
            {
                button_show.setSelected(false);

                String box_value = m_box_functions_str.getValue();
                for (int i = 0; i < m_list_series.size(); i++)
                {
                    String name_series = m_list_series.get(i).getName();
                    if (name_series.equals(box_value))
                    {
                        m_list_series.remove(i);
                        break;
                    }
                }
            }
        });

        button.setOnAction(e ->
        {
            String box_value = m_box_functions_str.getValue();
            UnaryOperator<Double> temp = m_hashtable.get(box_value);
            double start = Double.parseDouble(m_field_start.getText());
            double end = Double.parseDouble(m_field_end.getText());
            double step = Double.parseDouble(m_field_step.getText());
            int width_line = Integer.parseInt(m_field_width.getText());

            for (int j = 0; j < m_list_series.size(); j++)
            {
                String name = m_list_series.get(j).getName();

                if (name.equals(box_value))
                {
                    m_list_series.remove(j);
                    addSeries(start,end,step,temp,box_value,width_line);
                    return;
                }
            }
            addSeries(start,end,step,temp,box_value,width_line);
        });

        grid_pane.add(m_box_functions_str,0,0,1,1);
        grid_pane.add(label_thickness,1,0,1,1);
        grid_pane.add(m_field_width,1,1,1,1);
        grid_pane.add(label_start,2,0,1,1);
        grid_pane.add(m_field_start,2,1,1,1);
        grid_pane.add(label_end,3,0,1,1);
        grid_pane.add(m_field_end,3,1,1,1);
        grid_pane.add(label_step,4,0,1,1);
        grid_pane.add(m_field_step,4,1,1,1);
        grid_pane.add(button_show,5,0,1,1);
        grid_pane.add(button_hide,5,1,1,1);
        grid_pane.add(button,7,0,1,1);


        //setting up the border pane
        border_pain.setCenter(m_chart);
        border_pain.setBottom(grid_pane);


        primaryStage.setTitle("Line Chart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
