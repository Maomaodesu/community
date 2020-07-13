package life.maomao.community.enums;

/**
 * Created by Maomao on 2020/7/8
 */
public enum  CommentTypeEnum {
    TOPIC(1),//对主题进行评论
    COMMENT(2);//对评论进行评论
    private Long type;

    CommentTypeEnum(long i) {

    }

    public void setType(Long type) {
        this.type = type;
    }

    public static boolean isExist(Long type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()){
            if(commentTypeEnum.getType() == type){
                return true;
            }
        }
        return false;
    }

    public Long getType(){
        return type;
    }
    CommentTypeEnum(Long type){
        this.type = type;
    }
}
