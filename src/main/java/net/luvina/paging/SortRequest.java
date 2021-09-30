package net.luvina.paging;

public class SortRequest implements Sortable {
    private String sortBy;

    public SortRequest() {
    }

    public SortRequest(String sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public String getSortName() {
        return sortBy != null && sortBy.length() > 1 ? sortBy.substring(1) : null;
    }

    @Override
    public String getSortType() {
        return sortBy != null && sortBy.length() > 1 ? sortBy.substring(0, 1).equals("+") ? "ASC" : "DESC" : null;
    }
}
