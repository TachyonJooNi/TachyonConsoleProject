package banking;

import java.util.Scanner;


/*
main 메서드를 포함한 클래스. 프로그램은 여기서 실행한다. 
*/
public class BankingSystemMain implements ICustomDefine {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		AccountManager handler = new AccountManager(50);
		
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
					break;
				default:
					MenuSelectException ex = new MenuSelectException();
					throw ex;
				}//// switch 끝
			}
			catch(MenuSelectException e) {
				System.out.println("숫자를 잘못입력하셨습니다.");
			}
			catch(Exception e) {
				System.out.println("문자를 입력하셨습니다.");
				scan.nextLine();
			}
		} //// while 끝
	}

}
