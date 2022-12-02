package pd.application;

import java.util.Hashtable;
import java.util.ArrayList;

import db.*;

public final class ApplicationList {
    private static final ApplicationList list = new ApplicationList();
    private ApplicationList() {}

    private final Hashtable<String, Application> forAcceptTable =
            ForAcceptTable.getInstance().getHashTable();           // 수락 대기 테이블
    private final Hashtable<String, Application> forPaymentTable =
            ForPaymentTable.getInstance().getHashTable();         // 결제 대기 테이블
    private final Hashtable<String, Application> forActiveTable =
            ActiveTable.getInstance().getHashTable();                 // 진행중 테이블
    private final Hashtable<String, Application> forCompleteTable =
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
     * 상태가 "신청 대기"나 "결제 대기"에 있는 신청이 있는지 검사하고
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

    public String isExistInForPayment(String userID){
        for(String key:forPaymentTable.keySet()){
            if(key.split("-")[0].compareTo(userID) == 0){
                return key;
            }
        }
        return null;
    }
    
    //생성한 application Table 에 추가
    public void addForAccept(Application application){
        forAcceptTable.put(application.getApplicationID(), application);
    }
    public void moveForPaymentToAccept(String applicationID){
        Application application = forPaymentTable.get(applicationID);
        forPaymentTable.remove(applicationID);
        application.setState(0);
        forAcceptTable.put(applicationID, application);
    }
    //forAcceptTable 에서 forPaymentTable 로 이동
    public void moveForPayment(String applicationID) {
    	Application application = forAcceptTable.get(applicationID);
    	forAcceptTable.remove(applicationID);
    	application.setState(1);
    	forPaymentTable.put(applicationID, application);
    }
    //forPaymentTable 에서 forActiveTable 로 이동
    public void moveForActive(String applicationID) {
    	Application application = forPaymentTable.get(applicationID);
    	forPaymentTable.remove(applicationID);
    	application.setState(2);
    	forActiveTable.put(applicationID, application);
    }
    //forActiveTable 에서 forCompleteTable 로 이동
    public void moveForComplete(String applicationID) {
    	Application application = forActiveTable.get(applicationID);
    	forActiveTable.remove(applicationID);
    	application.setState(3);
    	forCompleteTable.put(applicationID, application);
    }
    //forAcceptTable 에서 삭제
    public void removeForAccept(String applicationID) {
    	forAcceptTable.remove(applicationID);
    }
    //forPaymentTable 에서 삭제
    public void removeForPayment(String applicationID) {
    	forPaymentTable.remove(applicationID);
    }
}
