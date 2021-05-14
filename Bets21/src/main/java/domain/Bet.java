package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Bet implements Serializable{

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer betNumber;
	private double betAmount;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Option> optionList;
	@XmlIDREF
	private User user;
	private int wins;
	private String result;
	
	public Bet() {
		this.betAmount = 0;
		this.optionList = new Vector<Option>();
		this.user = null;
	}
	
	public Bet(Vector<Option> optionList, Double amount, User user) {
		this.betAmount = amount;
		this.optionList = optionList;
		this.user = user;
	}
	
	public Vector<Option> getOptionList() {
		return this.optionList;
	}
	
	public void addOption(Option option) {
		this.optionList.add(option);
	}
	
	public String toString() {
		return this.user.getName();
	}
	
	public void setResult(String result) {
		this.result = result;
	}

	public double getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(double betAmount) {
		this.betAmount = betAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public String getResult() {
		return result;
	}

	public void setOptionList(Vector<Option> optionList) {
		this.optionList = optionList;
	}
	
	public void addWin() {
		this.wins++;
	}
}
