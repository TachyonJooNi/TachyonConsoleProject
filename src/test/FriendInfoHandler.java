package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
해당 프로그램에서 기능을 담당하는 클래스를 핸들러클래스 혹은
매니저클래스라고 한다.
*/
public class FriendInfoHandler {
	
	/*
	기존 객체배열에 저장하던 방식을 List계열의 컬렉션으로 변경한다.
	우리는 배열과 동일한 특징을 가지고 있는 ArrayList<E>로 객체를 생성한다.
	해당 컬렉션에는 Friend타입의 객체를 저장할 것이다. 따라서 상속관계가 있는
	하위 객체들을 모두 저장할 수 있다.
	*/
	private ArrayList<Friend> myFriends;
	
	public FriendInfoHandler(int num) {
		//멤버변수로 선언한 List컬렉션의 객체를 생성한다.
		myFriends = new ArrayList<Friend>();
		
		//List<E>계열의 컬렉션은 인덱스가 존재하긴 하나 자동 인덱싱을 지원하므로
		//인덱스 저장을 위한 변수는 필요없다
//		numOfFriends = 0;
	}
	public void addFriend(int choice) {
		
		//친구의 공통정보 입력받음
		Scanner scan = new Scanner(System.in);
		String iName, iPhone, iAddr, iNickname, iMajor;
		System.out.println("이름:"); iName = scan.nextLine();
		System.out.println("전화번호:"); iPhone = scan.nextLine();
		System.out.println("주소:"); iAddr = scan.nextLine();
		
		/*
		List<E> 컬렉션에 객체를 추가할때는 add()메서드를 사용한다.
		추가하면 자동으로 인덱스가 부여되므로 별도의 처리가 필요하지 않다.
		단, 인덱스를 건너뛸수는 없다.
		*/
		if(choice==1) {
			System.out.println("별명:"); iNickname = scan.nextLine();
			
			HighFriend high = new HighFriend(iName, iPhone, iAddr, iNickname);
//			myFriends[numOfFriends++] = high;
			myFriends.add(high);
			
		}
		else if(choice==2) {
			System.out.println("전공:"); iMajor = scan.nextLine();
			myFriends.add(new UnivFriend(iName, iPhone, iAddr, iMajor));
		}
		System.out.println("친구정보 입력이 완료되었습니다.");
	}////end of addFriend
	
	//정보출력1 : 일반for문
	public void showAllData() {
		/*
		컬렉션에 저장된 객체의 갯수만큼 반복한다. size()메서드를 통해
		얻어올 수 있다.
		*/
		for(int i=0 ; i<myFriends.size() ; i++) {
			myFriends.get(i).showAllData();
		}
		System.out.println("==전체정보가 출력되었습니다==");
	}
	
	//정보출력2 : 확장for문
	public void showSimpleData() {
		/*
		ArrayList<E>에는 Friend타입의 객체가 저장되어있다.
		따라서 확장 for문을 통해 하나씩 꺼내는 객체는 Friend타입이다.
		showBasicInfo()메서드는 오버라이딩 되어있으므로 항상 최하위의 메서드가
		호출되어 고딩친구 혹은 대딩친구의 정보를 출력할 수 있다.
		*/
		for(Friend f : myFriends) {
			f.showBasicInfo();
		}
		System.out.println("==간략정보가 출력되었습니다==");
	}
	
	//주소록 검색
	public void searchInfo() {
		//검색한 정보가 없는경우 false를 유지한다.
		boolean isFind = false;
		Scanner scan = new Scanner(System.in);
		System.out.print("검색할 이름을 입력하세요:");
		String searchName = scan.nextLine();
		
		//이터레이터를 통해 검색기능 구현하기
		Iterator<Friend> itr = myFriends.iterator();
		while(itr.hasNext()) {
			Friend fr = itr.next();
			if(searchName.equals(fr.name)) {
				System.out.println("# 귀하가 요청하는 정보를 찾았습니다 #");
				fr.showAllData();
				//정보를 찾으면 true로 변경한다.
				isFind = true;
			}
		}
		if(isFind==false) {
			System.out.println("***찾은 정보가 없습니다.***");
		}
	}////end of searchInfo
	
	//주소록 삭제
	public void deleteInfo() {
		
		boolean isFind = false;
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제할 이름을 입력하세요:");
		String deleteName = scan.nextLine();
		
		Iterator<Friend> itr = myFriends.iterator();
		while(itr.hasNext()) {
			Friend fr = itr.next();
			if(deleteName.equals(fr.name)) {
				myFriends.remove(fr);
				System.out.println("# 정보가 삭제되었습니다 #");
				isFind = true;
				break;
			}
		}
		if(isFind==false) {
			System.out.println("# 찾는 정보가 없습니다. #");
		}
	}////end of deleteInfo
}
