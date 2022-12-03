package db.petsitter;

import java.util.Hashtable;
import pd.systemuser.PetSitter;

/*
 * PetSitterDM으로 불러온 PetSitter Hashtable을 불러와서 보관하는 고유한 싱글톤 클래스.
 * PetSitterTable.getInstance() 형태로 불러오면 어디서든 같은 내용의 PetSitterTable 사용가능.
 */
public class PetSitterTable
{
    private static PetSitterTable petsittertable;
    private Hashtable<String, PetSitter> petsitterhashtable;
    private PetSitterDM petsitterDM;
    
    private PetSitterTable() {
    	petsitterDM = new PetSitterDM("Table/PetSitterTable");
    	petsitterhashtable = petsitterDM.readObjectData();
		if(petsitterhashtable == null)
			petsitterhashtable = new Hashtable<>();
    }
    
    public static PetSitterTable getInstance()
    {
        if (petsittertable == null)
        {
            synchronized(PetSitterTable.class)
            {
                petsittertable = new PetSitterTable();
            }
        }
        return petsittertable;
    }

    //getter
	public Hashtable<String, PetSitter> getHashTable() {
		return petsitterhashtable;
	}

	//전달받은 petsitterhashtable 파일에 저장
	public void saveHashTable() {
		petsitterDM.deletObjectData();
		petsitterDM.writeObjectData(petsitterhashtable);
	}

}