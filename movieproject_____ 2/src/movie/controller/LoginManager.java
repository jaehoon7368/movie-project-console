package movie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import movie.member.io.MemberIO;
import movie.memeber.model.vo.Member;
import movie.view.MenuNumberingInterface;

public class LoginManager implements MenuNumberingInterface {
	private MemberIO memberIO = new MemberIO();
	Scanner sc = new Scanner(System.in);
	List<Member> members;
	List<Member> copyMember;
	String loginId = "";

	public LoginManager() {
		List<Member> member = memberIO.loadmemberList(); // loginmanager 생성자 만드는순간 저장된파일에 있는 멤버들을 가져온다.

		this.members = member != null ? member : new ArrayList<>();

	}
	// 전체 회원 출력 
	public int memberPrint() { 
		for (int i = 0; i < members.size(); i++) {

			if (members.get(i).getGuestNum() != 0) {
				System.out.println("게스트회원로그인  임시번호 : " + members.get(i).getGuestNum());
			} else
				System.out.println(members.get(i));
		}
		return ADMINFULL_VIEW;
	}

	// 프로그램실행시 첫 화면 메뉴    
	public int loginmenu() {
		boolean bool = true;          // 로그인으로 다시 가기위한 변수

		while (bool) {                // true 로 break 다시 로그인메뉴로 돌아감
			loginString();
			String choice = sc.next();

			switch (choice) {
			case "1":
				memberLogin();          // 회원로그인 화면 로그인후 영화,음식주문 메뉴로
				loginId = getMemberLoginId();  //로그인시 ID 저장 변수
				return MAIN_MENU;
			case "2":
				guestLogin();            //게스트 로그인후 랜덤 번호 부여후 주문 메뉴로
				return MAIN_MENU;
			case "3":                    //회원가입 
				memberJoin();
				bool = true;
				break;
			case "4":                  // ID 찾기 
				ABD: while (true) {
					searchId();
					System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("\n로그인하시려면 1번을 입력하세요.\n다시 시도하려면 2번을 입력하세요.");
					String select = sc.next();
					if ("1".equals(select)) {
						bool = true;
						break ABD;
					} else if ("2".equals(select)) {
						continue;
					} else {
						System.out.println("🔹 다시 입력하세요");
					}
				}
				break;

			case "5":
				return ADMINFULL_VIEW;

			case "0":
				System.out.println(" 😊 감사합니다. 다음에 또 찾아주세요 😊 ");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				return EXIT;

			default:
				System.out.println("🔹 다시 입력해주세요. : ");
				break;
			}
		} // 스위치문 end
		return MAIN_MENU;
	} // login menu

	public int searchId() {
		while (true) {
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("〓〓〓〓〓〓아이디 찾기 〓〓〓〓〓〓〓〓〓");
			System.out.println("🔹 이름을 입력해주세요 : ");
			String name = sc.next();
			System.out.println("🔹 생년월일을 입력해주세요 : ");
			String birth = sc.next();
			System.out.println("🔹 핸드폰번호를 입력해주세요 : ");
			String phone = sc.next();
			String userId = searchMyid(name, birth, phone);
			if(userId == null) {
				System.out.println("일치하는 정보와 맞지 않습니다.");
			}
			else {
				System.out.println(name+"님의 아이디는 : "+userId+" 입니다.");
			}
			return SERCH_ID;
		}
	}

	// 아이디 조회 메소드 이름 생년월일 핸드폰번호가 같다면 String 으로 아이디 리턴 같지않다면 정보가 맞지않습니다 리턴 오버롣ㅇ
	
	public String searchMyid(String name,String birth ,String phone) {          

		for (int i = 0; i < members.size(); i++) {
			if (name.equals(members.get(i).getName())
					&&birth.equals(members.get(i).getBirth())
					&&phone.equals(members.get(i).getPhone())
					) {System.out.println("아이디검색에 성공하셨습니다.");
				
					return members.get(i).getUserId();
				
			}
		}
		return null;
	}
	
	

