package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import pd.application.Application;
import pd.application.ApplicationList;

/* ApplicationList 객체를 파일에 읽고 쓰는 클래스
 * ApplicationList 객체 자체를 파일에 저장
 * 정보 수정하려면 파일에서 ApplicationList를 가지고 와서 수정하고 기존 파일을 삭제하고 새로만들어야 함
 * 직렬화는 저장하려는 객체의 클래스에 Serializable가 implements되어 있어야함.
 */
public class ApplicationListDM {
	private File file; 				//파일클래스
	private ObjectOutputStream write; //파일에 객체의 정보를 입력하는 스트림
	private ObjectInputStream read; //파일에 저장되어있는 객체를 읽어오는 스트림
	
	//파일의 경로를 입력받고 스트림을 연결함
	public ApplicationListDM(String filePath) {
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
	//ApplicationList를 받은 다음 파일에 입력
	public void writeObjectData(ApplicationList a) {
		try {
			write.writeObject(a);
		} catch (IOException e) {
			System.out.print("file write false");
			e.printStackTrace();
		}
	}
	//파일에 있는 ApplicationList를 읽은 다음 ApplicationList return
	public ApplicationList readObjecfData() {
		try {
			ApplicationList temp = (ApplicationList)read.readObject();
			return temp;
		} catch (ClassNotFoundException e) {
			System.out.print("file read false");
		} catch (IOException e) {
			System.out.print("file read false");
		}
		return null;
	}
	//파일 삭제
	public void deletObjectData() {
		file.delete();
	}
	//스트림 close
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