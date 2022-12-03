package pd.systemuser;

import java.util.ArrayList;
import java.util.Hashtable;

import db.member.MemberTable;

@SuppressWarnings("serial")
public class Member extends SystemUser{
	//static 으로 선언된 membertable
    private static Hashtable<String, Member> membertable =
    		MemberTable.getInstance().getHashTable();
    private String enterWay;            // 출입 방법
    private int numOfPet;               // 반려동물 수
    private ArrayList<Pet> pets;
    
    // init
    public Member(String name, String birth, int age, String address, String cellPhoneContact, String email,
    		String loginID, String password, String userID, String enterWay, int numOfPet) {
        super(name, birth, age, address, cellPhoneContact, email, loginID, password, userID);
        setEnterWay(enterWay);
        setNumOfPet(numOfPet);
        pets = new ArrayList<>();
        membertable.put(this.getUserID(), this);
    }

    //반려견 등록
    public void addPet(Pet pet) {
    	pets.add(pet);
    }
    // setter
    public void setEnterWay(String enterWay) {
        this.enterWay = enterWay;
    }
    public void setNumOfPet(int numOfPet) {
        this.numOfPet = numOfPet;
    }

    // getter
    public String getEnterWay() {
        return enterWay;
    }
    public int getNumOfPet() {
        return numOfPet;
    }
    public ArrayList<Pet> getPets(){
    	return pets;
    }
    
    //static인 membertable을 이용하는 함수들
    //userID로 멤버를 찾아서 반환
    public static Member getMember(String UserID) {
    	return membertable.get(UserID);

    }
    //userID로 멤버를 찾아서 삭제
    public void removeMember(String UserID) {
    	membertable.remove(UserID);
    }
    
}
