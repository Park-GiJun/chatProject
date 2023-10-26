package chatapp.dao;

public class ChatappVo {
	private String id;
	private String pwd;
	
	public ChatappVo() {
		
	}
	
	public ChatappVo (String user_id, String user_pwd) {
		this.id = user_id;
		this.pwd = user_pwd;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPwd() {
		return pwd;
	}
}
