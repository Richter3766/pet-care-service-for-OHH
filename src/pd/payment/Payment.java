package pd.payment;

import pd.application.Application;
import pd.application.ApplicationList;

public class Payment {
    private String account;
    private String accountPassword;
    private String valid;
    private String birth;

    ApplicationList list = ApplicationList.getList();
    Application application;
    
    public Payment() {
    	
    }
    
    public Payment(String account, String accountPassword, String valid, String birth) {
    	setAccount(account);
    	setAccountPassword(accountPassword);
    	setValid(valid);
    	setBirth(birth);
    }
    
    public void Pay(String applicationID) {
    	list.moveForActive(applicationID);
    }
    
    public void setAccount(String account) {
        this.account = account;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getAccount() {
        return account;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public String getBirth() {
        return birth;
    }

    public String getValid() {
        return valid;
    }
}