package Dot;

import java.security.PublicKey;

public class AccountBookDto {

	private String iokind;             //수입/지출
    private int money;                 //금액
    private String title;              //제목
    private String content;            //내용
    private String adate;              //날짜
    
    public AccountBookDto( ) {
    }
    
    public AccountBookDto(String iokind,
    		int money,
    		String title,
    		String content,
    		String adate) {
    	super();
    	this.iokind = iokind;
    	this.money = money;
    	this.title = title;
    	this.content = content;
    	this.adate = adate;
    }
    
    public String getIokind() { return iokind;}
    public void setIokind(String iokind) { this.iokind = iokind;}
    
    public int getMoney() { return money;}
    public void setMoney(int money) { this.money = money;}
        
    public String getTitle() { return title;}
    public void setTitle(String title) { this.title = title;}
    
    public String getContent() { return content;}
    public void setcontent(String content) { this.content = content;}
    
    public String getAdate() { return adate;}
    public void setadate(String adate) { this.adate = adate;}
    
    @Override
    public String toString( ) {
    	return iokind + "-"
    			+title + "-"
    			+content + "-"
    			+adate;
    }
    
    	public void info() {
    		System.out.println("[" + iokind + "] 날짜: " + adate + " | 제목: " + title 
    				+ " | 금액: " + money + "원 | 내용: " + content);
    	}
    }
