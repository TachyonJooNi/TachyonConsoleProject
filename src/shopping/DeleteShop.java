package shopping;

import java.sql.SQLException;
import java.sql.Types;

/*
상품삭제
프로시저명 : ShopDeleteGoods
In파라미터 : 삭제할 상품의 일련번호
Out파라미터 : 레코드 삭제 결과(1 혹은 0)
클래스명 : DeleteShop
*/
public class DeleteShop extends IConnectImpl{

	public DeleteShop() {
		super("kosmo", "1234");
	}
	
	@Override
	public void execute() {
		try {
			csmt = con.prepareCall("{call ShopDeleteGoods(?,?)}");
			
			csmt.setString(1, scanValue("삭제할 상품의 일련번호"));
			csmt.registerOutParameter(2, Types.NUMERIC);
			csmt.execute();
			
			int outParamResult = csmt.getInt(2);
			switch(outParamResult) {
			case 1:
				System.out.println("해당 상품이 삭제되었습니다.");
				break;
			case 0:
				System.out.println("일련번호를 찾을 수 없습니다.");
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
		new DeleteShop().execute();
	}
}
