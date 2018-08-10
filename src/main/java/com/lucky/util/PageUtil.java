package com.lucky.util;

public class PageUtil {

    //当前页码
    public int pageIndex;

    //页大小
    public int pageSize;

    //总页数
    public int pageCount;

    //总记录数
    public int totalCount;



    public void setTotalCount(int totalCount) {
        if(totalCount>0){

            this.totalCount=totalCount;

            //总页数 =（总记录数%页大小==0）？（总记录数/页大小）:（总记录数/页大小+1）;
            this.pageCount=(this.totalCount%this.pageSize==0)?
                            (this.totalCount/this.pageSize):(this.totalCount/this.pageSize+1);
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public PageUtil() {

    }

    public PageUtil(int pageIndex, int pageSize, int pageCount, int totalCount) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", pageCount=" + pageCount +
                ", totalCount=" + totalCount +
                '}';
    }



}
