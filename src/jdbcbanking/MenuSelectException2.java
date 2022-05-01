package jdbcbanking;
/*
개발자가 직접 정의한 예외처리 클래스
*/
public class MenuSelectException2 extends Exception {
	
	public MenuSelectException2() {
		super("숫자를 잘못입력하셨습니다.");
	};
	
}
