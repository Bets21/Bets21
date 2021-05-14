package domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class IntegerAdapter extends XmlAdapter<String, Integer>  implements Serializable {
    
    public Integer unmarshal(String s) {
        return Integer.parseInt(s);
    }
 
    public String marshal(Integer number) {
        if (number == null) return "";
         
        return number.toString();
    }
    public IntegerAdapter() {
    	super();
    }
}