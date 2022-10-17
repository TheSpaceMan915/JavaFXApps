package task5.classes_table;

import javafx.beans.property.SimpleStringProperty;

public class ValPeriod
{
    private final SimpleStringProperty m_date;
    private final SimpleStringProperty m_value;


    public ValPeriod(String date, String value)
    {
        m_date = new SimpleStringProperty(date);
        m_value = new SimpleStringProperty(value);
    }


    public String getValue() { return m_value.get(); }

    public SimpleStringProperty getValueProperty() { return m_value; }

    public void setValue(String m_value) { this.m_value.set(m_value); }

    public String getDate() { return m_date.get(); }

    public SimpleStringProperty getDateProperty() { return m_date; }

    public void setDate(String m_date) { this.m_date.set(m_date); }
}
