package com.nov.emergency;

import com.alibaba.fastjson.JSONObject;
import com.nov.util.HttpClientUtil;

/**
 * @ClassName UnitMsg
 * @Description 查询并处理
 * @Author qiaodeqian
 * @Date 2020/2/9 17:43
 * @Modify By
 * @Version 1.0
 */
public class UnitMsg {

    private String url = "https://njyjb.skyco2.com/spp_grid/unit_places_new?pageNum=1&pageSize=10";
    private String charset = "utf-8";
    private HttpClientUtil httpClientUtil = null;

    public UnitMsg(){
        httpClientUtil = new HttpClientUtil();
    }

    public void SearchUnit(String unitName){
        String httpOrgCreateTest = url;
        String searchParamsStr = "{\"riskSourceRadio\":\"0\",\"industryRadio\":\"0\",\"exSupervisionRadio\":\"0\",\"supervisionRadio\":\"0\",\"realExistRadio\":\"0\",\"area\":\"\",\"street\":\"\",\"unit_name\":\"\",\"isSafety\":\"0\"}";
        JSONObject searchParamsJo = JSONObject.parseObject(searchParamsStr);
        searchParamsJo.put("unit_name",unitName);
        String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,searchParamsJo.toJSONString(),charset);
        System.out.println(httpOrgCreateTestRtn);
    }






}
