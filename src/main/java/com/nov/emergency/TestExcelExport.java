package com.nov.emergency;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.nov.util.ExportExcel;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName TestExcelExport
 * @Description 测试导出
 * @Author qiaodeqian
 * @Date 2020/2/9 21:16
 * @Modify By
 * @Version 1.0
 */
public class TestExcelExport {

    public static void main(String[] args) throws Exception {
        // 判断数据，组装对象
        // 组织查询参数
        String param = "肯德基,麦当劳,12345,江苏西关行餐饮管理有限公司";
        String[] paramArray = param.split(",");
        UnitMsg unitMsg = new UnitMsg();
        // 接收查询结果，为空则系统中无，有则取第一条进行判断
        JSONArray unitJa;
        List<Object[]> dataList = Lists.newArrayList();
        String[] rowsName = new String[]{"序号","名称","登记", "信用代码", "法人代表", "营业执照","门头照片","室内照片","比对名称"};
        for (int i = 0; i < paramArray.length; i++) {
            Object[] obj = new Object[rowsName.length];
            obj[0] = i+1;
            obj[1] = paramArray[i];
            obj[2] = "否";
            obj[3] = "无";
            obj[4] = "无";
            obj[5] = "无";
            obj[6] = "无";
            obj[7] = "无";
            obj[8] = "无";
            unitJa = unitMsg.searchUnit(paramArray[i]);
            if (unitJa != null && unitJa.size() > 0){
                // 输出在控制台便于查看
                outputData(unitJa);
                // 取第一个进行比对
                obj[2] = "是";
                Object socialCredit = unitJa.getJSONObject(0).get("social_credit_code");
                obj[3] = socialCredit != null && StringUtils.isNotBlank(socialCredit.toString())? socialCredit.toString() : "无";
                Object legalPerson = unitJa.getJSONObject(0).get("legal_person");
                obj[4] = legalPerson != null && StringUtils.isNotBlank(legalPerson.toString())? legalPerson : "无";
                obj[5] = "无";
                obj[6] = "无";
                obj[7] = "无";
                obj[8] = unitJa.getJSONObject(0).get("unit_name");
            }
            dataList.add(obj);
        }

        for (Object[] objs : dataList) {
            System.out.println(Arrays.toString(objs));
        }
        // 创建工作簿对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        String title = "网格化数据判断结果";
        int exportParam = 2;
        for (int x = 0;x < exportParam; x++) {
            // 创建工作表
            HSSFSheet sheet = workbook.createSheet(title + x);
            ExportExcel ex = new ExportExcel(title, rowsName, dataList);
            ex.export(workbook,sheet,x);
        }
    }

    private static void outputData(JSONArray unitJa) {
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
            System.out.print("营业执照：" +
                    (unitJa.getJSONObject(i).get("business_license_paths") != null ? "有" : "无"));
            System.out.print("营业执照List：" +
                    (unitJa.getJSONObject(i).get("businessLicensePathList") != null ? "有" : "无"));
            System.out.print("环境照片List：" +
                    (unitJa.getJSONObject(i).get("siteEnvironmentPathList") != null ? "有" : "无"));
            System.out.println();
        }
    }
}
