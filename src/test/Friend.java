package test;
/*
친구를 표현한 최상위 클래스로 해당 프로그램에서는 Friend클래스로
객체생성은 하지 않는다. 기본정보를 저장하고 상속할 목적으로 정의된
클래스이다.
이런 클래스를 VO(Value Object)라고 부른다. 즉 값만 가진 객체라는 뜻이다.
웹으로 넘어가면 계속보게 될 것이다.
*/
public class Friend {
	//멤버변수 : 기본적보 3가지
	 String name; //이름
	 String phone; //전화번호
	 String addr; //주소
	//인자생성자
	public Friend(String name, String phone, String addr) {
		this.name = name;
		this.phone = phone;
		this.addr = addr;
	}
	//멤버변수 전체 정보를 출력할 목적의 멤버메서드
	public void showAllData() {
		System.out.println("이름:"+ name);
		System.out.println("전화번호:"+ phone);
		System.out.println("주소:"+ addr);
	}
	/*
	간략 정보를 출력하는 용도의 멤버메서드로 실행부가 없는 상태로 정의한다.
	해당 프로그램에서는 오버라이딩의 목적으로만 사용하기 위해 정의한다.
	나중에는 추상메서드로 표현해 보겠다.
	*/
	public void showBasicInfo() {}
}
