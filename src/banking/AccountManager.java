package banking;

import java.util.Scanner;

/*
컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다. 
*/
public class AccountManager{

	private Account[] myAccount; // 계좌길이 초기 50설정할꺼임
	private int numOfmyAccount; // 인덱스를 위한 변수

	// 생성자
	public AccountManager(int num) {
		myAccount = new Account[num];
		numOfmyAccount = 0;
	}

	// 메뉴출력 메서드
	void showMenu() {
		System.out.println("######### Menu #########");
		System.out.print("1.계좌개설 ");
		System.out.println("2.입  금");
		System.out.print("3.출   금 ");
		System.out.println("4.계좌정보출력 ");
		System.out.println("5.프로그램종료");
		System.out.print("메뉴선택>>>");
	}

	// 계좌개설 메서드
	void makeAccount() {
		
		// 정보입력을 위한 객체생성 및 변수생성
		try {
			Scanner scan = new Scanner(System.in);
			String iAccountNumber, iName, iCreditRating;
			int iBalance, iInterestRate;
			
			System.out.println("***신규계좌개설***");
			System.out.println("---계좌선택---");
			System.out.println("1.보통계좌");
			System.out.println("2.신용신뢰계좌");
			System.out.println("선택:");
			int choice = scan.nextInt();
			// 기본정보 입력
			
			System.out.println("계좌번호:");
			iAccountNumber = scan.next();
			
			System.out.println("이름:");
			iName = scan.next();
			
			System.out.println("잔고:");
			iBalance = scan.nextInt();
			
			System.out.println("기본이자%(정수형태로입력):");
			iInterestRate = scan.nextInt();
			
			if (choice == 1) {
				
				myAccount[numOfmyAccount++] = new NormalAccount(iAccountNumber, iName, 
						iBalance, iInterestRate);
				
			} else if (choice == 2) {
				
				System.out.println("신용등급(A,B,C등급):");
				iCreditRating = scan.next();
				
				myAccount[numOfmyAccount++] = new HighCreditAccount(iAccountNumber, iName, 
						iBalance, iInterestRate, iCreditRating);
			}
			System.out.println("계좌계설이 완료되었습니다.");
		}
		catch(Exception e) {
			System.out.println("잘못입력하셨습니다.");
		}
	}

	// 입 금
	void depositMoney() {
		try {
			Scanner scan = new Scanner(System.in);
			boolean isFind = false;
			
			System.out.println("***입금***");
			System.out.println("계좌번호와 입글할 금액을 입력하세요");
			System.out.println("계좌번호:");
			String searchNumber = scan.next();
			System.out.println("금액:");
			int money = scan.nextInt();
			if(money<0) {
				System.out.println("음수로 잘못 입력하셨습니다.");
				depositMoney();
			}
			else if(!(money%500==0)) {
				System.out.println("입금은 500원 단위로만 가능합니다.");
				depositMoney();
			}
			
			for(int i=0 ; i<numOfmyAccount ; i++) {
				//입력받은 계좌번호와 같다면
				if(searchNumber.equals(myAccount[i].accountNumber)) { 
					Account basic = myAccount[i];//기본계좌와 상속관계를 확인
					if(basic instanceof NormalAccount) {//보통통장이면 기본이자율만 적용
						NormalAccount noramlAcc = (NormalAccount)basic;
						noramlAcc.balance += 
								(int)(money + (noramlAcc.balance*(noramlAcc.interestRate/100)));
					}
					else if(basic instanceof HighCreditAccount) {//우대통장이면 추가적용
						HighCreditAccount highAcc = (HighCreditAccount)basic;
						highAcc.balance += (int)(money+
								(highAcc.balance*(highAcc.interestRate/100+highAcc.creditRate)));
					}
					System.out.println("입금이 완료되었습니다.");
					System.out.println();
					isFind = true;
					return;
				}
			}
			if(isFind==false) {
				System.out.println("***계좌번호가 없습니다.***");
				System.out.println();
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
			
			System.out.println("***출금***");
			System.out.println("계좌번호와 출금할 금액을 입력하세요");
			System.out.println("계좌번호:");
			String searchNumber = scan.next();
			System.out.println("금액:");
			int money = scan.nextInt();
			if(money<0) {
				System.out.println("출금액으로 음수는 불가능합니다.");
				withdrawMoney();
			}
			else if(!(money%1000==0)) {
				System.out.println("출금은 1000원 단위로만 가능합니다");
				withdrawMoney();
			}
			
			for(int i=0 ; i<numOfmyAccount ; i++) {
				if(searchNumber.equals(myAccount[i].accountNumber)) {
					if (myAccount[i].balance >= money) {
						myAccount[i].balance -= money;
						System.out.println("계좌에서 " + money + "원이 출금되었습니다.");
					} 
					else {
						System.out.println("잔고부족합니다. 금액전체를 출금할까요? (YES / NO)");
						String yesOrno = scan.nextLine();
						if(yesOrno.toUpperCase()=="YES") {
							myAccount[i].balance = 0;
							System.out.println("계좌에서 " + myAccount[i].balance + "원이 출금되었습니다.");
						}
						else if(yesOrno.toUpperCase()=="NO") {
							System.out.println("출금이 취소되었습니다.");
							break;
						}
						else {
							System.out.println("잘못입력하셨습니다.");
							withdrawMoney();
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
		System.out.println("***계좌정보출력***");
		for(int i=0 ; i<numOfmyAccount ; i++) {
			myAccount[i].showAccInfo();
		}
		System.out.println("==전체계좌정보가 출력되었습니다==");
		System.out.println();
	}

}
