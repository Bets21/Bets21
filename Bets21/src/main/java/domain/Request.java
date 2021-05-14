package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Request implements Serializable {
	
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	@XmlID
	private Integer RequestNumber;
	@XmlIDREF
	private User fromUser;
	@XmlIDREF
	private User toUser;
	
	public Request() {
		super();
	}
	public Request(User fromUser, User toUser) {
		super();
		this.fromUser = fromUser;
		this.toUser = toUser;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	@Override
	public String toString() {
		return("NUM: "+this.RequestNumber+" FROM: "+this.getFromUser()+" TO: "+this.getToUser());
	}
	
}
