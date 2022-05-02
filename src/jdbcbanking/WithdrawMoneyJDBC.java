package jdbcbanking;

import java.sql.SQLException;
import java.util.Scanner;

public class WithdrawMoneyJDBC extends IConnectImplJDBC {

	public WithdrawMoneyJDBC() {
		super("kosmo", "1234");
	}

	@Override
	public void execute() {
		try {

			Scanner scan = new Scanner(System.in);

			System.out.print("계좌번호:");
			String searchNumber = scan.next(); // 계좌번호 입력
			System.out.print("금액:");
			int money = scan.nextInt(); // 출금할 급액입력

			if (money < 0) {
				System.out.println("출금액으로 음수는 불가능합니다.");
				return;
			} else if (!(money % 1000 == 0)) {
				System.out.println("출금은 1000원 단위로만 가능합니다");
				return;
			}

			String sql1 = "SELECT * FROM account " + "WHERE accountNumber like '%'||?||'%'";

			psmt = con.prepareStatement(sql1);
			psmt.setString(1, searchNumber);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String accountNumber = rs.getString(1);
				int balance = rs.getInt(3);

				String sql = "UPDATE account " + " SET balance=? " + " WHERE accountNumber=?";

				if (balance >= money) {
					balance -= money;
					System.out.println("계좌에서 " + money + "원이 출금되었습니다.");
				} else {
					System.out.println("잔고부족합니다. 금액전체를 출금할까요? (YES / NO)");
					String yesOrno = scan.next();
					System.out.println(yesOrno.toUpperCase());
					if (yesOrno.toUpperCase().equals("YES")) {
						System.out.println("계좌에서 " + balance + "원이 출금되었습니다.");
						balance = 0;
					} else if (yesOrno.toUpperCase().equals("NO")) {
						System.out.println("출금이 취소되었습니다.");
					} else {
						System.out.println("잘못입력하셨습니다.");
						return;
					}
				}

				psmt = con.prepareStatement(sql);
				psmt.setString(2, accountNumber);
				psmt.setInt(1, balance);

				int affected = psmt.executeUpdate();
				System.out.println(affected + "행이 업데이트 되었습니다.");
			}
		} catch (SQLException e) {
			System.out.println("같은 계좌번호가 이미 존재합니다.");
			e.printStackTrace();
		} finally {
			close();
		}
	}
}
