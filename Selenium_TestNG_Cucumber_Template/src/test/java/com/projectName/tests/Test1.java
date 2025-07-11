package com.projectName.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import com.projectName.testComponents.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Test1 extends BaseTest{

    @Test(groups={"Scenario"}, dataProvider = "getExcelData", priority = 1, description = "Test Case ID and Name")
    public void testCase()
    {

    }

    @DataProvider
    public Object[][] getJSONData() throws IOException {
        List<HashMap<String,String>> mapData=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//com//projectName//data//data.json");
        return new Object[][] {{mapData.get(0)}};
    }

    @DataProvider
    public Object[][] getExcelData() throws IOException {
        Object [][] data = getExcelDataToMap(System.getProperty("user.dir")+"//src//test//java//com//projectName//data//data.xlsx","Sheet1");
        return data;
    }
}
