package jdbcbanking;

//부모 생성자만 호출하면 DB연결과 자원해제는 완료된다.
public class MakeAccountJDBC extends IConnectImplJDBC {

	public MakeAccountJDBC() {
		super(ORACLE_DRIVER, "kosmo", "1234");
	}
	
	public void execute(int choice) {
		try {
			String query = "INSERT INTO account VALUES (?, ?, ?, ?, ?, ?)";
			
			psmt = con.prepareStatement(query);
			
//			while(true) {
				psmt.setString(1, scanValue("계좌번호"));
				psmt.setString(2, scanValue("이름"));
				psmt.setString(3, scanValue("잔고"));
				psmt.setString(4, scanValue("이자율"));
				if(choice==1) {
					psmt.setString(5, "(null)");
				}
				else {
					psmt.setString(5, scanValue("신용등급"));
				}
				
				java.util.Date utilDate = new java.util.Date();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				psmt.setDate(6, sqlDate);
				
				int affected = psmt.executeUpdate();
				System.out.println(affected +"행이 입력되었습니다.");
//			}
			
			
			
//			Scanner scan = new Scanner(System.in);
//			System.out.print("계좌번호:");
//			String accountNumber = scan.nextLine();
//			System.out.print("이름:");
//			String name = scan.nextLine();
//			System.out.print("잔고:");
//			double balance = scan.nextDouble();
//			System.out.print("이자율:");
//			double interestRate = scan.nextDouble();
//			System.out.print("신용등급:");
//			String credit = scan.nextLine();
				
//			psmt.setString(1, accountNumber);
//			psmt.setString(2, name);
//			psmt.setDouble(3, balance);
//			psmt.setDouble(4, interestRate);
//			psmt.setString(5, credit);
//			psmt.setString(4, "2022-04-30");
				
//			java.util.Date utilDate = new java.util.Date();
//			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//			psmt.setDate(6, sqlDate);
				
//			int affected = psmt.executeUpdate();
//			System.out.println(affected +"행이 입력되었습니다.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
//	public static void main(String[] args) {
//		new InsertSQL2().execute();
//	}
}
