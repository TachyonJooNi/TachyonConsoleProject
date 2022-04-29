package test;

//대학교 친구 정보를 저장하기 위한 클래스
public class UnivFriend extends Friend {
	
	//확장한 멤버변수 : 전공과목
	String major;
	
	//생성자와 멤버메서드 모두 High클래스와 동일하다.
	public UnivFriend(String name, String phone, String addr, String major) {
		super(name, phone, addr);
		this.major = major;
	}

	@Override
	public void showAllData() {
		System.out.println("===대딩친구(전체정보)===");
		super.showAllData();
		System.out.println("전공:"+ major);
	}

	@Override
	public void showBasicInfo() {
		System.out.println("===대딩친구===");
		System.out.println("이름:"+ name);
		System.out.println("전화번호:"+ phone);
	}
}
