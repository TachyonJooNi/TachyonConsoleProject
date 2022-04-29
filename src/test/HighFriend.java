package test;

//고등학교 친구 정보를 저장할 클래스
public class HighFriend extends Friend {
	
	//자식에서 확장한 멤버변수
	String nickname; //별명
	
	//인자생성자
	public HighFriend(String name, String phone, String addr, String nickname) {
		//자식객체는 부모객체를 초기화할 인수까지 모두 받아 super를 통해 전달한다.
		super(name, phone, addr);
		this.nickname = nickname;
	}
	/*
	고딩친구의 전체정보를 출력하기 위해 부모에서 정의된 메서드를 super를 통해
	호출하고, 자식에서 확장한 변수를 별도의 print문을 통해 출력한다.
	super을 안붙이면 자기자신을 호출하는 재귀호출이 되어버린다.
	*/
	@Override
	public void showAllData() {
		System.out.println("===고딩친구(전체정보)===");
		super.showAllData();
		System.out.println("별명:"+ nickname);
	}
	/*
	자식에서 오버라이딩 하여 재정의한 메서드로 고딩친구의 간략한
	정보를 출력한다.	
	*/
	@Override
	public void showBasicInfo() {
		System.out.println("===고딩친구===");
		System.out.println("별명:"+ nickname);
		System.out.println("전화번호:"+ phone);
	}
}
