package main;

import java.util.Scanner;

import dao.AccountBookDaoImpl;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		AccountBookDaoImpl dao = new AccountBookDaoImpl();

		while(true) {
			System.out.println("\n<<<<< Account Book (가계부) >>>>>");
			System.out.println("1. 데이터 추가");
			System.out.println("2. 데이터 삭제");
			System.out.println("3. 데이터 수정 (내용)");
			System.out.println("4. 데이터 검색 및 결산");
			System.out.println("5. 모두 출력");
			System.out.println("6. 데이터 저장");
			System.out.println("7. 프로그램 종료");
			
			System.out.print("번호 입력 >> ");
			int work = sc.nextInt();
			
			switch(work) {
				case 1:
					dao.insert();
					break;
				case 2:
					dao.delete();
					break;
				case 3:
					dao.update();
					break;
				case 4:
					System.out.println("\n[ 검색 메뉴 ]");
					System.out.println("1. 제목 검색");
					System.out.println("2. 날짜 검색");
					System.out.println("3. 월별 결산");
					System.out.print("검색 번호 선택 >> ");
					int searchMenu = sc.nextInt();
					
					if(searchMenu == 1) dao.selectTitle();
					else if(searchMenu == 2) dao.selectAdate();
					else if(searchMenu == 3) dao.monthlySummary();
					else System.out.println("잘못된 선택입니다.");
					break;
				case 5:
					dao.allprint();
					break;
				case 6:
					dao.fileSave();
					break;
				case 7:
					System.out.println("프로그램을 종료합니다.");
					sc.close();
					System.exit(0);
				default:
					System.out.println("메뉴를 다시 확인해 주세요.");
			}		
		}
	}
}
