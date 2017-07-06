package org.tzl.baselibrary.bean;

public class PageInfo {

    /**
     * 每页数量.
     */
    private int pageSize = 20;

    /**
     * 第几页
     */
    private int pageNo=1;

    private static volatile PageInfo pageInfo=null;

    public PageInfo() {
    }

    public static PageInfo getInstance(){
        if (pageInfo==null){
            synchronized (PageInfo.class){
                if(pageInfo==null){
                    pageInfo=new PageInfo();
                }
            }
        }
        return pageInfo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
