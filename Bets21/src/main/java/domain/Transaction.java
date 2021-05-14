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
public class Transaction implements Serializable {
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer TransactionNumber;
	@XmlIDREF
	private User u;
	private double amount;
	
	public Transaction() {
		super();
	}
	public Transaction(User u, double amount) {
		super();
		this.u = u;
		this.amount = amount;
	}
	
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
}