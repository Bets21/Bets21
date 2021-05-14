package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Person") 
public class Employee extends Person implements Serializable{


public Employee(String pname, String psurname, String pUsername, String pPassword) {
	super(pname,psurname,pUsername,pPassword);

}
public Employee() {
	super();
}



}
