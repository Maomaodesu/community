package life.maomao.community.exception;
/**
 * created by Maomao on 2020/7/1.
 */
public enum  CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("这个问题没了，咱换个吧");
    @Override
    public String getMessage() {
        return message;
    }

    private String message;
    CustomizeErrorCode(String message){
        this.message = message;
    }
}
