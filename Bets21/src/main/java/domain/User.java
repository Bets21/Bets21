package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity

public  class User extends Person implements Serializable {
	
	private int Adina;
	private int KontuKorrontea;
	private int KK_DataHilabetea;
	private int KK_DataUrtea;
	private int KK_CVV;
	private double UnekoSaldoa=0.0;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@XmlIDREF
	private Vector<Bet> bets = new Vector<Bet>();
	
	private String InvitedBy = null;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@XmlIDREF
	private Vector<Transaction> transactions = null;
	

	private Vector<String> invitedTo = null;
	

	private Vector<String> Requests = null;
	

	private Vector<String> YouCopy = null;
	
	private Vector<String> CopyYou = null;
public User() {
	super();
}
	public User(String pName, String pSurname, String pUsername, String pPassword, int pAdina) {
		super(pName,pSurname,pUsername,pPassword);
	
		this.Adina=pAdina;
		this.KontuKorrontea=0;
		this.KK_DataHilabetea=0;
		this.KK_DataUrtea=0;
		this.KK_CVV=0;
		this.UnekoSaldoa=0.0;
	}
	
	public User(String pName, String pSurname, String pUsername, String pPassword, int pAdina,String pinvitedBY) {
		super(pName,pSurname,pUsername,pPassword);
		this.Adina=pAdina;
		this.KontuKorrontea=0;
		this.KK_DataHilabetea=0;
		this.KK_DataUrtea=0;
		this.KK_CVV=0;
		this.UnekoSaldoa=0.0;
		this.InvitedBy=pinvitedBY;
	}
	
	public Vector<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Vector<Transaction> transactions) {
		this.transactions = transactions;
	}

	public void setBets(Vector<Bet> bets) {
		this.bets = bets;
	}

	public int getAdina() {
		return Adina;
	}
	
	public void setAdina(int adina) {
		Adina = adina;
	}
	
	public int getKontuKorrontea() {
		return KontuKorrontea;
	}
	
	public void setKontuKorrontea(int KontuKorrontea) {
		this.KontuKorrontea = KontuKorrontea;
	}
	
	public double getUnekoSaldoa() {
		return UnekoSaldoa;
	}
	
	public void setUnekoSaldoa(double unekoSaldoa) {
		UnekoSaldoa = unekoSaldoa;
	}
	
	public int getKK_DataHilabetea() {
		return KK_DataHilabetea;
	}

	public void setKK_DataHilabetea(int kK_DataHilabetea) {
		KK_DataHilabetea = kK_DataHilabetea;
	}

	public int getKK_DataUrtea() {
		return KK_DataUrtea;
	}

	public void setKK_DataUrtea(int kK_DataUrtea) {
		KK_DataUrtea = kK_DataUrtea;
	}

	public int getKK_CVV() {
		return KK_CVV;
	}

	public void setKK_CVV(int kK_CVV) {
		KK_CVV = kK_CVV;
	}
	
	public void addBet(Bet bet) {
		if (bets == null) {
			bets = new Vector<Bet>();
		}
		this.bets.add(bet);
	}
	
	public void addTransaction(Transaction t) {
		if (this.transactions == null) {
			this.transactions = new Vector<Transaction>();
		}
		this.transactions.add(t);
	}
	
	public void addInvitedTo(String it) {
		if (this.invitedTo == null) {
			this.invitedTo = new Vector<String>();
		}
		this.invitedTo.add(it);
	}
	
	public void addRequests(String it) {
		if (this.Requests == null) {
			this.Requests = new Vector<String>();
		}
		this.Requests.add(it);
	}
	
	public void removeRequests(String username) {
		this.Requests.remove(username);
	}
	
	public void addCopyYou(String it) {
		if (this.CopyYou == null) {
			this.CopyYou = new Vector<String>();
		}
		this.CopyYou.add(it);
	}
	
	public void addYouCopy(String it) {
		if (this.YouCopy == null) {
			this.YouCopy = new Vector<String>();
		}
		this.YouCopy.add(it);
	}
	
	public Vector<String> getRequests() {
		return Requests;
	}

	public void setRequests(Vector<String> requests) {
		Requests = requests;
	}

	public Vector<String> getYouCopy() {
		return YouCopy;
	}

	public void setYouCopy(Vector<String> youCopy) {
		YouCopy = youCopy;
	}

	public Vector<String> getCopyYou() {
		return CopyYou;
	}

	public void setCopyYou(Vector<String> copyYou) {
		CopyYou = copyYou;
	}

	public Vector<String> getInvitedTo() {
		return invitedTo;
	}

	public void setInvitedTo(Vector<String> invitedTo) {
		this.invitedTo = invitedTo;
	}

	public Vector<Bet> getBets(){
		return this.bets;
	}
	
	public String getInvitedBy() {
		return InvitedBy;
	}

	public void setInvitedBy(String invitedBy) {
		InvitedBy = invitedBy;
	}
	
	
	public void removeYouCopy(String username2) {
        this.YouCopy.remove(username2);
    }

    public void removeCopyYou(String username) {
        this.CopyYou.remove(username);
    }
    
	public String TOstring() {
		return(this.getName()+" "+this.getSurname()+" "+this.getUsername());
	}

	

}
