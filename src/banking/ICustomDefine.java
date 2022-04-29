package banking;

/*
interface로 생성한다. 
메뉴선택과 이자율 지정을 위한 인터페이스형 상수를 정의한다. 
메뉴 : 계좌개설, 입금, 출금, 전체계좌정보출력, 종료를 1~5까지로 지정한다.
이자율 : 고객의 신용등급을 A, B, C로 나눠서 7%, 4%, 2%로 지정한다.
*/
interface ICustomDefine {

	int MAKE=1; // 계좌개설
	int DEPOSIT=2; // 입금
	int WITHDRAW=3; // 출금
	int INQUIRE=4; // 전체계좌정보출력
	int EXIT=5; // 프로그램종료
	
	double AINTERESTRATE = 0.07; //신용등급 A의 이자율
	double BINTERESTRATE = 0.04; //신용등급 B의 이자율
	double CINTERESTRATE = 0.02; //신용등급 C의 이자율

}
