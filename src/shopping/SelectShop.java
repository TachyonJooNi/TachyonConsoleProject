package shopping;

import java.text.DecimalFormat;

/*
▶ 상품조회
Statement객체를 사용하여 작성한다.
클래스명 : SelectShop
검색할 상품명을 입력받은 후 like를 통해 해당조건에 맞는 레코드만 출력한다. 
출력항목 : 일련번호, 상품명, 가격, 등록일, 제품코드
단, 등록일은 0000-00-00 00:00 형태로 출력해야 한다.	상품가격은 세자리마다 컴마를 찍어준다.
Statement객체는 인파라미터를 통한 동적쿼리를 작성할 수 없으므로 순수 Java변수를 사용한다. 
*/
public class SelectShop extends IConnectImpl {

	DecimalFormat decFormat = new DecimalFormat("###,###");
	
	public SelectShop() {
		super(ORACLE_DRIVER, "kosmo", "1234");
	}

	@Override
	public void execute() {
		try {
			stmt = con.createStatement();
			
			String query = "SELECT * FROM sh_goods "
					+ " WHERE goods_name like '%세탁기%'";
			
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String g_idx = rs.getString(1);
				String goods_name = rs.getString(2);
				int price =  Integer.parseInt(rs.getString(3));
				String goods_price = decFormat.format(price);
//				String goods_price = String.format("%,d", price);
				String regidate = rs.getString(4).substring(0,16);
				String p_code = rs.getString(5);
				
				System.out.printf("%s %s %s %s %s \n",
						g_idx, goods_name, goods_price, regidate, p_code);
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
		new SelectShop().execute();
	}
}
