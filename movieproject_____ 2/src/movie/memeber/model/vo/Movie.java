package movie.memeber.model.vo;

import java.io.Serializable;

public class Movie implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userId;
	private String title;
	private String local;
	private String time;
	private int price;
	
	public Movie() {
		super();
	}

	public Movie(String userId, String title, String local, String time, int price) {
		super();
		this.userId = userId;
		this.title = title;
		this.local = local;
		this.time = time;
		this.price = price;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "-------------------------------\n" + "UserID = " + userId + "\n영화 제목 = " + title + "\n영화관 = " + local + "\n영화 시간 = " + time + "\n결제 내역 = "
				+ price + "원\n" + "-------------------------------";
	}
	
	
}
