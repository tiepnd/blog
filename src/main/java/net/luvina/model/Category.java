package net.luvina.model;

public class Category extends BaseModel{
    private String name;
    private String code;

    public Category() {
    }

    public Category(Long id,String name, String code) {
        super(id);
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
