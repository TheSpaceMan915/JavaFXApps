package task5.classes_xml.metals_period;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Metall")
public class MetalCurs
{
    @XmlElement(name = "Record")
    private final List<PreciousMetal> m_list_metalls = new ArrayList<>();


    public MetalCurs() {}

    public List<PreciousMetal> getListMetalls() { return m_list_metalls; }
}
