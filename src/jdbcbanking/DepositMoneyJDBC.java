package jdbcbanking;

import java.sql.SQLException;
import java.util.Scanner;

public class DepositMoneyJDBC extends IConnectImplJDBC implements ICustomDefineJDBC {
	public DepositMoneyJDBC() {
		super("kosmo", "1234");
	}

	@Override
	public void execute() {
		try {
			Scanner scan = new Scanner(System.in);

			System.out.print("계좌번호:");
			String searchNumber = scan.next(); // 계좌번호 입력
			System.out.print("금액:");
			int money = scan.nextInt(); // 입금할 급액입력
			if (money < 0) { // 음수로 잘못 입력시 문제발생을 알리고 입금하기로 돌아감.
				System.out.println("음수로 잘못 입력하셨습니다.");
				return;
			} else if (!(money % 500 == 0)) { // 500단위가 아닐시 문제발생을 알리고 입금하기로 돌아감.
				System.out.println("입금은 500원 단위로만 가능합니다.");
				return;
			}

			String sql1 = "SELECT * FROM account "
					+ "WHERE accountNumber like '%'||?||'%'";

			psmt = con.prepareStatement(sql1);
			psmt.setString(1, searchNumber);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String accountNumber = rs.getString(1);
				String balance = rs.getString(3);
				String interestRate = rs.getString(4);
				String credit = rs.getString(5);

				double douBalance = Double.parseDouble(balance);
				double douInterestRate = Double.parseDouble(interestRate);
				int depositMoney = 0;
				double creditRate = 0;
				switch (credit) {
				case "A":
					creditRate = A_INTEREST;
					break;
				case "B":
					creditRate = B_INTEREST;
					break;
				case "C":
					creditRate = C_INTEREST;
					break;
				}
				if (credit.equals(null)) {
					depositMoney = (int) (douBalance * (1 + douInterestRate / 100) + money);
				} else {
					depositMoney = (int) (douBalance * (1 + creditRate + douInterestRate / 100) + money);
				}

				String sql2 = "UPDATE account "
								+ " SET balance=? "
								+ " WHERE accountNumber=?";
				psmt = con.prepareStatement(sql2);
				psmt.setString(2, accountNumber);
				psmt.setInt(1, depositMoney);

				int affected = psmt.executeUpdate();
				System.out.println(affected + "행이 업데이트 되었습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
}
