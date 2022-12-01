package db;

import java.util.Hashtable;

import pd.application.Application;

/*
 * ActiveDM으로 불러온 Active Hashtable을 불러와서 보관하는 고유한 싱글톤 클래스.
 * ActiveTable.getInstance() 형태로 불러오면 어디서든 같은 내용의 ForPaymentTable 사용가능.
 */
public class ActiveTable
{
    private static ActiveTable activetable;
    private Hashtable<String, Application> activehashtable;
    private ActiveDM activeDM;
    
    private ActiveTable() {
    	activeDM = new ActiveDM("Table/ActiveTable");
    	activehashtable = activeDM.readObjectData();
		if(activehashtable == null)
			activehashtable = new Hashtable<>();
    }
    
    public static ActiveTable getInstance()
    {
        if (activetable == null)
        {
            synchronized(ActiveTable.class)
            {
                activetable = new ActiveTable();
            }
        }
        return activetable;
    }

    //getter
	public Hashtable<String, Application> getHashTable() {
   		return activehashtable;
	}
	
	//파일에 전달받은 activehashtable 저장
	public void saveHashTable(Hashtable<String, Application> activehashtable) {
		activeDM.deletObjectData();
		activeDM.writeObjectData(activehashtable);
	}
}