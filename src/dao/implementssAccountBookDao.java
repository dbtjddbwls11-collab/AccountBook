package dao;

public interface implementssAccountBookDao {
	void insert();
	void delete();
	void update();
	
	void selectTitle();  
	void selectAdate();  
	void monthlySummary(); 
	
	int search(String title);	
	void allprint();
	void fileSave();
}
