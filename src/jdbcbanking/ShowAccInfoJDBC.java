package jdbcbanking;

import java.sql.SQLException;
import java.util.Scanner;

public class ShowAccInfoJDBC extends IConnectImplJDBC implements ICustomDefineJDBC {

	public ShowAccInfoJDBC() {
		super("kosmo", "1234");
	}

	@Override
	public void execute() {
		try {

			String sql1 = "SELECT * FROM account";

			psmt = con.prepareStatement(sql1);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				String accountNumber = rs.getString(1);
				String name = rs.getString(2);
				String balance = rs.getString(3);
				String interestRate = rs.getString(4);
				String credit = rs.getString(5);
				
				System.out.println("계좌번호 : " +accountNumber);
				System.out.println("이   름 : " +name);
				System.out.println("잔   고 : " +balance);
				System.out.println("이 자 율 : " +interestRate);
				System.out.println("신용등급 : " +credit);
				System.out.println("---------------------------------");
				
			}
			int affected = psmt.executeUpdate();
			System.out.println(affected + "행이 업데이트 되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
}
