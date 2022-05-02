package jdbcbanking;

import java.util.HashSet;
import java.util.Scanner;

/*
컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다. 
*/
public class AccountManagerJDBC extends IConnectImplJDBC {

	private HashSet<AccountJDBC> myAccount;
	
	
//	private Account[] myAccount; // 계좌길이 초기 50설정할꺼임
//	private int numOfmyAccount; // 인덱스를 위한 변수

	// 생성자
	public AccountManagerJDBC() {
		myAccount = new HashSet<AccountJDBC>();
		
//		myAccount = new Account[num];
//		numOfmyAccount = 0;
	}

	// 메뉴출력 메서드
	void showMenu() {
		System.out.println("==============Menu===============");
		System.out.printf("|   1.계좌개설       2.입  금      |%n");
		System.out.printf("|   3.출   금       4.계좌정보출력  |%n");
		System.out.printf("|   5.프로그램종료                 |%n");
		System.out.println("=================================");
		System.out.print("메뉴선택>>>");
	}

	// 계좌개설 메서드
	void makeAccount() {
		
		// 정보입력을 위한 객체생성 및 변수생성
		try {
			Scanner scan = new Scanner(System.in);

			
			// 기본정보 입력
			System.out.println("===========신규계좌개설=============");
			System.out.printf("|           -계좌선택-            |%n");
			System.out.printf("|    1.보통계좌      2.신용신뢰계좌  |%n");
			System.out.println("=================================");
			System.out.print("선택:");
			int choice = scan.nextInt();
			if(!(choice==1 || choice==2)) {
				System.out.println("잘못입력하셨습니다.");
				return;
			}
			new MakeAccountJDBC().execute(choice);
			
		}
		catch(Exception e) { // 예외발생
			System.out.println("잘못입력하셨습니다.");
			makeAccount();
		}
	}

	// 입 금
	void depositMoney() {
		try {
//			boolean isFind = false;
			
			System.out.println("=============입  금===============");
			System.out.printf("| -계좌번호와 입글할 금액을 입력하세요- |%n");
			System.out.printf("| -입금은 500원 단위로만 가능합니다-   |%n");
			System.out.println("=================================");
			
			new DepositMoneyJDBC().execute();
			
		}
		catch(Exception e) {
			System.out.println("문자로 잘못입력하셨습니다.");
		}
	}

	// 출 금
	void withdrawMoney() {
		try {
			Scanner scan = new Scanner(System.in);
			boolean isFind = false;
			
			
			System.out.println("=============출  금===============");
			System.out.printf("| -계좌번호와 출글할 금액을 입력하세요- |%n");
			System.out.printf("| -출금은 1000원 단위로만 가능합니다-  |%n");
			System.out.println("=================================");
			System.out.println("계좌번호:");
			String searchNumber = scan.next();
			System.out.println("금액:");
			int money = scan.nextInt();
			
			if(money<0) {
				System.out.println("출금액으로 음수는 불가능합니다.");
				return;
			}
			else if(!(money%1000==0)) {
				System.out.println("출금은 1000원 단위로만 가능합니다");
				return;
			}
			
			for(AccountJDBC acc : myAccount) {
//			for(int i=0 ; i<numOfmyAccount ; i++) {
				if(searchNumber.equals(acc.getAccountNumber())) {
					if (acc.getBalance() >= money) {
						acc.setBalance(acc.getBalance()-money);
//				if(searchNumber.equals(myAccount[i].accountNumber)) {
//					if (myAccount[i].balance >= money) {
//						myAccount[i].balance -= money;
						System.out.println("계좌에서 " + money + "원이 출금되었습니다.");
					} 
					else {
						System.out.println("잔고부족합니다. 금액전체를 출금할까요? (YES / NO)");
						String yesOrno = scan.next();
						System.out.println(yesOrno.toUpperCase());
						if(yesOrno.toUpperCase().equals("YES")) {
							System.out.println("계좌에서 " + acc.getBalance() + "원이 출금되었습니다.");
							acc.setBalance(0);
//							System.out.println("계좌에서 " + myAccount[i].balance + "원이 출금되었습니다.");
//							myAccount[i].balance = 0;
							return;
						}
						else if(yesOrno.toUpperCase().equals("NO")) {
							System.out.println("출금이 취소되었습니다.");
							return;
						}
						else {
							System.out.println("잘못입력하셨습니다.");
							return;
						}
					}
					isFind = true;
					break;
				}
			}
			if(isFind==false) {
				System.out.println("***계좌번호가 없습니다.***");
			}
			System.out.println();
		}
		catch(Exception e) {
			System.out.println("잘못입력하셨습니다.");
		}
	}
	
	// 전체계좌정보출력
	public void showAccInfo() {
		System.out.println("===========계좌정보출력=============");
		for(AccountJDBC acc : myAccount) {
			acc.showAccInfo();
//		for(int i=0 ; i<numOfmyAccount ; i++) {
//			myAccount[i].showAccInfo();
		}
		System.out.println("=====전체 계좌정보가 출력되었습니다=====");
		System.out.println();
	}
	
//	public void saveAccountInfo() {
//			try {
//				ObjectOutput out = new ObjectOutputStream
//						(new FileOutputStream("src/banking/AccountInfo.obj"));
//				
//				for(Account2 acc : myAccount) {
//					out.writeObject(acc);
//				}
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//				System.out.println("계좌 정보 직렬화시 예외발생");
//			}
//		}
//	
//	public void readAccountInfo() {
//		try {
//			ObjectInputStream in = new ObjectInputStream
//					(new FileInputStream("src/banking/AccountInfo.obj"));
//			while(true) {
//				Account2 acc = (Account2)in.readObject();
//				myAccount.add(acc);
//				if(acc==null) break;
//			}
//		}
//		catch(Exception e) {
//			System.out.println("더 이상 읽을 객체가 없습니다.");
//		}
//		System.out.println("계좌정보가 복원되었습니다.");
//	}
}
