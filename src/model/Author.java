package model;

public class Author {

	private int id;
	private String authorName;
	
	public Author() {
		
	}
	
	
	public Author(int id, String authorName) {
		this.id = id;
		this.authorName = authorName;
	}
	
	//getter
	
	public int getAuthorId() {
		return id;     
		}
	
	public String getAuthorName() {
		return authorName; 
     }
	
	//setter

	public void setAuthorId(int id) {
		this.id = id;
		}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
		}
	
}
