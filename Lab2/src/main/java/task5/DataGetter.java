package task5;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import task5.classes_xml.currency_one_day.ValCurs;
import task5.classes_xml.currency_one_day.Valute;
import task5.classes_xml.currency_period.ValCursPeriod;
import task5.classes_xml.currency_period.ValutePeriod;
import task5.classes_xml.metals_period.MetalCurs;
import task5.classes_xml.metals_period.PreciousMetal;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;


public class DataGetter
{
    public static List<Valute> getCurrencyOneDay(String date)
    {

        ValCurs temp = null;

        //setting url
        String str_url = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date;
        try
        {
            //unmarshalling the data
            URL url = new URL(str_url);
            JAXBContext context = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            temp = (ValCurs) unmarshaller.unmarshal(url);

        }
        catch (IOException exep)
        { exep.printStackTrace(); }
        catch (JAXBException exep)
        {
            exep.printStackTrace();
            JOptionPane.showMessageDialog(null,"JAXBE error","Error",JOptionPane.ERROR_MESSAGE);
        }

        //checking if temp != null. If temp = null, the exception will be thrown
        assert temp != null;
        return temp.getListValutes();
    }


    public static List<ValutePeriod> getCurrencyPeriod(String date1, String date2, String id)
    {
        ValCursPeriod temp = null;

        //setting url
        //String  str = "http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=02/03/2001&date_req2=14/03/2001&VAL_NM_RQ=R01235";
        String str_url = "http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=" + date1 + "&date_req2=" + date2 + "&VAL_NM_RQ=" + id;
        try
        {
            //unmarshalling the data
            URL url = new URL(str_url);

            //CHANGE THE CONTEXT FOR EVERY NEW DATA TYPE!!!
            JAXBContext context = JAXBContext.newInstance(ValCursPeriod.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            temp = (ValCursPeriod) unmarshaller.unmarshal(url);

        }
        catch (IOException exep)
        { exep.printStackTrace(); }
        catch (JAXBException exep)
        {
            exep.printStackTrace();
            JOptionPane.showMessageDialog(null,"JAXBE error","Error",JOptionPane.ERROR_MESSAGE);
        }

        assert temp != null;
        return temp.getListValutes();
    }


    public static List<PreciousMetal> getMetallsPeriod(String date1, String date2)
    {
        MetalCurs temp = null;

        //setting url
        //String  str = "http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=02/03/2001&date_req2=14/03/2001&VAL_NM_RQ=R01235";
        String str_url = "http://www.cbr.ru/scripts/xml_metall.asp?date_req1=" + date1 + "&date_req2=" + date2;
        try
        {
            //unmarshalling the data
            URL url = new URL(str_url);

            //CHANGE THE CONTEXT FOR EVERY NEW DATA TYPE!!!
            JAXBContext context = JAXBContext.newInstance(MetalCurs.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            temp = (MetalCurs) unmarshaller.unmarshal(url);

        }
        catch (IOException exep)
        { exep.printStackTrace(); }
        catch (JAXBException exep)
        {
            exep.printStackTrace();
            JOptionPane.showMessageDialog(null,"JAXBE error","Error",JOptionPane.ERROR_MESSAGE);
        }

        assert temp != null;
        return temp.getListMetalls();
    }

    public static void main(String[] args)
    {
        getCurrencyOneDay("06/03/2021");
    }
}
