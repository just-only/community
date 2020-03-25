package com.example.community.demo;

/**
 * Package: com.example.community.demo
 * Description：
 * Author: weidongya
 * Date:  2020/3/24 16:26
 * Modified By:
 */
public class Question {
    private String description;
    private String title;
    private Long create_time;
    private Long modified_time;
    private int comment_count;
    private int creator;
    private int view_count;
    private int like_count;
    private String tag;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Long getModified_time() {
        return modified_time;
    }

    public void setModified_time(Long modified_time) {
        this.modified_time = modified_time;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Question{" +
                "description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", create_time=" + create_time +
                ", modified_time=" + modified_time +
                ", comment_count=" + comment_count +
                ", creator='" + creator + '\'' +
                ", view_count=" + view_count +
                ", like_count=" + like_count +
                ", tag='" + tag + '\'' +
                '}';
    }
}
