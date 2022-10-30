package movie.controller;

import java.util.List;
import java.util.Scanner;

import movie.member.io.FoodIO;
import movie.member.io.MovieIO;
import movie.memeber.model.vo.Food;
import movie.memeber.model.vo.Member;
import movie.memeber.model.vo.Movie;
import movie.view.MenuNumberingInterface;

public class AdminManager implements MenuNumberingInterface{
	private MovieIO movieIO = new MovieIO();
	private FoodIO foodIO = new FoodIO();
	Scanner sc = new Scanner(System.in);
	List<Movie> movies;
	List<Food> foodPrice ; // 푸드 총 금액 읽어올  리스트 변수

	
	public AdminManager() {
	movies = movieIO.loadmovieList();       // loginmanager 생성자 만드는순간  저장된파일에 있는 멤버들을 가져온다.
	foodPrice = foodIO.foodLoadList();
	
	}
	
	
	public int adminFullview() {         
		adminView();
		while(true) {
			
			String choice = sc.next();
			switch (choice) { // 회원 목록 총 예매내역, 총매출,
			case "1":
				return MEMBER_PRINT;
			case "2": 
				return MOVIE_ALLINFO;

			case "3":
				return TOTAL_PRICE; // 메인메뉴 상수
			default:
				;
			}
			
			return LOGIN_MENU;
		}
	}
	
	public int movieAllInfo() {
		for (int i = 0 ; i < movies.size(); i++) {
			System.out.println(movies.get(i));
		}
		return ADMINFULL_VIEW;
	}
	
	public int totalPriceInfo() {
		int sum = 0 ;
		int foodSum = 0;
		int movieSum = 0;
		for (Food fm : foodPrice) {
			foodSum += fm.getPrice();
		}
		for (Movie m : movies ) {
			movieSum+= m.getPrice();
		}
		sum = movieSum + foodSum;
		System.out.println("음식주문 총매출액 : " + foodSum + "입니다.");
		System.out.println("영화티켓 총매출액 : " + movieSum + "입니다.");
		System.out.println("총 매출액은 : " + sum + "입니다.");
		return ADMINFULL_VIEW;
	}
	
	 
	
	
	public void adminView(){
		System.out.println("■■■■■■■■■■■■■■■■■ 관리자모드 ■■■■■■■■■■■■■■■■■");
		System.out.println("1. 회원목록 리스트 ");
		System.out.println("2. 총예매내역 리스트 ");
		System.out.println("3. 총매출  ");
		System.out.println("4. 나가기 ");
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		
	}
	
}
