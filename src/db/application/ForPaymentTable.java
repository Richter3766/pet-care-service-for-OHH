package db.application;

import java.util.Hashtable;

import pd.application.Application;

/*
 * ForPaymentDM으로 불러온 ForPayment Hashtable을 불러와서 보관하는 고유한 싱글톤 클래스.
 * ForPaymentTable.getInstance() 형태로 불러오면 어디서든 같은 내용의 ForPaymentTable 사용가능.
 */
public class ForPaymentTable
{
    private static ForPaymentTable forpaymenttable;
    private Hashtable<String, Application> forpaymenthashtable;
    private ForPaymentDM forpaymentDM;
    
    private ForPaymentTable() {
    	forpaymentDM = new ForPaymentDM("Table/ForPaymentTable");
    	forpaymenthashtable = forpaymentDM.readObjectData();
		if(forpaymenthashtable == null)
			forpaymenthashtable = new Hashtable<>();
    }
    
    public static ForPaymentTable getInstance()
    {
        if (forpaymenttable == null)
        {
            synchronized(ForPaymentTable.class)
            {
                forpaymenttable = new ForPaymentTable();
            }
        }
        return forpaymenttable;
    }
    
    //getter
	public Hashtable<String, Application> getHashTable() {
   		return forpaymenthashtable;
	}
	
	//전달받은 forpaymenthashtable 파일에 저장
	public void saveHashTable() {
		forpaymentDM.deletObjectData();
		forpaymentDM.writeObjectData(forpaymenthashtable);
	}

}