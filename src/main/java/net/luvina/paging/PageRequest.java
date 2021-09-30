package net.luvina.paging;

public class PageRequest implements Pageable {
    private Integer currenPage;
    private Integer limit;

    public PageRequest() {
    }

    public PageRequest(Integer currenPage, Integer limit) {
        this.currenPage = currenPage;
        this.limit = limit;
    }

    public Integer getCurrenPage() {
        return currenPage;
    }

    public void setCurrenPage(Integer currenPage) {
        this.currenPage = currenPage;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return (this.currenPage - 1) * this.limit;
    }
}
