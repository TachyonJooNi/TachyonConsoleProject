package banking;

import java.util.Scanner;


/*
main 메서드를 포함한 클래스. 프로그램은 여기서 실행한다. 
*/
public class BankingSystemMain implements ICustomDefine {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		AccountManager handler = new AccountManager();
		handler.readAccountInfo(); // 저장데이터 불러오기
		
		while (true) {

			try {
			handler.showMenu();

			int choice1 = scan.nextInt();
			int choice2 = 0;
				switch (choice1) {
				case MAKE: //계좌개설
					handler.makeAccount();
					break;
				case DEPOSIT: //입금
					handler.depositMoney();
					break;
				case WITHDRAW: //출금
					handler.withdrawMoney();
					break;
				case INQUIRE: //계좌정보출력
					handler.showAccInfo();
					break;
				case EXIT: //프로그램종료
					System.out.println("프로그램종료");
					handler.saveAccountInfo();; // 데이터 저장
					return;
				case AUTOSAVE : //자동저장
					System.out.println("1.자동저장시작  2.자동저장중지");
					choice2 = scan.nextInt();
					if(choice2==1) {
//						handler.setDaemon(true);
						handler.start();
						break;
					}
					else if(choice2==2) {
						handler.interrupt();
						break;
					}
				default: // 다른 숫자들어오면 지정한 사용자예외발생
					MenuSelectException ex = new MenuSelectException();
					throw ex;
				}//// switch 끝
			}
			catch(MenuSelectException e) { // 사용자 예외는 숫자문제임을 알려줌
				System.out.println("숫자를 잘못입력하셨습니다.");
			}
			catch(Exception e) { // nextInt()에 숫자 이외의 것이 들어오면 일반예외발생
				System.out.println("문자를 입력하셨습니다.");
				scan.nextLine(); // while문의 무한루프를 막기위해 엔터를 먹어주기위한 용도
			}
		} //// while 끝
	}

}
