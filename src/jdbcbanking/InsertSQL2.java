package jdbcbanking;

import java.util.Scanner;



//부모 생성자만 호출하면 DB연결과 자원해제는 완료된다.
public class InsertSQL2 extends IConnectImpl2 {

	public InsertSQL2() {
		super(ORACLE_DRIVER, "kosmo", "1234");
	}
	
	@Override
	public void execute() {
		try {
			//1.쿼리문준비 : 값의 세팅이 필요한 부분을 ?(인파라미터)로 대체한다.
			String query = "INSERT INTO account VALUES (?, ?, ?, ?, ?, ?)";
			
			//2.prepared객체생성 : 생성시 준비한 쿼리문을 인수로 전달한다.
			psmt = con.prepareStatement(query);
			
			/* 연습으로 작성
			while(true) {
				psmt.setString(1, scanValue("계좌번호"));
				psmt.setString(2, scanValue("이름"));
				psmt.setString(3, scanValue("잔고"));
				psmt.setString(4, scanValue("이자율"));
				psmt.setString(5, scanValue("신용등급"));
				
				java.util.Date utilDate = new java.util.Date();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				psmt.setDate(6, sqlDate);
				
				int affected = psmt.executeUpdate();
				System.out.println(affected +"행이 입력되었습니다.");
			}
			*/
			
			//3.사용자로부터 입력할 내용을 받는다.
			Scanner scan = new Scanner(System.in);
			System.out.print("계좌번호:");
			String accountNumber = scan.nextLine();
			System.out.print("이름:");
			String name = scan.nextLine();
			System.out.print("잔고:");
			String balance = scan.nextLine();
			System.out.print("이자율:");
			String interestRate = scan.nextLine();
			System.out.print("신용등급:");
			String credit = scan.nextLine();
			/*
			4.인파라미터 설정 : ?의 순서대로 설정하고, 인덱스는 1부터 시작한다.
				자료형이
					숫자면 setInt()
					문자면 setString()
					날짜면 setDate()
				입력값이 문자이거나 날짜면 싱글쿼테이션이 자동으로 삽입된다.
			*/
//			psmt.setString(1, id);
//			psmt.setString(2, pw);
//			psmt.setString(3, name);
			//날짜입력1 : 날짜를 문자열로 입력하는 경우
//			psmt.setString(4, "2022-04-30");
			//날짜입력2 : Date형으로 입력하는 경우
			/*
				현재날짜를 Java에서 입력하는 경우 아래와 같이 변환과정을 거쳐야한다.
				util패키지로 시간을 얻어온 후 sql패키지로 타입을 변환한다.
				이때는 date형으로 입력되므로 setDate()로 인파리미터를 설정해야한다.
			*/
//			java.util.Date utilDate = new java.util.Date();
//			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//			psmt.setDate(4, sqlDate);
//			//5.쿼리실행 후 결과값 반환
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

	public static void main(String[] args) {
		new InsertSQL2().execute();
	}
}