	// 게스트로그인 랜덤값 값 저장해서 중복일시 다른번호로
	public int guestLogin() {
		Random rnd = new Random();
		System.out.println("비회원 게스트로 로그인 합니다 .");

		while (true) {
			for (Member m : members) {
				int rndguestnum = rnd.nextInt(10000) + 9999;
				if (rndguestnum != m.getGuestNum()) {
					System.out.printf("임시회원번호는 %d 입니다.\n", rndguestnum);
					members.add(new Member(rndguestnum)); // 임시번호를 파일에 저장
					memberIO.saveMemberList(members); // 파일에 저장
					return MAIN_MENU;
				}
			}
		} //while문
	}

	// 로그인시 id 저장용메소드
	public String getMemberLoginId() {
		return this.loginId;
	}

	// 로그아웃 id 지우고 로그인메뉴 첫화면으로 돌아간다.
	public int logOutId() {
		this.loginId = null;
		System.out.println("로그아웃 되었습니다");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n\n");
		return LOGIN_MENU;
	}
	//회원 로그인
	public int memberLogin() { 
		String id = "";
		String pw = "";
		System.out.println("■■■■■■■■■■■■■■■■ 로그인 페이지 ■■■■■■■■■■■■■■■■");

		while (true) {
			System.out.print("🔹 ID를 입력하세요 : ");
			id = sc.next();
			System.out.print("🔹 PW를 입력하세요.(4자리) : ");
			pw = sc.next();
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			if (dataCheck(id, pw)) {

				this.loginId = id;

				System.out.println(id + "님이 로그인 하셨습니다.");
				return MAIN_MENU;
			} else
				System.out.println("로그인에 실패하였습니다. 다시입력해주세요.");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			} // while 끝

	}
	//회원 ID PW 체크 메소드
	public boolean dataCheck(String id, String pw) { 
		for (Member m : members) {
			if (id.equals(m.getUserId()) && pw.equals(m.getUserPw())) {

				return true;
			}
		}
		return false;
	}
	
	public boolean checkId(String newId) {          //아아디 중복검사 메소드

		for (int i = 0; i < members.size(); i++) {
			if (newId.equals(members.get(i).getUserId())) {
				return true;
			}
		}
		return false;
	}
	

	public int memberJoin() { // 멤버 회원가입
		System.out.println("■■■■■■■■■■■■■■■■ 회원가입 ■■■■■■■■■■■■■■■■\n ");

		while (true) {
			System.out.println("🔹 아이디를 입력해주세요 : ");
			String newId = sc.next();

			if (checkId(newId) ) {
				System.out.println("이미 존재하는 아이디입니다.\n다시 입력해주세요");
				continue ;
			}
			
			System.out.println("🔹 비밀번호를 입력해주세요 : ");
			String newPw = sc.next();

			while (true) {
				System.out.println("🔹 비밀번호를 한번 더 입력해주세요 : ");
				String cfPw = sc.next();
				if (newPw.equals(cfPw))
					break;
				else
					System.out.println("비밀번호가 일치하지않습니다 다시 입력해주세요 .");
			}
			System.out.println("🔹 이름을 입력해주세요 : ");
			String name = sc.next();
			System.out.println("🔹 생년월일을 입력해주세요(Ex:19970504) : ");
			String birth = sc.next();
			System.out.println("🔹 핸드폰번호를 입력해주세요 : ");
			String phone = sc.next();
			System.out.println("🔹 e-mail을 입력해주세요 : ");
			String email = sc.next();
			members.add(new Member(newId, newPw, name, birth, phone, email));
			memberIO.saveMemberList(members);
			System.out.println(" 🙏 회원가입이 완료되었습니다 🙏 ");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			return MAIN_MENU;
		}
	}

	public void loginString() {
		System.out.println(
				"〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\r\n"+
				"           	   👨‍👩‍👧‍👦 시네마 👨‍👩‍👧‍👦\r\n\n" +
				"1. 회원 로그인 \r\n" + 
			    "2. 비회원 로그인   \r\n" + 
			    "3. 회원가입   \n" + 
			    "4. ID 찾기  \n" +
			    "5. 관리자 모드\n" +
			    "0. 종료   \r\n" + 
			    "〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\r\n" + 
			    "🔹 선택해주세요 : ");
			}

}
