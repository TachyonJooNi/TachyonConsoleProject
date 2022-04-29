package banking;

/*
계좌정보를 표현한 클래스로 NormalAccount, HighCreditAccount의 부모클래스가 된다. 
*/
public abstract class Account {

	String accountNumber; //계좌번호
	String name; //이름
	int balance; //잔고
	
	//생성자
	public Account(String accountNumber, String name, int balance) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
	}
	
	void showAccInfo() {
		System.out.println("---------------");
		System.out.println("계좌번호>"+ accountNumber);
		System.out.println("고객이름>"+ name);
		System.out.println("잔고:"+ balance);
	}
	
	

}
