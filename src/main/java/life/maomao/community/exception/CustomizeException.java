package life.maomao.community.exception;
/**
 * created by Maomao on 2020/7/1.
 */
public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message){
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
