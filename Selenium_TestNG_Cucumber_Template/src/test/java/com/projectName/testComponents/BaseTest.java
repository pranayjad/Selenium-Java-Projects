package com.projectName.testComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//com//projectName//resources//GlobalData.properties");
        prop.load(fis);
        String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
        // above ternary operator is used to get browser value from Maven commnad line -Dbrowser=firefox using System.getProperty("browser")

        switch(browserName)
        {
            case "chrome":
            case "chromeheadless":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if(browserName.contains("headless"))
                    options.addArguments("headless");

                driver = new ChromeDriver(options);
                driver.manage().window().setSize(new Dimension(1440,900));
                break;
            case "firefox" :
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge" :
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        driver.quit();
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile,new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png"));
        return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    }

    // Read data from excel file
    public Object[][] getExcelDataToMap(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        DataFormatter formatter = new DataFormatter();

        XSSFWorkbook wb = new XSSFWorkbook(fis);

        XSSFSheet sheet = wb.getSheet(sheetName);
        int rowCount=sheet.getPhysicalNumberOfRows();

        XSSFRow row =sheet.getRow(0);
        int colCount=row.getLastCellNum();

        Object [][] data = new Object[rowCount-1][colCount];
        for(int i=0;i<rowCount-1;i++)
        {
            row=sheet.getRow(i+1);
            for(int j=0;j<colCount;j++)
            {
                XSSFCell cell=row.getCell(j);
                data[i][j]=formatter.formatCellValue(cell);
            }
        }
        return data;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath));

        //string to Hashmap jackson databind
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
    }
}
