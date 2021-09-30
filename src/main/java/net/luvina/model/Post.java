package net.luvina.model;

import java.sql.Timestamp;
import java.util.List;

public class Post extends BaseModel<Post> {
    private String title;
    private String thumbnail;
    private String shortDescription;
    private String content;
    private Long categoryId;
    private List<Category> listCategory;

    public Post() {
    }

    public Post(Long id) {
        super(id);
    }

    public Post(Long id, String title, String thumbnail, String shortDescription, String content, Long categoryId, Timestamp createdDate, String createdBy) {
        super(id, createdDate, createdBy);
        this.title = title;
        this.thumbnail = thumbnail;
        this.shortDescription = shortDescription;
        this.content = content;
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Category> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<Category> listCategory) {
        this.listCategory = listCategory;
    }
}
