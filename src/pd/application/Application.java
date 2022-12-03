package pd.application;

import java.io.Serializable;

public class Application implements Serializable{
    private String applicationID;               // 신청 ID        형태: "userId-idIdx"
    private String periodOfService;             // 이용 시간
    private String location;                    // 위치
    private String kindOfServices;              // 신청 서비스 종류
    private String price;                       // 가격
    private String state;                       // 진행 상황
    private String[] review = {"", "", ""};     // 리뷰 정보
    
    
    static private int idIdx;                   // 신청 ID 생성을 위한 int 변수, 1씩 증가함
    private String petSitterID;					// 돌봄이의 정보를 열람하려면 회원에게 돌봄이 ID를 전달해야하는데 그 경로로 신청정보가 적합할 것 같아 돌봄이 ID 변수 추가
    String[] stateList = {"수락 대기", "결제 대기", "진행중", "완료"};
    private static final ApplicationList list = ApplicationList.getList();
    // review 추가
  
	
	public Application(){
        this.applicationID = "";
        this.periodOfService = "";
        this.location = "";
        this.kindOfServices = "";
        this.price = "";
        this.state = stateList[0];

    }
    // setter

    /**
     * applicationID 는 "userId-idIdx" 의 형태를 가진다
     * @param userId 사용자 ID
     */
    public void setApplicationID(String userId) {
        this.applicationID = userId + "-"+ idIdx++;
    }
    public void setPeriodOfService(String periodOfService) {
        this.periodOfService = periodOfService;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setKindOfServices(String kindOfServices) {
        this.kindOfServices = kindOfServices;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setState(int index) {
        this.state = stateList[index];
    }
    public void setPetSitterID(String petSitterID) {
		this.petSitterID = petSitterID;
	}
    // 리뷰신청
    public void setReview(String score, String title, String content) {
    	this.review[0] = score;
    	this.review[1] = title;
    	this.review[2] = content;
    }

// getter

    public String getApplicationID() {
        return applicationID;
    }
    public String getPeriodOfService() {
        return periodOfService;
    }
    public String getLocation() {
        return location;
    }
    public String getKindOfServices() {
        return kindOfServices;
    }
    public String getPrice() {
        return price;
    }
    public String getState() {
        return state;
    }
    public String getUserID(){
        return this.applicationID.split("-")[0];
    }
    public String getPetSitterID() {
		return petSitterID;
	}
    public String[] getReview() {
    	return review;
    }

    /**
     * 신청 정보를 신청 목록에 등록한다
     */
    public void requestApplication(){
        this.setState(0);                   // '수락 대기'
        list.addForAccept(this);
    }

    /**
     * 수락대기 상태에 있는 중복 정보 삭제를 요청한다
     * @param redundantID 삭제할 신청 정보 ID
     */
    public void requestRemoveAccept(String redundantID){
        if(redundantID != null){
            list.removeForAccept(redundantID);
        }

    }
    /**
     * 결제 대기 상태에 있는 중복 정보 삭제를 요청한다
     * @param redundantID 삭제할 신청 정보 ID
     */
    public void requestRemovePayment(String redundantID){
        if(redundantID != null){
            list.removeForPayment(redundantID);
        }
    }

    /**
     * 수락 대기 상태에 있는 중복 정보가 있는지 찾아 반환한다.
     */
    public String requestIsRedundantInAccept(){
        return list.isExistInForAccept(this.getUserID());
    }
    /**
     * 결제 대기 상태에 있는 중복 정보가 있는지 찾아 반환한다.
     */
    public String requestIsRedundantInPayment(){
        return list.isExistInForPayment(this.getUserID());
    }
}
