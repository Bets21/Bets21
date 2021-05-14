package domain;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Person") 
@Entity
public class Admin extends Person implements Serializable{

	public Admin(String name, String surname, String pUsername, String pPassword) {
		super(name,surname,pUsername,pPassword);
		
	} 
	public Admin() {
		super();
	}

}
