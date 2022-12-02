package db;

import java.util.Hashtable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import pd.application.Application;

/* Complete (완료, 리뷰대기?) Hashtable 객체를 파일에 읽고 쓰는 클래스
 * Complete Hashtable을 파일에 저장
 * 직렬화는 저장하려는 객체의 클래스에 Serializable가 implements되어 있어야함.
 */
public class CompleteDM {
	private File file;   				//파일 class
	private ObjectOutputStream write; //파일에 객체의 정보를 입력하는 스트림
	private ObjectInputStream read; //파일에 저장되어있는 객체를 읽어오는 스트림
	
	public CompleteDM(String filePath) {
		file = new File(filePath);
		try {
			this.write = new ObjectOutputStream(new FileOutputStream(filePath));
			this.read = new ObjectInputStream(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			System.out.println("stream false");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("stream false");
			e.printStackTrace();
		}
	}
	public void writeObjectData(Hashtable<String, Application> a) {
		try {
			write.writeObject(a);
		} catch (IOException e) {
			System.out.println("file write false");
			e.printStackTrace();
		}
	}
	public Hashtable<String, Application> readObjectData() {
		try {
			Hashtable<String, Application> temp = (Hashtable<String, Application>)read.readObject();
			return temp;
		} catch (ClassNotFoundException e) {
			System.out.println("file read false");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("file read false");
//			e.printStackTrace();
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