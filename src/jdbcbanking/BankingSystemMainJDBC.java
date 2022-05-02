package jdbcbanking;

import java.util.Scanner;


/*
main 메서드를 포함한 클래스. 프로그램은 여기서 실행한다. 
*/
public class BankingSystemMainJDBC implements ICustomDefineJDBC {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		AccountManagerJDBC handler = new AccountManagerJDBC();
		
		while (true) {

			handler.showMenu();

			try {
			int choice = scan.nextInt();
				switch (choice) {
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
					return;
				default: // 다른 숫자들어오면 지정한 사용자예외발생
					MenuSelectExceptionJDBC ex = new MenuSelectExceptionJDBC();
					throw ex;
				}//// switch 끝
			}
			catch(MenuSelectExceptionJDBC e) { // 사용자 예외는 숫자문제임을 알려줌
				System.out.println("숫자를 잘못입력하셨습니다.");
			}
			catch(Exception e) { // nextInt()에 숫자 이외의 것이 들어오면 일반예외발생
				System.out.println("문자를 입력하셨습니다.1111");
				e.printStackTrace();
				scan.nextLine(); // while문의 무한루프를 막기위해 엔터를 먹어주기위한 용도
			}
		} //// while 끝
	}

}
