package db;

import java.util.Hashtable;

import pd.application.Application;

/*
 * ForAcceptDM으로 불러온 ForAccept Hashtable을 불러와서 보관하는 고유한 싱글톤 클래스.
 * ForAcceptTable.getInstance() 형태로 불러오면 어디서든 같은 내용의 ForAcceptTable 사용가능.
 */
public class ForAcceptTable
{
    private static ForAcceptTable foraccepttable;
    private Hashtable<String, Application> foraccepthashtable;
    private ForAcceptDM foracceptDM;
    
    private ForAcceptTable() {
    	foracceptDM = new ForAcceptDM("Table/ForAcceptTable");
    	foraccepthashtable = foracceptDM.readObjectData();
		if(foraccepthashtable == null)
			foraccepthashtable = new Hashtable<>();
    }
    
    public static ForAcceptTable getInstance()
    {
        if (foraccepttable == null)
        {
            synchronized(ForAcceptTable.class)
            {
                foraccepttable = new ForAcceptTable();
            }
        }
        return foraccepttable;
    }
    
    //getter
	public Hashtable<String, Application> getHashTable() {
   		return foraccepthashtable;
	}
	
	//전달받은 foaccepthashtable 파일에 저장
	public void saveHashTable(Hashtable<String, Application> foraccepthashtable) {
		foracceptDM.deletObjectData();
		foracceptDM.writeObjectData(foraccepthashtable);
	}

}