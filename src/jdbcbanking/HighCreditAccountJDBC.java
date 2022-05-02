package jdbcbanking;

import java.util.Objects;


/*
Account의 자식클래스로 신용도가 높은 고객에게 개설이 허용되며 높은 이율의 계좌이다.
생성자를 통해서 이율정보(이자비율의정보)를 초기화 할수있도록 정의한다.
*/
public class HighCreditAccountJDBC extends AccountJDBC implements ICustomDefineJDBC {
	
	private double interestRate; // 이자율
	private double creditRate; //신용이자율
	private String credit; //신용등급
	
	//생성자
	public HighCreditAccountJDBC(String accountNumber, String name, int balance,
			double interestRate, String credit) {
		super(accountNumber, name, balance);
		this.interestRate = interestRate;
		
		this.credit = credit.toUpperCase(); //선택된 신용등급에 따라 추가이자율 설정
		switch (credit.toUpperCase()) {
		case "A" :
			this.creditRate = A_INTEREST;
			break;
		case "B" :
			this.creditRate = B_INTEREST;
			break;
		case "C" :
			this.creditRate = C_INTEREST;
			break;
		}
	}

	@Override
	void showAccInfo() {
		super.showAccInfo();
		System.out.println("|  기본이자 : "+(int)interestRate+"\t\t\t|");
		System.out.println("|  신용등급 : "+credit+"\t\t\t|");
		System.out.println("---------------------------------");
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getCreditRate() {
		return creditRate;
	}

	public void setCreditRate(double creditRate) {
		this.creditRate = creditRate;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(super.getAccountNumber());
	}
	@Override
	public boolean equals(Object obj) {
		HighCreditAccountJDBC other = (HighCreditAccountJDBC)obj;
		if (other.getAccountNumber().equals(super.getAccountNumber())) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
