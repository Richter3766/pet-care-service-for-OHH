package pd.application;

import java.util.Hashtable;
import java.util.ArrayList;

import db.*;

public final class ApplicationList {
    private static ApplicationList list = new ApplicationList();
    private ApplicationList() {}

    private static Hashtable<String, Application> forAcceptTable =
            ForAcceptTable.getInstance().getHashTable();           // 수락 대기 테이블
    private static Hashtable<String, Application> forPaymentTable =
            ForPaymentTable.getInstance().getHashTable();         // 결제 대기 테이블
    private static Hashtable<String, Application> forActiveTable =
            ActiveTable.getInstance().getHashTable();                 // 진행중 테이블
    private static Hashtable<String, Application> forCompleteTable =
            CompleteTable.getInstance().getHashTable();             // 완료 테이블
    
    public static ApplicationList getList(){
        return list;
    }

    // getter
    public Hashtable<String, Application> getForAcceptTable() {
        return forAcceptTable;
    }

    public Hashtable<String, Application> getForPaymentTable() {
        return forPaymentTable;
    }

    public Hashtable<String, Application> getForActiveTable() {
        return forActiveTable;
    }

    public Hashtable<String, Application> getForCompleteTable() {
        return forCompleteTable;
    }

	/**
     * 사용자 ID를 input 으로 받아
     * 상태가 "신청 대기"에 있는 신청이 있는지 검사하고
     * 있으면 그 신청의 ID, 없으면 null 을 반환
     * @param userID 사용자 ID
     * @return key or null
     */
    public String isExistInForAccept(String userID){
        for(String key:forAcceptTable.keySet()){
            if(key.split("-")[0].compareTo(userID) == 0){
                return key;
            }
        }
        return null;
    }
    /**
     * 사용자 ID를 input 으로 받아
     * 상태가 "결제 대기"에 있는 신청이 있는지 검사하고
     * 있으면 그 신청의 ID, 없으면 null 을 반환
     * @param userID 사용자 ID
     * @return key or null
     */
    public String isExistInForPayment(String userID){
        for(String key:forPaymentTable.keySet()){
            if(key.split("-")[0].compareTo(userID) == 0){
                return key;
            }
        }
        return null;
    }

    /**
     * 인자로 받은 신청 정보를 테이블에 등록한다.
     * @param application 등록할 신청 정보
     */
    public void addForAccept(Application application){
        forAcceptTable.put(application.getApplicationID(), application);
    }

    /**
     * 수락 취소된 신청 ID 를 인자로 받아
     * 상태를 '수락 대기' 로 만들고
     * 결제 테이블에서 삭제,
     * 수락 테이블에 추가
     * @param applicationID 수락 취소랄 신청 ID
     */
    public void moveForPaymentToAccept(String applicationID){
        Application application = forPaymentTable.get(applicationID);
        forPaymentTable.remove(applicationID);
        application.setState(0);
        forAcceptTable.put(applicationID, application);
    }

    /**
     * 수락된 신청 ID 를 인자로 받아
     * 상태를 '결제 대기' 로 만들고
     * 수락 테이블에서 삭제,
     * 결제 테이블에 추가
     * @param applicationID 수락된 신청 ID
     */
    public void moveForPayment(String applicationID) {
    	Application application = forAcceptTable.get(applicationID);
    	forAcceptTable.remove(applicationID);
    	application.setState(1);
    	forPaymentTable.put(applicationID, application);
    }
    /**
     * 결제된 신청 ID 를 인자로 받아
     * 상태를 '진행중' 로 만들고
     * 결제 테이블에서 삭제,
     * 진행중 테이블에 추가
     * @param applicationID 결제된 신청 ID
     */
    public void moveForActive(String applicationID) {
    	Application application = forPaymentTable.get(applicationID);
    	forPaymentTable.remove(applicationID);
    	application.setState(2);
    	forActiveTable.put(applicationID, application);
    }
    //forActiveTable 에서 forCompleteTable 로 이동
    /**
     * 완료된 신청 ID 를 인자로 받아
     * 상태를 '완료' 로 만들고
     * 진행중 테이블에서 삭제,
     * 완료 테이블에 추가
     * @param applicationID 완료된 신청 ID
     */
    public void moveForComplete(String applicationID) {
    	Application application = forActiveTable.get(applicationID);
    	forActiveTable.remove(applicationID);
    	application.setState(3);
    	forCompleteTable.put(applicationID, application);
    }

    /**
     * 인자로 받은 ID 값으로 신청 정보를 '신청 정보' 테이블에서 삭제한다
     * @param applicationID 삭제할 신청 정보
     */
    public void removeForAccept(String applicationID) {
    	forAcceptTable.remove(applicationID);
    }
    /**
     * 인자로 받은 ID 값으로 신청 정보를 '결제 대기' 테이블에서 삭제한다
     * @param applicationID 삭제할 신청 정보
     */
    public void removeForPayment(String applicationID) {
    	forPaymentTable.remove(applicationID);
    }
}
