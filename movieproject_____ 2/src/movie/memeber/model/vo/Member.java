package movie.memeber.model.vo;

import java.io.Serializable;
import java.util.Objects;

public class Member implements Serializable{
	
	// 결제금액 구매날짜  userid
	
	
	/** 
	 *
	 *         
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userPw;
	private String name ;
	private String birth ; 
	private String phone ;
	private String email;
	private int guestNum;
	
	public Member() {
		super();
	}
	public Member (int guestNum) {      //게스트용 멤버 생성자  상속 X 
		this.guestNum = guestNum;
	}
	
	
	public Member(String id , String pw )  {    // id pw 로그인 조회 생성자
		this.userId = id ;
		this.userPw = pw ;
	}
	public Member(String id ) {
		this.userId =id ;
	}
	
	
	 

	@Override
	public String toString() {
		return "회원 아이디 : " + userId +  " 이름 : " + name + " 생년월일 : " + birth + " 핸드폰번호 : "
				+ phone + " 이메일 : " + email;
	}
	public Member(String userId, String userPw, String name, String birth, String phone, String email) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGuestNum() {
		return guestNum;
	}

	public void setGuestNum(int guestNum) {
		this.guestNum = guestNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double discount(int price) { // 일반회원가입의 경우 그냥 member로 들어오며 할인율은 없다.
		return price;// 할인율계산
	}
	@Override
	public int hashCode() {
		return Objects.hash(birth, email, guestNum, name, phone, userId, userPw);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(birth, other.birth) && Objects.equals(email, other.email) && guestNum == other.guestNum
				&& Objects.equals(name, other.name) && Objects.equals(phone, other.phone)
				&& Objects.equals(userId, other.userId) && Objects.equals(userPw, other.userPw);
	}
	
	
	
}
