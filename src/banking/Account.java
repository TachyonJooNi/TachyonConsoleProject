package banking;

import java.io.Serializable;
import java.util.Objects;

/*
계좌정보를 표현한 클래스로 NormalAccount, HighCreditAccount의 부모클래스가 된다. 
*/
public abstract class Account implements Serializable {

	private String accountNumber; //계좌번호
	private String name; //이름
	private int balance; //잔고
	
	//생성자
	public Account(String accountNumber, String name, int balance) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
	}
	
	void showAccInfo() { // 정보출력
		System.out.println("|\t\t\t\t|");
		System.out.println("|  계좌번호 : "+ accountNumber+"\t\t\t|");
		System.out.println("|  고객이름 : "+ name+"\t\t\t|");
		System.out.println("|  잔   고 : "+ balance+"\t\t|");
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber);
	}

	@Override
	public boolean equals(Object obj) {
		Account other = (Account) obj;
		if (Objects.equals(accountNumber, other.accountNumber))
			return true;
		else
			return false;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
