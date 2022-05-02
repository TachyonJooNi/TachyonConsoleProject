package banking;

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
public class AccountManager extends Thread {

	private HashSet<Account> myAccount;
	
	// 생성자
	public AccountManager() {
		myAccount = new HashSet<Account>();
		
	}
	
	// 메뉴출력 메서드
	void showMenu() {
		System.out.println("==============Menu===============");
		System.out.println("|   1.계좌개설       2.입  금      |");
		System.out.println("|   3.출   금       4.계좌정보출력  |");
		System.out.println("|   5.프로그램종료    6.자동저장     |");
		System.out.println("=================================");
		System.out.print("메뉴선택>>>");
	}

	
	// 계좌개설 메서드
	void makeAccount() {
		
		// 정보입력을 위한 객체생성 및 변수생성
		try {
			Scanner scan = new Scanner(System.in);
			String iAccountNumber, iName, iCreditRating;
			int iBalance, iInterestRate, overlap;
			
			// 기본정보 입력
			System.out.println("===========신규계좌개설=============");
			System.out.println("|           -계좌선택-            |");
			System.out.println("|    1.보통계좌      2.신용신뢰계좌  |");
			System.out.println("=================================");
			System.out.print("선택:");
			int choice = scan.nextInt();
			if(!(choice==1 || choice==2)) { // 1,2외의 숫자입력 사용자 정의 예외로 걸러서 던짐
				MenuSelectException ex = new MenuSelectException();
				throw ex;
			}
			
			System.out.print("계좌번호:");
			iAccountNumber = scan.next();
			System.out.print("이름:");
			iName = scan.next();
			System.out.print("잔고:");
			iBalance = scan.nextInt();
			System.out.print("기본이자%(정수형태로입력):");
			iInterestRate = scan.nextInt();
			
			for(Account acc : myAccount) { // 이미 존재하는 계좌번호를 입력시 조치사항 안내.
				if(iAccountNumber.equals(acc.getAccountNumber())) {
					System.out.println("입력하신 계좌번호가 이미 존재합니다.");
					System.out.println("1.초기화면으로 돌아가기");
					System.out.println("2.기존계좌 해지");
					overlap = scan.nextInt();
					if(overlap==1) {
						System.out.println("초기화면으로 돌아갑니다.");
						return;
					}
					else if (overlap==2) {
						myAccount.remove(acc);
						System.out.println("기존계좌를 해지하셨습니다.");
						return;
					}
				}
			}
			
			if (choice == 1) { 			//1번 보통계좌면 위에서 입력받은 값들로 바로 추가
				myAccount.add(new NormalAccount(iAccountNumber, iName, 
						iBalance, iInterestRate));
				
			} else if (choice == 2) { 	// 2번 우대계좌라면 신용등급 추가로 입력받아서 추가
				System.out.println("신용등급(A,B,C등급):");
				iCreditRating = scan.next();
				myAccount.add(new HighCreditAccount(iAccountNumber, iName, 
						iBalance, iInterestRate, iCreditRating));
			}
			System.out.println("계좌계설이 완료되었습니다.");
		}
		catch(MenuSelectException e) { // 던져진 예외 받아서 처리
			System.out.println("잘못된 숫자를 입력하셨습니다.");
			return;
		}
		catch(Exception e) { // 예외발생
			System.out.println("잘못입력하셨습니다.");
			return;
		}
	}

	
	// 입 금
	void depositMoney() {
		try {
			Scanner scan = new Scanner(System.in);
			boolean isFind = false;
			
			System.out.println("=============입  금===============");
			System.out.println("| -계좌번호와 입글할 금액을 입력하세요- |");
			System.out.println("| -입금은 500원 단위로만 가능합니다-   |");
			System.out.println("=================================");
			System.out.println("계좌번호:");
			String searchNumber = scan.next(); 	//계좌번호 입력
			System.out.println("금액:");
			int money = scan.nextInt();			//입금할 급액입력
			if(money<0) { // 음수로 잘못 입력시 문제발생을 알리고 돌아감.
				System.out.println("음수로 잘못 입력하셨습니다.");
				return;
			}
			else if(!(money%500==0)) { // 500단위가 아닐시 문제발생을 알리고 돌아감.
				System.out.println("입금은 500원 단위로만 가능합니다.");
				return;
			}
			
			for(Account acc : myAccount) {
				if(searchNumber.equals(acc.getAccountNumber())) { 
					Account basic = acc;
					if(basic instanceof NormalAccount) {//보통통장이면 기본이자율만 적용 : 잔고*(1+기본이자)+입금액 
						NormalAccount noramlAcc = (NormalAccount)basic;
						noramlAcc.setBalance((int)(noramlAcc.getBalance()
								*(1+noramlAcc.getInterestRate()/100)+money));
					}
					
					else if(basic instanceof HighCreditAccount) {//우대통장이면 추가적용 : 잔고*(1+기본이자+추가이자)+입금액
						HighCreditAccount highAcc = (HighCreditAccount)basic;
						highAcc.setBalance((int)(highAcc.getBalance()
								*(1+highAcc.getInterestRate()/100+highAcc.getCreditRate())+money));
					}
					System.out.println("입금이 완료되었습니다.");
					System.out.println();
					isFind = true; // 입금완료시 for문 탈출
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
			System.out.println("| -계좌번호와 출글할 금액을 입력하세요- |");
			System.out.println("| -출금은 1000원 단위로만 가능합니다-  |");
			System.out.println("=================================");
			System.out.println("계좌번호:");
			String searchNumber = scan.next();
			System.out.println("금액:");
			int money = scan.nextInt();
			
			if(money<0) { // 출금액 음수입력 방지
				System.out.println("출금액으로 음수는 불가능합니다.");
				return;
			}
			else if(!(money%1000==0)) { // 1000원 단위 체크
				System.out.println("출금은 1000원 단위로만 가능합니다");
				return;
			}
			
			for(Account acc : myAccount) {	// 출금액이 잔고보다 큰지를 확인하고
				if(searchNumber.equals(acc.getAccountNumber())) {
					
					if (acc.getBalance() >= money) {
						acc.setBalance(acc.getBalance()-money);
						System.out.println("계좌에서 " + money + "원이 출금되었습니다.");
					} 
					else { // 출금액이 더 크다면 추가 선택
						System.out.println("잔고부족합니다. 금액전체를 출금할까요? (YES / NO)");
						String yesOrno = scan.next();
						
						if(yesOrno.toUpperCase().equals("YES")) { // toUpperCase()로 대소문자 혼용을 방지.
							System.out.println("계좌에서 " + acc.getBalance() + "원이 출금되었습니다.");
							acc.setBalance(0); // 금액전체를 출금하겠다고 했으니 출금메세지 이후 잔고를 0으로 만듬.
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
					isFind = true; // 출금완료시 for문 탈출
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
		for(Account acc : myAccount) {
			acc.showAccInfo();
		}
		System.out.println("=====전체 계좌정보가 출력되었습니다=====");
		System.out.println();
	}
	
	//데이터 저장
	public void saveAccountInfo() {
			try {
			
			ObjectOutput out = new ObjectOutputStream
					(new FileOutputStream("src/banking/AccountInfo.obj"));
			
			for(Account acc : myAccount) { // 향상된 for문으로 하나씩 꺼내서 데이터 저장
				out.writeObject(acc);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("계좌 정보 직렬화시 예외발생");
		}
	}
	
	//데이터 불러오기
	public void readAccountInfo() {
		try {
			ObjectInputStream in = new ObjectInputStream
					(new FileInputStream("src/banking/AccountInfo.obj"));
			while(true) {
				Account acc = (Account)in.readObject();
				myAccount.add(acc);
				if(acc==null) break; // 다 불러오면 반복문 탈출
			}
		}
		catch(Exception e) {
			System.out.println("더 이상 읽을 객체가 없습니다.");
		}
		System.out.println("계좌정보가 복원되었습니다.");
	}
	
	public void run() {
		
		while(!isInterrupted()) {
			try {
				ObjectOutput out = new ObjectOutputStream
						(new FileOutputStream("src/banking/AccountInfo.obj"));
				
				for(Account acc : myAccount) { // 향상된 for문으로 하나씩 꺼내서 데이터 저장
					out.writeObject(acc);
				}
				System.out.println("자동저장완료");
				sleep(5000);
			}
			catch(InterruptedException e) {
				System.out.println("자동저장을 중지했습니다.");
				return;
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("자동저장시 예외발생");
			}
		}
	}
}
