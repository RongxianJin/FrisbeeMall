package test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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
import java.util.concurrent.TimeUnit;


@RunWith(Parameterized.class)
public class PasswordChangeTest {
	private static WebDriver driver;
	
	private String password; 

	public PasswordChangeTest(String password) {
		super();
		this.password = password;
	}

	@Parameterized.Parameters
    public static Collection<Object[]> testData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("D:\\opencsv\\passwordChange.csv"));
 
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
    public void pwdcgTest() throws Exception {
    	try {
    	driver.get("file:///D:/FrisbeeMall/FrisbeeMall-main/FrisbeeMall-main/frontpart/seller.html");

    	//填写新密码
        driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys(password);
		
		//点击“修改密码”
		WebElement submitButton = driver.findElement(By.xpath("//button[text()='修改密码']"));
		submitButton.click();
	    
	    // 等待弹窗出现
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.alertIsPresent());

        // 切换到弹窗并验证文本
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText().trim(); // 移除两端的空格
	    System.out.println(alertText);
        alert.accept(); // 点击确定关闭弹窗
        
        // 验证弹窗文本是否符合预期结果
        Assert.assertEquals("密码修改成功", alertText);

    } catch (Exception e) {
        e.printStackTrace();
        Assert.fail("测试失败：" + e.getMessage());
    }
  }
}
