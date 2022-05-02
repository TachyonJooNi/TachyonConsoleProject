package jdbcbanking;

import java.sql.SQLException;

public class UpdateSQLJDBC extends IConnectImplJDBC {
	
	public UpdateSQLJDBC() {
		super("kosmo", "1234");
	}
	
	@Override
	public void execute() {
		String sql = "UPDATE account "
				+ " SET balance=? "
				+ " WHERE accountNumber=?";
		try {
			psmt = con.prepareStatement(sql);
			while(true) {
				psmt.setString(2, scanValue("수정할 계좌번호"));
				psmt.setString(1, scanValue("잔고"));
				
				int affected = psmt.executeUpdate();
				System.out.println(affected +"행이 업데이트 되었습니다.");
			}
		}
		catch(SQLException e) {
			System.out.println("같은 계좌번호가 이미 존재합니다.");
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
//	public static void main(String[] args) {
//		new UpdateSQL2().execute();
//	}
}
