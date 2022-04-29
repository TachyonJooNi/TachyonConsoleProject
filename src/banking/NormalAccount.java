package banking;

/*
Account의 자식클래스로 보통예금계좌를 의미한다.
생성자를 통해서 이율정보(이자비율의정보)를 초기화 할수있도록 정의한다.
*/
public class NormalAccount extends Account implements ICustomDefine {

	double interestRate; //기본이자율

	public NormalAccount(String accountNumber, String name, int balance, 
			double interestRate) {
		super(accountNumber, name, balance);
		this.interestRate = interestRate;
	}

	@Override
	void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자>"+(int)interestRate+"%");
		System.out.println("---------------");
		System.out.println();

	}

	
	
	
	
}
