package movie.member.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import movie.memeber.model.vo.Food;

public class FoodIO {

	private File FoodFile = new File("FoodFile.ser");
	
	
	public List<Food> foodLoadList(){
		
		try (ObjectInputStream ois = new ObjectInputStream (new BufferedInputStream(new FileInputStream(FoodFile)));
				){
			return (List<Food>)ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	public void saveFoodList(List<Food>orderList){
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(FoodFile)))) {
			oos.writeObject(orderList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

