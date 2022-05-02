package shopping;

/*
▶ 상품입력
PreparedStatement객체를 사용하여 작성한다.
클래스명 : InsertShop
상품명, 상품가격, 상품코드를 scanValue() 메소드로 입력받아 사용한다. 
입력이 완료되면 입력된 행의 갯수를 반환하여 출력한다. 
*/
public class InsertShop extends IConnectImpl {
	
	public InsertShop() {
		super(ORACLE_DRIVER, "kosmo", "1234");
	}
	
	@Override
	public void execute() {
		try {
			String query = "INSERT INTO sh_goods "
					+ " (goods_name, goods_price, g_idx) VALUES (?, ?, ?)";
			psmt = con.prepareStatement(query);
			
			while(true) {
				psmt.setString(1, scanValue("상품명"));
				psmt.setString(2, scanValue("상품가격"));
				psmt.setString(3, scanValue("상품코드"));
				
				int affected = psmt.executeUpdate();
				System.out.println(affected +"행이 입력되었습니다.");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

	public static void main(String[] args) {
		new InsertShop().execute();
	}
}