package com.haomeng.wisdomputian.bean;

import lombok.Data;

@Data
public class SearchListPageParam {
    private String limit;
    private String startnum;
    private String endnum;
    private String pagenum;
}
