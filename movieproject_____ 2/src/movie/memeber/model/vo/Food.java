package movie.memeber.model.vo;

import java.io.Serializable;
import java.util.Objects;

public class Food implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private int price;
	private String number;
	private int count;
	
	public Food() {
		super();
		
	}
	public Food( String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	
	public Food(String no, String name, int price) {
		super();
		this.name = name;
		this.price = price;
		this.number = no;
	}
	
	public Food(String no, String name, int price, int count) {
		super();
		this.name = name;
		this.price = price;
		this.number = no;
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getNumber() {
		return number;
	}

	public void setNo(String number) {
		this.number = number;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(count, name, number, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
		return count == other.count && Objects.equals(name, other.name) && Objects.equals(number, other.number)
				&& price == other.price;
	}

	@Override
	public String toString() {
		return number +"." + " " + name + " " + price+"원";
	}

	
	
}
