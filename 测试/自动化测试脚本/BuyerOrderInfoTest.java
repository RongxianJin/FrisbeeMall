package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class BuyerOrderInfoTest {
	private static WebDriver driver;
	private String buyerID;        //用户ID
    private String buyerName;        //用户姓名
    private String phoneNumber;        //用户电话
    private String exp;
    
	public BuyerOrderInfoTest(String buyerID, String buyerName, String phoneNumber, String exp) {
		super();
		this.buyerID = buyerID;
		this.buyerName = buyerName;
		this.phoneNumber = phoneNumber;
		this.exp = exp;
	}

	@Parameterized.Parameters
    public static Collection<Object[]> testData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("D:\\opencsv\\buyerOrderInfo.csv"));
 
        //reader.readLine();

        Collection<Object[]> data = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
        	String[] values = line.split(",");
            System.out.println("Read data: " + Arrays.toString(values)); // Log the read data
            data.add(values);
        }

        reader.close();

        return data;
    }
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception   {
        System.setProperty("webdriver.gecko.driver", "C:/driver/geckodriver.exe/");
        driver = new FirefoxDriver(); 
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    	driver.quit();
    }

    @Test
    public void pubFreTest() throws Exception {
    	driver.get("file:///D:/FrisbeeMall/FrisbeeMall-main/FrisbeeMall-main/frontpart/buyer.html");
    	
    	Thread.sleep(100);
    	
		//点击“我要购买”
		WebElement buyButton = driver.findElement(By.xpath("//button[text()='我要购买']"));
		buyButton.click();
		

    	//填写个人信息
        driver.findElement(By.id("buyerId")).clear();
		driver.findElement(By.id("buyerId")).sendKeys(buyerID);
		driver.findElement(By.id("buyerName")).clear();
		driver.findElement(By.id("buyerName")).sendKeys(buyerName);
		driver.findElement(By.id("buyerPhone")).clear();
		driver.findElement(By.id("buyerPhone")).sendKeys(phoneNumber);
		
		//点击“提交”
		WebElement submitButton = driver.findElement(By.xpath("//button[text()='提交']"));
		submitButton.click();
	    
	    // 等待弹窗出现并点击确定
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.alertIsPresent());
	    Alert alert = driver.switchTo().alert();
	    assertEquals("购买信息已提交，请等待卖家联系", alert.getText()); // 验证弹窗文本
	    alert.accept(); // 点击确定

	    //跳转到卖家页面
	    driver.get("file:///D:/FrisbeeMall/FrisbeeMall-main/FrisbeeMall-main/frontpart/seller.html");

	    System.out.println("测试通过");
    }

}
