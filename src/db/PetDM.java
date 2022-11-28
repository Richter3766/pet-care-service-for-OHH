package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import pd.systemuser.Pet;

/* Pet 객체를 파일에 읽고 쓰는 클래스
 * Pet 객체 자체를 파일에 저장
 * 정보 수정하려면 파일에서 Pet 객체 가지고 와서 수정하고 기존 파일을 삭제하고 새로만들어야 함
 * 직렬화는 저장하려는 객체의 클래스에 Serializable가 implements되어 있어야함.
 */
public class PetDM {
	private File file; //파일 클래스
	private ObjectOutputStream write; //파일에 객체의 정보를 입력하는 스트림
	private ObjectInputStream read; //파일에 저장되어있는 객체를 읽어오는 스트림
	
	public PetDM(String filePath) {
		file = new File(filePath);
		String fileName = file.getName();
		try {
			this.write = new ObjectOutputStream(new FileOutputStream(fileName));
			this.read = new ObjectInputStream(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("stream false");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("stream false");
			e.printStackTrace();
		}
	}
	public void writeObjectData(Pet a) {
		try {
			write.writeObject(a);
		} catch (IOException e) {
			System.out.print("file write false");
			e.printStackTrace();
		}
	}
	public Pet readObjecfData() {
		try {
			Pet temp = (Pet)read.readObject();
			return temp;
		} catch (ClassNotFoundException e) {
			System.out.print("file read false");
		} catch (IOException e) {
			System.out.print("file read false");
		}
		return null;
	}
	public void deletObjectData() {
		file.delete();
	}
	public void close() {
		try {
			write.close();
		} catch (IOException e) {
			System.out.println("write close False");
			e.printStackTrace();
		}
		try {
			read.close();
		} catch (IOException e) {
			System.out.println("read close False");
			e.printStackTrace();
		}
	}
}