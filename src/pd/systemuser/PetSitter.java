package pd.systemuser;

import java.util.Hashtable;

import db.petsitter.PetSitterTable;

@SuppressWarnings("serial")
public class PetSitter extends SystemUser{
	//static 으로 선언된 petsittertable
	private static Hashtable<String, PetSitter> petsittertable =
    		PetSitterTable.getInstance().getHashTable();             
    private int numOfCertificate;                                 // 자격증 수
    private String[] certificate = new String[5];  // 소지 자격증
    
    
    // init
    public PetSitter(String name, String birth, int age, String address, String cellPhoneContact, String email,
    		String loginID, String password, String userID, String certificate) {
    	super(name, birth, age, address, cellPhoneContact, email, loginID, password, userID);
        setNumOfCertificate(0);
        setCertificate(certificate);
        petsittertable.put(this.getUserID(), this);
    }

    // setter
    public void setCertificate(String certificate) {
        this.certificate[this.numOfCertificate++] = certificate;
    }
    public void setNumOfCertificate(int numOfCertificate) {
        this.numOfCertificate = numOfCertificate;
    }

    // getter
    public String getCertificate() {
        return String.join(", ", certificate);
    }
    public int getNumOfCertificate() {
        return numOfCertificate;
    }
    
    //static인 petsittertable을 이용하는 함수들
    //userID로 멤버를 찾아서 반환
    public static PetSitter getPetSitter(String UserID) {
    	return petsittertable.get(UserID);

    }
    //userID로 멤버를 찾아서 삭제
    public void removePetSitter(String UserID) {
    	petsittertable.remove(UserID);
    }
}
