package movie.view;

import java.util.Scanner;

import movie.controller.AdminManager;
import movie.controller.FoodManager;
import movie.controller.LoginManager;
import movie.controller.MovieManager;

public class View implements MenuNumberingInterface{
	Scanner sc = new Scanner(System.in);
	LoginManager lm = new LoginManager();
	FoodManager fm = new FoodManager();
	AdminManager  am = new AdminManager();
	MovieManager mr = new MovieManager(lm);
	String loginId = "";
	
	
              //전체 메뉴 
	    public int viewMenu() {
	        int menuNum = LOGIN_MENU;
	        
	        while(true) {
	            switch(menuNum) {
	            
	            case LOGIN_MENU :                //첫 로그인 회원가입 선택화면
	                menuNum = lm.loginmenu();
	                break;
	                
	            case SERCH_ID :					 //아이디 조회 
	                menuNum = lm.searchId();    
	                break;
	            case GUEST_LOGIN : 				//비회원 로그인
	                menuNum = lm.guestLogin(); 
	                break;
	            case MAIN_MENU :                //영화 음식 메뉴 주문 화면
	                menuNum = mainMenu();     
	                break;
	            case JOIN_MENU :                //회원가입 메뉴
	            	menuNum = lm.memberJoin();
	                break;
	            case MOVIE_TITLE : 				//영화제목선택
	                menuNum = mr.showMovie();
	                break;
	            case MOVIE_LOCAL :				//영화지역선택
	                menuNum = mr.showLocal();
	                break;
	            case MOVIE_TIME :				//영화시간선택
	                menuNum = mr.showTime();
	                break;
	            case MOVIE_SEAT :				//좌석선택
	                menuNum = mr.showSeat();
	                break;
	            case MOVIE_PAY : 				//영화결제
	                menuNum = mr.moviePay();
	                break;
	            case FOOD_MENU :				//음식주문메뉴
	                menuNum = fm.foodOrder();
	                break;
	            case ADMINFULL_VIEW :			//관리자메뉴
	                menuNum = am.adminFullview();
	                break;
	            case MEMBER_PRINT:				//관리자회원목록
	                menuNum = lm.memberPrint();
	                break;
	            case MOVIE_ALLINFO :			//관리자영화예매모든내역
	                menuNum = am.movieAllInfo();
	                break;
	            case TOTAL_PRICE:				//총매출내역
	                menuNum = am.totalPriceInfo();
	                break;
	            case LOGOUT:					//로그아웃
	                menuNum = lm.logOutId();
	                break;
	            case EXIT :						//프로그램종료
	                return EXIT;
	            
	            }
	        }//while
	    }
		

	    public int mainMenu() {
	        while (true) {
	            mainMenuString();
	            String select = sc.next();

	            switch (select) {
	            case "1":
	                return MOVIE_TITLE; // 영화예매
	            case "2":
	                return FOOD_MENU; // 음식주문
	            case "3":
	                if (mr.counting() != 0) { // 예매내역출력
	                    mr.pirntReservedInfo();
	                    return MAIN_MENU;
	                } else
	                    System.out.println("예매내역이 없습니다.");
	                break;
	            case "4":
	                return LOGOUT;
	            case "0":
	                return EXIT; // 프로그램종료

	            default:
	                System.out.println("잘못 입력하셨습니다.");
	            }
	            return MAIN_MENU;
	        }
	    }
	    
	    
	    
	    
	    
	    public void mainMenuString() {
	    	 System.out.println(
		        		"〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\r\n"+
		        		"           	 👨‍👩‍👧‍👦시네마 👨‍👩‍👧‍👦 \r\n\n" + 
		        		"1. 영화예매 👓 \r\n"+ 
		        		"2. 음식주문 🥨 \r\n" + 
		        		"3. 예매내역 📆 \r\n" +
		        		"4. 로그아웃 👊   \r\n" +
		        		"0. 종료 🖐 \r\n" + 
		        		"〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\r\n" + 
		        		"🔹 선택해주세요 : "  );
		    }
}