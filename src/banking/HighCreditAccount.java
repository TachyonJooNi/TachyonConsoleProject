package banking;

/*
Account의 자식클래스로 신용도가 높은 고객에게 개설이 허용되며 높은 이율의 계좌이다.
생성자를 통해서 이율정보(이자비율의정보)를 초기화 할수있도록 정의한다.
*/
public class HighCreditAccount extends Account implements ICustomDefine {

	double interestRate; // 이자율
	double creditRate; //신용이자율
	String credit; //신용등급
	
	//생성자
	public HighCreditAccount(String accountNumber, String name, int balance,
			double interestRate, String credit) {
		super(accountNumber, name, balance);
		this.interestRate = interestRate;
		
		this.credit = credit.toUpperCase();
		switch (credit.toUpperCase()) {
		case "A" :
			this.creditRate = AINTERESTRATE;
			break;
		case "B" :
			this.creditRate = BINTERESTRATE;
			break;
		case "C" :
			this.creditRate = CINTERESTRATE;
			break;
		}
	}

	@Override
	void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자>"+(int)interestRate+"%");
		System.out.println("신용등급>"+credit);
		System.out.println("---------------");
		System.out.println();
	}

	
	
}
