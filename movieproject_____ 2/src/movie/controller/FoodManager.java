package movie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import movie.member.io.FoodIO;
import movie.memeber.model.vo.Food;
import movie.view.MenuNumberingInterface;

public class FoodManager implements MenuNumberingInterface{
	FoodIO foodIO = new FoodIO();
	Scanner sc = new Scanner(System.in);

	List<Food> foodMenu = new ArrayList<>();
	{
		foodMenu.add(new Food("1", "팝콘", 8000));
		foodMenu.add(new Food("2", "나초", 5000));
		foodMenu.add(new Food("3", "오징어버터구이", 6000));
		foodMenu.add(new Food("4", "콜라", 3000));
		foodMenu.add(new Food("5", "사이다", 3000));

	}

	public int foodOrder() {
		int sum = 0;// 금액합계
		String result = "";// 결과출력용 문자열

		List<Food> foodcopy = new ArrayList<>();
		
		// 주메뉴선택후 처리
		
		
		
		loop: while (true) {
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			for (int i = 0; i < foodMenu.size(); i++) {
				System.out.println(foodMenu.get(i));
			}
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("🔹 선택해주세요 : ");
			String select = sc.next();


			switch (select) {
			case "1":
				foodcopy.add(new Food(foodMenu.get(0).getName(), foodMenu.get(0).getPrice()));
				break;
			case "2":
				foodcopy.add(new Food(foodMenu.get(1).getName(), foodMenu.get(1).getPrice()));
				break;
			case "3":
				foodcopy.add(new Food(foodMenu.get(2).getName(), foodMenu.get(2).getPrice()));
				break;
			case "4":
				foodcopy.add(new Food(foodMenu.get(3).getName(), foodMenu.get(3).getPrice()));
				break;
			case "5":
				foodcopy.add(new Food(foodMenu.get(0).getName(), foodMenu.get(0).getPrice()));
				break;

			default:
				System.out.println("없는 메뉴입니다. 다시 선택해주세요");
				break;

			// 파일저장용 메소드
			}
			
			System.out.print("🔹 수량을 입력해주세요 : ");
			String str = sc.next();
			System.out.println("🔹 주문을 더 하시겠습니까? (y/n) : ");
			String addMenu = sc.next();
			
			
			int num = Integer.parseInt(str);
			
			if (addMenu.equals("n")) {

				int total = 0;
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("■■■■■■■■■■■■■■ 주문내역 ■■■■■■■■■■■■■■");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("메뉴명\t단가\t수량\t금액");
				
				for (Food m : foodcopy) {
					System.out
							.println(m.getName() + "\t" + m.getPrice() + "\t" + num + "\t" + m.getPrice() * num + "원");

					total += (m.getPrice() * num);
				}
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("		총 가격 : " + total + "원 입니다");
				System.out.println("🔹 결제하시겠습니까? (y/n) : ");
				String choice = sc.next();
				if (choice.equals("y")) {
					System.out.println("결제가 완료되었습니다.");
					foodIO.saveFoodList(foodcopy);
					return MAIN_MENU ;
				} else {
					continue;
				}
				
				
				

			}

		}
	}
}