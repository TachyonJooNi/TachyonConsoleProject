package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MyFriendInfoBookMain {
	
	/*
	매개변수도 없고 반환타입도 없는 메서드
	: 해당 프로그램에서 메뉴를 출력하는 용도로 정의함.
	*/
	public static void menuShow() {
		System.out.println("######### 메뉴를 입력하세요 #########");
		System.out.print("1.고딩친구입력 ");
		System.out.println("2.대딩친구입력 ");
		System.out.print("3.전체정보출력 ");
		System.out.println("4.간략정보출력 ");
		System.out.print("5.검색 ");
		System.out.print("6.삭제 ");
		System.out.println("7.프로그램종료");
		System.out.print("메뉴선택>>>");
	}

	/*
	메인 메서드는 해당 프로그램의 시작점(Entry point)이므로 복잡한 로직의
	구성보다는 프로그램의 전반적인 흐름에 대해서만 기술하는것이 좋다.
	따라서 선택한 메뉴에 따라 핸들러 클래스의 메서드만 호출하는 형태로
	구현되어 있다.
	*/
	public static void main(String[] args) {
		
		//사용자 입력을 위한 객체 생성
		Scanner scan = new Scanner(System.in);
		/*
		기능을 담당하는 핸들러(메니저) 클래스의 객체를 생성
		초기값으로 100명의 정보를 저장할수 있는 Friend타입의 객체배열을 생성한다.
		*/
		FriendInfoHandler handler = new FriendInfoHandler(100);
		
		/*
		무한루프 조건으로 특정 입력에만 종료할 수 있는 구조를 만들어준다.
		break문은 반복문을 탈출시키는 기능이 있으므로 이와같은 무한루프에서는
		자주 사용된다.
		또한 for(;;) 문을 통해 무한루프를 구현 할 수 있으나 반복의 횟수가 정확하지
		않은 경우에는 while문을 주로 사용한다.
		*/
		while(true) {
			
			//메뉴를 출력한다.
			menuShow();
			
			//사용자는 수행할 기능의 메뉴를 선택한다.
			int choice = scan.nextInt();
			
			//선택한 메뉴를 수행할수 있는 메서드를 switch 문을 통해 호출한다.
			switch(choice){
			case 1: case 2:
				//고딩/대딩 친구 입력
				handler.addFriend(choice);
				break;//break문을 만나면 switch문을 탈출한다.
			case 3:
				//전체정보출력
				handler.showAllData();
				break;
			case 4:
				//간략정보출력
				handler.showSimpleData();
				break;
			case 5:
				//검색
				handler.searchInfo();
				break;
			case 6:
				//삭제
				handler.deleteInfo();
				break;
			case 7:
				System.out.println("프로그램종료");
				return;//main의 종료는 프로그램의 종료로 이어진다.
			}////switch 끝
		}////while 끝
	}////main 끝
}////class 끝
