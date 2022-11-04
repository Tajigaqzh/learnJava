package com.haiping;

/**
 * @author tony
 * @version 1.0
 * @date 2022-11-04 08:46
 */
public class PageQuery {
    private int startIndex;
    private int pageSize;

    public PageQuery() {
    }

    public PageQuery(int startIndex, int pageSize) {
        this.startIndex = startIndex;
        this.pageSize = pageSize;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
