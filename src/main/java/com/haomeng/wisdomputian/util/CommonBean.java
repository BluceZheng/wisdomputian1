package com.haomeng.wisdomputian.util;

import java.util.List;

public class CommonBean {

    private long id;
    private String name;
    private String pic;
    private int sort;
    private List<CommonBean> children;
    private long level;

    public CommonBean() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<CommonBean> getChildren() {
        return children;
    }

    public void setChildren(List<CommonBean> children) {
        this.children = children;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }
}
