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
public class Option implements Serializable {

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer id;
	private String description;
	private double odd;
	@XmlIDREF
	private Question question;
	private String result;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Bet> bets=new Vector<Bet>();
	public Option() {
		super();
	}
	public Option(String description, double odd, Question q) {
		this.description = description;
		this.odd = odd;
		this.question=q;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getOdd() {
		return odd;
	}

	public void setOdd(double odd) {
		this.odd = odd;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public String toString() {
		return (this.description+": "+this.odd);
	}
	
	public Vector<Bet> getBets(){
		return this.bets;
	}
	
	public void setBets(Vector<Bet> bets) {
		this.bets = bets;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public void addBet(Bet bet) {
		if (bets ==  null) {
			bets = new Vector<Bet>();
		}
		this.bets.add(bet);
	}
}
