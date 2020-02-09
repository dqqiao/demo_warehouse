package com.nov.emergency;

import com.alibaba.fastjson.JSONArray;
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

    public JSONArray searchUnit(String unitName){
        String httpPostUrl = url;
        String searchParamsStr = "{\"riskSourceRadio\":\"0\",\"industryRadio\":\"0\",\"exSupervisionRadio\":\"0\",\"supervisionRadio\":\"0\",\"realExistRadio\":\"0\",\"area\":\"\",\"street\":\"\",\"unit_name\":\"\",\"isSafety\":\"0\"}";
        JSONObject searchParamsJo = JSONObject.parseObject(searchParamsStr);
        searchParamsJo.put("unit_name",unitName);
        String httpPostUnitRtn = httpClientUtil.doPost(httpPostUrl,searchParamsJo.toJSONString(),charset);
        JSONObject unitDataJo = JSONObject.parseObject(httpPostUnitRtn);
        JSONArray unitArray = unitDataJo.getJSONArray("items");
        return unitArray.size() > 0 ? unitArray : null;
    }

    public static void main(String[] args) {
        UnitMsg unitMsg = new UnitMsg();
        unitMsg.searchUnit("肯德基");
    }


}
