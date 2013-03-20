import java.util.Date;

public class Tweet {

    private Date currentDate;
    
    private String message;

    private static final int MAX_LENGTH = 140;

    public Tweet() {

	currentDate = new Date();
    }

    public static boolean isValidMessage(String message) {

	if( message.length() <= MAX_LENGTH && message.length() > 0 ) {
	    return true;
	}
	else {
	    return false;
	}

    }

    public void setMessage(String message) {

	this.message = message;
	
    }    

    public String getMessage() {
	return message;
    }

    public String toString() {
	return "(" + currentDate.toString() + ", " + message + ")";
    }

}