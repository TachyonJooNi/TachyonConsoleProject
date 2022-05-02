package shopping;

public interface IConnect {
	
	String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	void connect(String user, String pass);//DB연결
	void execute();//쿼리실행
	void close();//자원반납
	
	String scanValue(String title);
}
