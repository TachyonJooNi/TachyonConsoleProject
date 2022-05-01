package jdbcbanking;

import java.sql.SQLException;
import java.util.Scanner;

public class SelectSQL2 extends IConnectImpl2 {
	public SelectSQL2() {
		super("kosmo", "1234");
	}

	@Override
	public void execute() {
		try {
			while (true) {
				/*
				prepared 객체를 통해 인파라미터를 성정하면 문자인 경우 자동으로
				싱글쿼테이션을 추가하게 되므로 || 연산자를 사용해야 한다.
				*/
				String sql = "SELECT * FROM account "
//					+ "WHERE name like '%?%'"; //에러발생 : 부적합한 열 인덱스
					+ "WHERE accountNumber like '%'||?||'%'";
				
				psmt = con.prepareStatement(sql);
				psmt.setString(1, scanValue("찾는계좌번호"));
				rs = psmt.executeQuery();
				while(rs.next()) {
					String accountNumber = rs.getString(1);
					String name = rs.getString(2);
					String balance = rs.getString(3);
					String interestRate = rs.getString(4);
					String credit = rs.getString(5);
					//날짜를 문자형으로 추출하면 시간까지 출력되므로 날짜부분만 잘라서 출려
					String regidate = rs.getString(6).substring(0, 10);
					
					System.out.printf("%s %s %s %s %s %s\n",
							accountNumber, name, balance, interestRate, credit, regidate);
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			close();
		}
	}

	public static void main(String[] args) {
		new SelectSQL2().execute();
	}
}
