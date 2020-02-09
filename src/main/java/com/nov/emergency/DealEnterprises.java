package com.nov.emergency;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nov.util.HttpClientUtil;

/**
 * @ClassName DealEnterprises
 * @Description 将数据筛选生成，复制到excel中，一次处理20条记录
 * @Author qiaodeqian
 * @Date 2020/2/7 11:35
 * @Modify By
 * @Version 1.0
 */
public class DealEnterprises {


    private HttpClientUtil httpClientUtil = new HttpClientUtil();

    public static void main(String[] args) {

        String rawUnitData = "{}";
        JSONObject rawJson = JSONObject.parseObject(rawUnitData);
        JSONArray unitJa = rawJson.getJSONArray("items");
        for (int i = 0; i < unitJa.size(); i++) {
            // 单位名称
            System.out.println(unitJa.getJSONObject(i).get("unit_name"));
        }
        System.out.println("============================================================");

        for (int i = 0; i < unitJa.size(); i++) {
            System.out.println("------------------------------------");
            System.out.print("第"+ (i+1) + "条");
            // 单位名称
            System.out.print("单位名称：");
            System.out.println(unitJa.getJSONObject(i).get("unit_name"));

            // 实际经营地址
            System.out.print("经营地址：");
            System.out.print(unitJa.getJSONObject(i).get("unit_address"));
            System.out.println();
            // 定位地址
            System.out.print("定位地址：");
            System.out.print(unitJa.getJSONObject(i).get("current_address"));
            System.out.println();
            // 注册地址
            System.out.print("注册地址：");
            System.out.println(unitJa.getJSONObject(i).get("registered_address"));
            System.out.println();

            // 统一社会信用代码
            System.out.print("统一社会信用代码为：");
            System.out.print(unitJa.getJSONObject(i).get("social_credit_code"));
            System.out.println();
            // 法人代表
            System.out.print("法人代表：");
            System.out.print(unitJa.getJSONObject(i).get("legal_person"));
            System.out.println();

            // 环境照片
            System.out.print("环境照片：" +
                    (unitJa.getJSONObject(i).get("site_environment_paths") != null ? "有" : "无"));
            System.out.println();
        }
    }

    public String httpPostUnitData(){
        String url = "https://njyjb.skyco2.com/spp_grid/unit_places_new?pageNum=1&pageSize=10";
        String charset = "utf-8";
        String jsonString = "{\"riskSourceRadio\":\"0\",\"industryRadio\":\"0\",\"exSupervisionRadio\":\"0\",\"supervisionRadio\":\"0\",\"realExistRadio\":\"0\",\"area\":\"320115\",\"street\":\"320115402\",\"unit_name\":\"\",\"isSafety\":\"0\"}";
        String httpOrgCreateTestRtn = httpClientUtil.doPost(url,jsonString,charset);
        System.out.println(httpOrgCreateTestRtn);
        return httpOrgCreateTestRtn;
    }
}
