package jdbcbanking;
/*
개발자가 직접 정의한 예외처리 클래스
*/
public class MenuSelectExceptionJDBC extends Exception {
	
	public MenuSelectExceptionJDBC() {
		super("숫자를 잘못입력하셨습니다.");
	};
	
}
