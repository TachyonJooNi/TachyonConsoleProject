package shopping;

import java.sql.SQLException;
import java.sql.Types;

/*
▶ 상품수정 및 삭제
프로시저 작성후 CallableStatement객체를 사용하여 호출하도록 한다. 
상품수정
프로시저명 : ShopUpdateGoods
In파라미터 : 상품명, 가격, 제품코드, 수정할 상품의 일련번호
Out파라미터 : 레코드 수정 결과(1 혹은 0)
클래스명 : UpdateShop
*/
public class UpdateShop extends IConnectImpl{

	public UpdateShop() {
		super("kosmo", "1234");
	}
	
	@Override
	public void execute() {
		try {
			csmt = con.prepareCall("{call ShopUpdateGoods(?,?,?,?,?)}");
			
			csmt.setString(1, scanValue("수정할 상품의 일련번호"));
			csmt.setString(2, scanValue("상품명"));
			csmt.setString(3, scanValue("가격"));
			csmt.setString(4, scanValue("제품코드"));
			
			csmt.registerOutParameter(5, Types.NUMERIC);
			csmt.execute();
			
			int outParamResult = csmt.getInt(5);
			switch(outParamResult) {
			case 1:
				System.out.println("일련번호가 수정되었습니다.");
				break;
			case 0:
				System.out.println("정보가 일치하지않습니다.");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	public static void main(String[] args) {
		new UpdateShop().execute();
	}
}
