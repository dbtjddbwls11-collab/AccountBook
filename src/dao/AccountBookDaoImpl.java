package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dot.AccountBookDto;
import Dot.Singleton;
import file.FileProc;

public class AccountBookDaoImpl implements implementssAccountBookDao {
	
	Scanner sc = new Scanner(System.in);
	private FileProc fproc;

	public AccountBookDaoImpl() {
		fproc = new FileProc("accountBook");
		fproc.load();
	}
	
	public void insert() {
		System.out.println("\n=== 데이터 추가 ===");
		AccountBookDto dto = new AccountBookDto();
		
		System.out.print("수입/지출 선택 (수입 또는 지출 입력) = ");
		dto.setIokind(sc.next());
		
		System.out.print("금액 = ");
		dto.setMoney(sc.nextInt());
		
		System.out.print("제목 = ");
		dto.setTitle(sc.next());
		
		System.out.print("내용 = ");
		dto.setcontent(sc.next());
		
		System.out.print("날짜 (예:26.06.09) = ");
		dto.setadate(sc.next());
		
		Singleton single = Singleton.getInstance();		
		boolean isS = single.list.add(dto);
		if(!isS) {
			System.out.println("추가되지 않았습니다.");
			return;
		}
		System.out.println("정상적으로 추가되었습니다.");
	}

	public void delete() {
		System.out.println("\n=== 데이터 삭제 ===");
		System.out.print("삭제할 데이터의 제목 입력 = ");		
		String title = sc.next();
		
		int index = search(title);        
		if(index == -1) {
			System.out.println("데이터를 찾을 수 없습니다.");
			return;
		}
		
		Singleton single = Singleton.getInstance();	
		AccountBookDto deleteDto = single.list.remove(index);		
		deleteDto.info();
		System.out.println("위 내역을 삭제하였습니다.");
	}

	public void update() {
		System.out.println("\n=== 데이터 수정 ===");
		System.out.print("수정할 데이터의 제목 입력 = ");		
		String title = sc.next();
		
		int index = search(title);		
		if(index == -1) {
			System.out.println("데이터를 찾을 수 없습니다.");
			return;
		}
		
		System.out.print("수정할 내용 입력 = ");
		String content = sc.next();
		
		Singleton single = Singleton.getInstance();	
		AccountBookDto updateDto = single.list.get(index);
		updateDto.setcontent(content);
		
		System.out.println("내용이 수정되었습니다.");
	}

	public void selectTitle() {
		System.out.println("\n=== 1. 제목 검색 ===");
		System.out.print("검색할 제목 = ");		
		String title = sc.next();
		
		List<AccountBookDto> findList = new ArrayList<>();
		Singleton single = Singleton.getInstance();	
		
		for (AccountBookDto acc : single.list) {
			if(acc.getTitle().contains(title)) {
				findList.add(acc);
			}
		}
		
		if(findList.isEmpty()) {
			System.out.println("데이터를 찾을 수 없습니다.");
			return;
		}
		
		for (AccountBookDto acc : findList) {
			acc.info();		
		}
	}

	public void selectAdate() {
		System.out.println("\n=== 2. 날짜 검색 ===");
		System.out.print("검색할 날짜(adate, 예: 26.06.09) = ");		
		String adate = sc.next();
		
		List<AccountBookDto> findList = new ArrayList<>();
		Singleton single = Singleton.getInstance();	
		
		for (AccountBookDto acc : single.list) {
			if(adate.equals(acc.getAdate())) {
				findList.add(acc);
			}			
		}
		
		if(findList.isEmpty()) {
			System.out.println("데이터를 찾을 수 없습니다.");
			return;
		}
		
		for (AccountBookDto acc : findList) {
			acc.info();
		}		
	}

	public void monthlySummary() {
		System.out.println("\n=== 3. 월별 결산 ===");
		System.out.print("결산할 년-월 입력 (예: 26.06) = ");
		String yearMonth = sc.next();
		
		int totalIncome = 0;
		int totalExpense = 0;
		boolean hasData = false;
		
		Singleton single = Singleton.getInstance();
		System.out.println("[" + yearMonth + " 내역 리스트]");
		
		for (AccountBookDto acc : single.list) {
			if (acc.getAdate().startsWith(yearMonth)) {
				acc.info();
				hasData = true;
				
				if (acc.getIokind().equals("수입")) {
					totalIncome += acc.getMoney();
				} else if (acc.getIokind().equals("지출")) {
					totalExpense += acc.getMoney();
				}
			}
		}
		
		if (!hasData) {
			System.out.println("해당 월에 입력된 가계부 데이터가 없습니다.");
			return;
		}
		
		System.out.println("--------------------------------------------------");
		System.out.println(yearMonth + " 총 수입: " + totalIncome + "원");
		System.out.println(yearMonth + " 총 지출: " + totalExpense + "원");
		System.out.println(yearMonth + " 순수 잔액: " + (totalIncome - totalExpense) + "원");
		System.out.println("--------------------------------------------------");
	}

	public int search(String title) {
		int index = -1;
		Singleton single = Singleton.getInstance();	
		for (int i = 0; i < single.list.size(); i++) {
			AccountBookDto dto = single.list.get(i);			
			if(title.equals(dto.getTitle())) {
				index = i;
				break;
			}
		}		
		return index;
	}

	public void allprint() {
		System.out.println("\n=== 전체 출력 ===");
		Singleton single = Singleton.getInstance();	
		
		if(single.list.isEmpty()) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		
		for (AccountBookDto acc : single.list) {
			acc.info();
		}				
	}

	public void fileSave() {		
		fproc.save();		
	}
}
