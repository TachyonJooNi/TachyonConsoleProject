package banking;
/*
개발자가 직접 정의한 예외처리 클래스
*/
public class MenuSelectException extends Exception {
	
	public MenuSelectException() {
		super("숫자를 잘못입력하셨습니다.");
	};
	
}
