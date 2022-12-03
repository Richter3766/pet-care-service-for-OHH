package pd.payment;

public class Payment {
    private String cardnum;
    private String password;
    private String cvc;
    private String birth;

    public Payment(String cardnum, String password, String valid, String birth) {
    	this.cardnum = cardnum;
    	this.password = password;
    	this.cvc = valid;
    	this.birth = birth;
    }
    
    public static boolean CheckValid(Payment payment) {
    	if(payment.cardnum.length() == 15 || payment.cardnum.length() == 16)
    		if(payment.password.length() == 4)
    			if(payment.cvc.length() == 3)
    				if(payment.birth.length() == 4)
    					return true;
    	return false;
    }
}