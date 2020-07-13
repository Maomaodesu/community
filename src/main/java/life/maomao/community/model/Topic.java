package life.maomao.community.model;

public class Topic {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic.id
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic.title
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic.gmt_create
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic.gmt_modified
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic.creator_id
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    private Long creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic.comment_count
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    private Long commentCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic.view_count
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    private Long viewCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic.like_count
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    private Long likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic.dislike_count
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    private Long dislikeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic.tag
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    private String tag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic.description
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic.id
     *
     * @return the value of topic.id
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic.id
     *
     * @param id the value for topic.id
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic.title
     *
     * @return the value of topic.title
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic.title
     *
     * @param title the value for topic.title
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic.gmt_create
     *
     * @return the value of topic.gmt_create
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic.gmt_create
     *
     * @param gmtCreate the value for topic.gmt_create
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic.gmt_modified
     *
     * @return the value of topic.gmt_modified
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic.gmt_modified
     *
     * @param gmtModified the value for topic.gmt_modified
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic.creator_id
     *
     * @return the value of topic.creator_id
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public Long getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic.creator_id
     *
     * @param creatorId the value for topic.creator_id
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic.comment_count
     *
     * @return the value of topic.comment_count
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public Long getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic.comment_count
     *
     * @param commentCount the value for topic.comment_count
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic.view_count
     *
     * @return the value of topic.view_count
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public Long getViewCount() {
        return viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic.view_count
     *
     * @param viewCount the value for topic.view_count
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic.like_count
     *
     * @return the value of topic.like_count
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public Long getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic.like_count
     *
     * @param likeCount the value for topic.like_count
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic.dislike_count
     *
     * @return the value of topic.dislike_count
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public Long getDislikeCount() {
        return dislikeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic.dislike_count
     *
     * @param dislikeCount the value for topic.dislike_count
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public void setDislikeCount(Long dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic.tag
     *
     * @return the value of topic.tag
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic.tag
     *
     * @param tag the value for topic.tag
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic.description
     *
     * @return the value of topic.description
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic.description
     *
     * @param description the value for topic.description
     *
     * @mbg.generated Wed Jul 08 16:09:33 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}