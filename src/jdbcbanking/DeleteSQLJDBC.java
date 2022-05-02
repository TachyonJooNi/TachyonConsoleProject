package jdbcbanking;


public class DeleteSQLJDBC extends IConnectImplJDBC {

	public DeleteSQLJDBC() {
		super("kosmo", "1234");
	}
	
	@Override
	public void execute() {
		try {
			//1.쿼리문준비
			String query = "DELETE FROM account WHERE accountNumber=?";
			//2.쿼리문을 인수로 prepared객체 생성
			psmt = con.prepareStatement(query);
			//3.인파라미터 설정
			psmt.setString(1, scanValue("삭제할계좌번호"));
			//4.쿼리실행 및 결과출력
			System.out.println(psmt.executeUpdate()
					+"행이 삭제되었습니다.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	public static void main(String[] args) {
		new DeleteSQLJDBC().execute();
	}
}