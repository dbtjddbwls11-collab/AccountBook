package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Dot.AccountBookDto; // 패키지 경로 명시로 에러 해결
import Dot.Singleton;      // 패키지 경로 명시로 에러 해결

public class FileProc {

	private File file;
	
	public FileProc(String filename) {
		file = new File("C:/tmp/" + filename + ".txt");
		
		try {
			if (file.getParentFile() != null && !file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			if(file.createNewFile()) {
				System.out.println("파일 생성 성공");
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	public void save() {	
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			Singleton single = Singleton.getInstance();
			
			for(AccountBookDto dto : single.list) {
				pw.println(dto.toString());				
			}			
			pw.close();
			System.out.println("파일 저장이 완료되었습니다.");
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
	
	public void load() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			Singleton single = Singleton.getInstance();
			single.list.clear();
			
			String str = "";	
			while((str = br.readLine()) != null) {
				String arr[] = str.split("-");
				if(arr.length < 5) continue;
				
				AccountBookDto dto = new AccountBookDto(
						arr[0], 
						Integer.parseInt(arr[1]), 
						arr[2], 
						arr[3], 
						arr[4]
				);
				single.list.add(dto);				
			}			
			br.close();			
		} catch (FileNotFoundException e) {			
			System.out.println("저장된 파일이 없어 새로운 가계부를 시작합니다.");
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
