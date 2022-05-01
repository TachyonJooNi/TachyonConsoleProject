package jdbcbanking;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Scanner;

/*
컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다. 
*/
public class AccountManager2{

	private HashSet<Account2> myAccount;
	
//	private Account[] myAccount; // 계좌길이 초기 50설정할꺼임
//	private int numOfmyAccount; // 인덱스를 위한 변수

	// 생성자
	public AccountManager2() {
		myAccount = new HashSet<Account2>();
		
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
			String iAccountNumber, iName, iCreditRating;
			int iBalance, iInterestRate;
			
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
			
			System.out.print("계좌번호:");
			iAccountNumber = scan.next();
			
			System.out.print("이름:");
			iName = scan.next();
			
			System.out.print("잔고:");
			iBalance = scan.nextInt();
			
			System.out.print("기본이자%(정수형태로입력):");
			iInterestRate = scan.nextInt();
			
			if (choice == 1) {
				
				myAccount.add(new NormalAccount2(iAccountNumber, iName, 
						iBalance, iInterestRate));
				
//				myAccount[numOfmyAccount++] = new NormalAccount(iAccountNumber, iName, 
//						iBalance, iInterestRate);
				
			} else if (choice == 2) {
				
				System.out.println("신용등급(A,B,C등급):");
				iCreditRating = scan.next();
				
				myAccount.add(new HighCreditAccount2(iAccountNumber, iName, 
						iBalance, iInterestRate, iCreditRating));
				
//				myAccount[numOfmyAccount++] = new HighCreditAccount(iAccountNumber, iName, 
//						iBalance, iInterestRate, iCreditRating);
			}
			System.out.println("계좌계설이 완료되었습니다.");
		}
		catch(Exception e) { // 예외발생
			System.out.println("잘못입력하셨습니다.");
			makeAccount();
		}
	}

	// 입 금
	void depositMoney() {
		try {
			Scanner scan = new Scanner(System.in);
			boolean isFind = false;
			
			System.out.println("=============입  금===============");
			System.out.printf("| -계좌번호와 입글할 금액을 입력하세요- |%n");
			System.out.printf("| -입금은 500원 단위로만 가능합니다-   |%n");
			System.out.println("=================================");
			System.out.println("계좌번호:");
			String searchNumber = scan.next(); 	//계좌번호 입력
			System.out.println("금액:");
			int money = scan.nextInt();			//입금할 급액입력
			if(money<0) { // 음수로 잘못 입력시 문제발생을 알리고 입금하기로 돌아감.
				System.out.println("음수로 잘못 입력하셨습니다.");
				depositMoney();
			}
			else if(!(money%500==0)) { // 500단위가 아닐시 문제발생을 알리고 입금하기로 돌아감.
				System.out.println("입금은 500원 단위로만 가능합니다.");
				depositMoney();
			}
			
			for(Account2 acc : myAccount) {			//(컬렉션)
//			for(int i=0 ; i<numOfmyAccount ; i++) { //(배열)
				//입력받은 계좌번호와 같다면
				if(searchNumber.equals(acc.getAccountNumber())) {  //(컬렉션)
//				if(searchNumber.equals(myAccount[i].accountNumber)) { (배열)
					
					Account2 basic = acc;//기본계좌와 상속관계를 확인(컬렉션)
//					Account basic = myAccount[i];//기본계좌와 상속관계를 확인(배열)
					
					if(basic instanceof NormalAccount2) {//보통통장이면 기본이자율만 적용
						NormalAccount2 noramlAcc = (NormalAccount2)basic;
						noramlAcc.setBalance((int)(noramlAcc.getBalance()+
								(money + (noramlAcc.getBalance()*
										(noramlAcc.getInterestRate()/100)))));
					}
					
					else if(basic instanceof HighCreditAccount2) {//우대통장이면 추가적용
						HighCreditAccount2 highAcc = (HighCreditAccount2)basic;
						highAcc.setBalance((int)(highAcc.getBalance()+(money+
								(highAcc.getBalance()*
										(highAcc.getInterestRate()/100+
												highAcc.getCreditRate())))));
					}
					
					System.out.println("입금이 완료되었습니다.");
					System.out.println();
					isFind = true;
					return;
				}
			}
			if(isFind==false) {
				System.out.println("***계좌번호가 없습니다.***");
			}
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
			
			for(Account2 acc : myAccount) {
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
		for(Account2 acc : myAccount) {
			acc.showAccInfo();
//		for(int i=0 ; i<numOfmyAccount ; i++) {
//			myAccount[i].showAccInfo();
		}
		System.out.println("=====전체 계좌정보가 출력되었습니다=====");
		System.out.println();
	}
	
	public void saveAccountInfo() {
			try {
				ObjectOutput out = new ObjectOutputStream
						(new FileOutputStream("src/banking/AccountInfo.obj"));
				
				for(Account2 acc : myAccount) {
					out.writeObject(acc);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("계좌 정보 직렬화시 예외발생");
			}
		}
	
	public void readAccountInfo() {
		try {
			ObjectInputStream in = new ObjectInputStream
					(new FileInputStream("src/banking/AccountInfo.obj"));
			while(true) {
				Account2 acc = (Account2)in.readObject();
				myAccount.add(acc);
				if(acc==null) break;
			}
		}
		catch(Exception e) {
			System.out.println("더 이상 읽을 객체가 없습니다.");
		}
		System.out.println("계좌정보가 복원되었습니다.");
	}
}
