package jdbcbanking;

import java.sql.SQLException;

public class UpdateSQL2 extends IConnectImpl2 {
	
	public UpdateSQL2() {
		super("kosmo", "1234");
	}
	
	@Override
	public void execute() {
		/*
		수정, 삭제시에는 주로 PK로 지정된 컬럼을 조건으로 사용한다.
		중복되지 않고, null값을 가질수 없으므로 가장 안전한 값이라고 볼수있다.
		*/
		String sql = "UPDATE account "
				+ " SET accountNumber=?, name=?, balance=?, interestRate=?, credit=? "
				+ " WHERE accountNumber=?";
		try {
			psmt = con.prepareStatement(sql);
			/*
			사용자 입력시 exit를 입력할때까지는 계속 실행되는 구조를 가지고있다.
			prepared객체는 재사용이 가능한 동적쿼리문을 실행하는 구조이므로 이런
			형태의 프로그래밍이 가능하다.
			*/
			while(true) {
				//인파라미터 설정시 해당 인덱스만 맞으면 순서는 상관없다.
				psmt.setString(1, scanValue("수정할 계좌번호"));
				psmt.setString(2, scanValue("이름"));
				psmt.setString(3, scanValue("잔고"));
				psmt.setString(4, scanValue("이자율"));
				psmt.setString(5, scanValue("신용등급"));
				
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
	public static void main(String[] args) {
		new UpdateSQL2().execute();
	}
}
