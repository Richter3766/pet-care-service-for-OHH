package pd.application;

import java.util.Scanner;

public class Application {
    private String applicationID;               // 신청 ID        형태: "userId-idIdx"
    private String periodOfService;             // 이용 시간
    private String location;                    // 위치
    private String kindOfServices;              // 신청 서비스 종류
    private String price;                          // 가격
    private String state;                       // 진행 상황
    static private int idIdx;                   // 신청 ID 생성을 위한 int 변수, 1씩 증가함
    private String petSitterID;					// 돌봄이의 정보를 열람하려면 회원에게 돌봄이 ID를 전달해야하는데 그 경로로 신청정보가 적합할 것 같아 돌봄이 ID 변수 추가
    String[] stateList = {"수락 대기", "결제 대기", "진행중", "완료"};
    // review 추가
    private String[] review = {"", "", ""};
	
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
        this.applicationID = userId + "-"+ Integer.toString(idIdx++);
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
     * 1. 신청 목록에 중복된 정보가 있으면 중복된 신청 ID 반환
     * 2. 아니면 신청 목록에 신청 정보를 등록한다
     */
    public String requestApplication(){
        ApplicationList list = ApplicationList.getList();
        String redundantID;

        redundantID = list.isExistInPresent(this.getUserID());

        // redundantID 가 null 이 아니면 redundantID 반환
        if(redundantID != null){
            return redundantID;
        }
        // 중복 정보가 없으면 신청 등록
        this.setState(0);
        list.addToPresent(this);
        return null;
    }

    /**
     * 신청 목록에 있는 중복 정보 삭제를 요청한다
     * @param redundantID 삭제할 신청 정보 ID
     */
    public void requestReplaceApplication(String redundantID){
        ApplicationList list = ApplicationList.getList();
        list.removeFromPresent(redundantID);
    }
}
