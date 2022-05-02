package jdbcbanking;

import java.util.HashSet;
import java.util.Scanner;

/*
컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다. 
*/
public class AccountManagerJDBC extends IConnectImplJDBC {

	private HashSet<AccountJDBC> myAccount;
	

	// 생성자
	public AccountManagerJDBC() {
		myAccount = new HashSet<AccountJDBC>();
		
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
			
			System.out.println("=============출  금===============");
			System.out.printf("| -계좌번호와 출글할 금액을 입력하세요- |%n");
			System.out.printf("| -출금은 1000원 단위로만 가능합니다-  |%n");
			System.out.println("=================================");
			
			new WithdrawMoneyJDBC().execute();
			
		}
		catch(Exception e) {
			System.out.println("잘못입력하셨습니다.");
		}
	}
	
	// 전체계좌정보출력
	public void showAccInfo() {
		System.out.println("===========계좌정보출력=============");
		new ShowAccInfoJDBC().execute();
		
//		for(AccountJDBC acc : myAccount) {
//			acc.showAccInfo();
//		}
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
