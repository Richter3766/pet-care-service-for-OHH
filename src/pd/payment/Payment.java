package pd.payment;

import pd.application.Application;

public class Payment {
    private String account;
    private String accountPassword;
    private String valid;
    private String birth;

    public Payment() {
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