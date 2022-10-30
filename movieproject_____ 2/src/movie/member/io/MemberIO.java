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

import movie.memeber.model.vo.Member;

public class MemberIO {

	private File memberFile = new File("MemberFile.ser");

	public List<Member> loadmemberList() {

		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(memberFile)));) {
			return (List<Member>) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveMemberList(List<Member> memberList) {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(memberFile)))) {
			oos.writeObject(memberList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
