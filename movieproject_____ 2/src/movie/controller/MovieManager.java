package movie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import movie.member.io.FoodIO;
import movie.member.io.MovieIO;
import movie.memeber.model.vo.Movie;
import movie.view.MenuNumberingInterface;

	
public class MovieManager implements MenuNumberingInterface{
	
	Scanner sc = new Scanner(System.in);
	private MovieIO movieIo = new MovieIO();
	LoginManager loginManager;
	
	String[] movieTitle = {"블랙아담","듄","공조2-인터내셔날","짱구는 못말려"}; //영화제목
	String[] movieLocal = {"잠실","강남","역삼","여의도"}; //영화 지역
	String[] movieTime = {"오전 9시","오후 12시","오후 3시","오후 6시","오후 9시"}; //영화 시간
	String[][] arr = {{"A1","A2","A3","A4","A5"},{"B1","B2","B3","B4","B5"}}; //좌석
	
	public MovieManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}
	
	public int counting() {
        return this.reservation;
    }
	

	
	int reservation = 0; //좌석 예약 숫자
	private int price = 14000; //영화 가격
	
	//예매 임시 정보 저장 객체
	List<String> temp = new ArrayList<>();
	// 예매 정보 파일 저장 객체
	List<Movie> movieList = new ArrayList<>();
	
	public MovieManager() {
		List<Movie> movieList = movieIo.loadmovieList();
		this.movieList = movieList != null ? 
								movieList : 
									new ArrayList<>();
	}
	

	// 결제가 끝나면 실행 
	public void saveMovieInfo() {
			String userId = loginManager.getMemberLoginId();
			
			movieList.add(new Movie(userId,temp.get(0),temp.get(1),temp.get(2),price * reservation));
			
			
			movieIo.saveMovieList(movieList);
		
	}
	
	public void pirntReservedInfo() {
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
        System.out.println("■■■■■■■■■■■■■■■■■ 결제정보 ■■■■■■■■■■■■■■■■■");
       System.out.println("영화 제목 : " + temp.get(0));
		System.out.println("영화관 : " + temp.get(1));
		System.out.println("영화 시간 : " + temp.get(2));
		for(int i = 3; i < temp.size();i++) {
			System.out.println("좌석"+(i-2) + " : "+ temp.get(i)); 
		}
		System.out.println("예매표 : " + reservation +"개");
		System.out.println("총 결제가격 : " + price * reservation + "원");
		System.out.println("------------------------------------------------");
		
	}
	
	
	public int showMovie() {
		while (true) {
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("■■■■■■■■■■■■■■■■■ 상영영화 ■■■■■■■■■■■■■■■■■");
			for (int i = 0; i < movieTitle.length; i++) {
				System.out.println((i + 1) + ". " + movieTitle[i]);
			}
			System.out.println("0. 종료");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("🔹 선택해주세요 : ");
			String menu = sc.next();

			switch (menu) {
			case "1":
				temp.add(movieTitle[0]);
				break;
			case "2":
				temp.add(movieTitle[1]);
				break;
			case "3":
				temp.add(movieTitle[2]);
				break;
			case "4":
				temp.add(movieTitle[3]);
				break;
			case "0":
				return MAIN_MENU;
			default:
				 System.out.println("🔹 다시 입력해주세요. : ");
				return MOVIE_TITLE;
			}
			return MOVIE_LOCAL;
		}
	}
	
	public int showLocal() {
		while (true) {
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("■■■■■■■■■■■■■■■■■■■ 지점 ■■■■■■■■■■■■■■■■■■");
			for (int i = 0; i < movieLocal.length; i++) {
				System.out.println((i + 1) + ". " + movieLocal[i]);
			}
			System.out.println("0. 종료");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("🔹 선택해주세요 : ");
			String local = sc.next();

			switch (local) {
			case "1":
				temp.add(movieLocal[0]);
				break;
			case "2":
				temp.add(movieLocal[1]);
				break;
			case "3":
				temp.add(movieLocal[2]);
				break;
			case "4":
				temp.add(movieLocal[3]);
				break;
			case "0":
				return MOVIE_TITLE;
			default:
				System.out.println("🔹 다시 입력해주세요. : ");
				return MOVIE_LOCAL;
			}
			return MOVIE_TIME;
		}
	}
	
	public int showTime() {
		while (true) {
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("■■■■■■■■■■■■■■■■■ 상영시간 ■■■■■■■■■■■■■■■■■");
			for (int i = 0; i < movieTime.length; i++) {
				System.out.println((i + 1) + ". " + movieTime[i]);
			}
			System.out.println("0. 종료");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("🔹 선택해주세요 : ");
			String time = sc.next();

			switch (time) {
			case "1":
				temp.add(movieTime[0]);
				break;
			case "2":
				temp.add(movieTime[1]);
				break;
			case "3":
				temp.add(movieTime[2]);
				break;
			case "4":
				temp.add(movieTime[3]);
				break;
			case "5":
				temp.add(movieTime[4]);
				break;
			case "0":
				return MOVIE_TIME;
			default:
				System.out.println("🔹 다시 입력해주세요. : ");
				return MOVIE_TIME;
			}
			return MOVIE_SEAT;
		}
	}
	
	public void seatsStatus(String[][] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i].length;
		}
		System.out.print("(전체 좌석 : " + sum + "/ 예약가능 좌석 : " + (sum - reservation) + ")\n");
	}
	
	
	public int showSeat() {
		while(true) {
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("■■■■■■■■■■■■■■■■ 좌석 지정 ■■■■■■■■■■■■■■■■■");
			System.out.print("1. 좌석예매");
			seatsStatus(arr);
			System.out.println("2. 결제하기");
			System.out.println("0. 돌아가기");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("🔹 선택해주세요 : ");
		
		String menu = sc.next();
		
		switch (menu) {
		case "1":
			seatsSet();
			break;
		case "2":
			if (reservation != 0) {
				pirntReservedInfo();
				return MOVIE_PAY;
			} else {
				System.out.println("좌석을 선택해주세요.");
				seatsSet();
				break;
			}
		case "0":
			return MOVIE_TIME;
		default:
			System.out.println("🔹 다시 입력해주세요. : ");
			return MOVIE_SEAT;
		}
	}
}

	//좌석 예매
		public void seatsSet() {
			 System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		        System.out.println("■■■■■■■■■■■■■■■■ SCREEN ■■■■■■■■■■■■■■■■");
		        for(int i = 0; i < arr.length; i++) {
				for(int j = 0; j < arr[i].length; j++) {
					System.out.print(arr[i][j] + "\t");
				}
				System.out.println();
			}
			System.out.println();
			
			System.out.print("좌석 선택 : ");
			String set = sc.next();
			
			for(int i = 0; i < arr.length; i++) { //좌석을 선택하면 다시 선택할수 없게 해당 좌석 X로 변환
				for(int j = 0; j < arr[i].length; j++) {
					if(!arr[i][j].equals("X")) {
						if(arr[i][j].equals(set)) {
							temp.add(arr[i][j]);
							arr[i][j] = "X";
							reservation++;
							System.out.println(set + " 좌석이 선택되었습니다."); break;
						}
					}
				}
			}
		}

		public int moviePay() {
			while (true) {
				String[][] arr = { { "A1", "A2", "A3", "A4", "A5" }, { "B1", "B2", "B3", "B4", "B5" } }; // 결제 취소시 좌석을
																											// 리셋해주기 위한
																											// 배열
				System.out.println("결제 하시겠습니까?");
				System.out.println("1. 예");
				System.out.println("2. 아니요");
				System.out.print("🔹 선택해주세요 : ");
				String pay = sc.next();

				switch (pay) {
				case "1": // 결제가 완료되면 파일에 저장 후 메인메뉴로 리턴
					System.out.println("결제가 완료되었습니다.");
					saveMovieInfo();
					break;

				case "2": // 결제를 안할 경우 모든 선택 값 리셋 후 메인메뉴로 리턴
					temp.clear();
					reservation = 0;
					this.arr = arr;
					break;
				default:
					System.out.println("잘못된 입력입니다.");
					return MOVIE_PAY;

				}

				return MAIN_MENU;
			}
		}
		
}
