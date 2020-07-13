package life.maomao.community.dto;

import lombok.Data;

/**
 * Created by Maomao on 2020/7/5
 */
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Long type;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}
