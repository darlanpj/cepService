package user.contato.Exception;

/**
 * Created by darlanpj on 02/04/2017.
 */

public class InvalidCepException extends Exception {

    /**
     * Serial uuid
     */
    private static final long serialVersionUID = 8008223647769926275L;

    public InvalidCepException() {
        // TODO Auto-generated constructor stub
    }

    public InvalidCepException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public InvalidCepException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public InvalidCepException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public InvalidCepException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}

