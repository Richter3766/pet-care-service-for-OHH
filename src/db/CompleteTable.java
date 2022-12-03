package db;

import java.util.Hashtable;

import pd.application.Application;

/*
 * CompleteDM으로 불러온 Complete Hashtable을 불러와서 보관하는 고유한 싱글톤 클래스.
 * CompleteTable.getInstance() 형태로 불러오면 어디서든 같은 내용의 CompleteTable 사용가능.
 */
public class CompleteTable
{
    private static CompleteTable completetable;
    private Hashtable<String, Application> completehashtable;
    private CompleteDM completeDM;
    
    private CompleteTable() {
    	completeDM = new CompleteDM("Table/CompleteTable");
    	completehashtable = completeDM.readObjectData();
		if(completehashtable == null)
			completehashtable = new Hashtable<>();
    }
    
    public static CompleteTable getInstance()
    {
        if (completetable == null)
        {
            synchronized(CompleteTable.class)
            {
                completetable = new CompleteTable();
            }
        }
        return completetable;
    }

    //getter
	public Hashtable<String, Application> getHashTable() {
   		return completehashtable;
	}
	//전달받은 completehashtable 파일에 저장
	public void saveHashTable() {
		completeDM.deletObjectData();
		completeDM.writeObjectData(completehashtable);
	}

}