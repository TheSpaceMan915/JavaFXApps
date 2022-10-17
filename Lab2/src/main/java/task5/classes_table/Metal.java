package task5.classes_table;

import javafx.beans.property.SimpleStringProperty;

public class Metal
{
    private final SimpleStringProperty m_type;
    private final SimpleStringProperty m_date;
    private final SimpleStringProperty m_buy;
    private final SimpleStringProperty m_sell;


    public Metal(String type, String date, String buy, String sell)
    {
        m_type = new SimpleStringProperty(type);
        m_date = new SimpleStringProperty(date);
        m_buy = new SimpleStringProperty(buy);
        m_sell = new SimpleStringProperty(sell);
    }


    public String getSell() { return m_sell.get(); }

    public SimpleStringProperty getSellProperty() { return m_sell; }

    public void setSell(String m_sell) { this.m_sell.set(m_sell); }

    public String getBuy() { return m_buy.get(); }

    public SimpleStringProperty getBuyProperty() { return m_buy; }

    public void setBuy(String m_buy) { this.m_buy.set(m_buy); }

    public String getDate() { return m_date.get(); }

    public SimpleStringProperty getDateProperty() { return m_date; }

    public void setDate(String m_date) { this.m_date.set(m_date); }

    public String getType() { return m_type.get(); }

    public SimpleStringProperty getTypeProperty() { return m_type; }

    public void setType(String m_type) { this.m_type.set(m_type); }
}
