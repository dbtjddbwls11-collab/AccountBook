package Dot;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
	
	private static Singleton instance = null;
	public List<AccountBookDto> list;
	
	private Singleton() {
		list = new ArrayList<AccountBookDto>();
	}
	
	public static Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}