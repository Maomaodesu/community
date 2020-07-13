package life.maomao.community.exception;
/**
 * created by Maomao on 2020/7/1.
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    TOPIC_NOT_FOUND(2001,"这个主题没了，咱换个吧"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何主题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试"),
    SYS_ERROR(2004,"服务器冒烟了，要不然你稍后试试？"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOTFOUND(2006,"恢复到评论不存在，要不要换个试试？"),
    ;
    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode( Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
