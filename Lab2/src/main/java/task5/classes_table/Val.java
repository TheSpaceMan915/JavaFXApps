package task5.classes_table;

import javafx.beans.property.SimpleStringProperty;

public class Val
{
    private final SimpleStringProperty m_name;
    private final SimpleStringProperty m_value;


    public Val(String name, String value)
    {
        m_name = new SimpleStringProperty(name);
        m_value = new SimpleStringProperty(value);
    }


    public String getName() { return m_name.get(); }

    public void setName(String m_name) { this.m_name.set(m_name); }

    public SimpleStringProperty getNameProperty() { return m_name; }

    public String getValue() { return m_value.get(); }

    public void setValue(String m_value) { this.m_value.set(m_value); }

    public SimpleStringProperty getValueProperty() { return m_value; }

}
