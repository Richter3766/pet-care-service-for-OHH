package pd.systemuser;

public class Pet {
    private String petName;                 // 이름
    private int petAge;                     // 나이
    private String petKind;                 // 종류
    private String chronicDisease;          // 지병 여부
    private String kindOfFeed;              // 사료의 종류

	// init
    public Pet(String petName, int petAge, String petKind, String chronicDisease, String kindOfFeed){
        setPetName(petName);
        setPetAge(petAge);
        setPetKind(petKind);
        setChronicDisease(chronicDisease);
        setKindOfFeed(kindOfFeed);
    }

    // setter
    public void setPetName(String petName) {
        this.petName = petName;
    }
    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }
    public void setPetKind(String petKind) {
        this.petKind = petKind;
    }
    public void setChronicDisease(String chronicDisease) {
        this.chronicDisease = chronicDisease;
    }
    public void setKindOfFeed(String kindOfFeed) {
        this.kindOfFeed = kindOfFeed;
    }

	// getter
    public String getPetName() {
        return petName;
    }
    public int getPetAge() {
        return petAge;
    }
    public String getPetKind() {
        return petKind;
    }
    public String getChronicDisease() {
        return chronicDisease;
    }
    public String getKindOfFeed() {
        return kindOfFeed;
    }
    
}
