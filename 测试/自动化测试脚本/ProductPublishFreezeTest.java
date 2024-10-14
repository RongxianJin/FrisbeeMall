package test;

import org.junit.AfterClass;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class ProductPublishFreezeTest {
	private static WebDriver driver;
	private String productName;        //商品名称
    private String productDescription;        //商品描述
    private String productUrl;        //商品图片url
    private String productPrice;        //商品价格
    

    
    public ProductPublishFreezeTest(String productName, String productDescription, String productUrl, String productPrice) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productUrl = productUrl;
		this.productPrice = productPrice;
	}

	@Parameterized.Parameters
    public static Collection<Object[]> testData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("D:\\opencsv\\productPublishFreeze.csv"));
 
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
    	driver.get("file:///D:/FrisbeeMall/FrisbeeMall-main/FrisbeeMall-main/frontpart/seller.html");

    	//填写待发布的商品信息
        driver.findElement(By.id("productName")).clear();
		driver.findElement(By.id("productName")).sendKeys(productName);
		driver.findElement(By.id("productDescription")).clear();
		driver.findElement(By.id("productDescription")).sendKeys(productDescription);
		driver.findElement(By.id("productImageUrl")).clear();
		driver.findElement(By.id("productImageUrl")).sendKeys(productUrl);
		driver.findElement(By.id("productPrice")).clear();
		driver.findElement(By.id("productPrice")).sendKeys(productPrice);
		
		//点击“添加商品”
		WebElement submitButton = driver.findElement(By.xpath("//button[text()='添加商品']"));
		submitButton.click();
	    
	    // 等待弹窗出现并点击确定
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.alertIsPresent());
	    Alert alert = driver.switchTo().alert();
	    assertEquals("商品添加成功", alert.getText()); // 验证弹窗文本
	    alert.accept(); // 点击确定


	    // 跳转到买家页面确认商品信息
	    driver.get("file:///D:/FrisbeeMall/FrisbeeMall-main/FrisbeeMall-main/frontpart/buyer.html");
		Thread.sleep(100);

	    // 验证商品名称
	    String productDetailsXPath = "//div[@id='productDetails']/h3";
	    WebElement productTitle = driver.findElement(By.xpath(productDetailsXPath));
	    String productName1 = productTitle.getText();
	    System.out.println("商品名称: " + productName1);
	    assertEquals("商品名称不匹配", productName, productName1);

	    // 验证商品图片url
	    String productImageXPath = "//div[@id='productDetails']/img";
	    WebElement imgElement = driver.findElement(By.xpath(productImageXPath));
	    String productUrl1 = imgElement.getAttribute("src");
	    System.out.println("商品图片url: " + productUrl1);
	    assertEquals("商品图片URL不匹配", productUrl, productUrl1);

	    // 验证商品描述
	    String productDescriptionXPath = "//div[@id='productDetails']/p[1]";
	    String productDescription1 = driver.findElement(By.xpath(productDescriptionXPath)).getText();
	    System.out.println("商品描述: " + productDescription1);
	    assertEquals("商品描述不匹配", productDescription, productDescription1);

	    // 验证商品价格
	    String productPriceXPath = "//div[@id='productDetails']/p[2]";
	    String productPriceText = driver.findElement(By.xpath(productPriceXPath)).getText();

	    // 去除 "价格: " 前缀
	    String pricePrefix = "价格: ";
	    if (productPriceText.startsWith(pricePrefix)) {
	        productPriceText = productPriceText.substring(pricePrefix.length()).trim();
	    }
	    System.out.println("商品价格: " + productPriceText);
	    assertEquals("商品价格不匹配", productPrice, productPriceText);

	    // 获取商品序号
	    String buyerFormButtonXPath = "//button[contains(@onclick, 'showBuyerForm')]";
	    WebElement buyerFormButton = driver.findElement(By.xpath(buyerFormButtonXPath));
	    String onclickAttribute = buyerFormButton.getAttribute("onclick");
	    String productIndex = onclickAttribute.substring(onclickAttribute.indexOf("(") + 1, onclickAttribute.indexOf(")"));
	    String freezeButtonXPath = String.format("//button[contains(@onclick, 'freezeProduct(%s)')]", productIndex);
	    
	    // 跳转到卖家页面
	    driver.get("file:///D:/FrisbeeMall/FrisbeeMall-main/FrisbeeMall-main/frontpart/seller.html");
	    WebElement freezeButton = driver.findElement(By.xpath(freezeButtonXPath));
	    assertNotNull("未找到冻结按钮", freezeButton);
	    freezeButton.click();

	    // 等待冻结操作完成
	    wait.until(ExpectedConditions.alertIsPresent());
	    alert = driver.switchTo().alert();
	    alert.accept(); // 点击确定

	    // 跳转到买家页面
	    driver.get("file:///D:/FrisbeeMall/FrisbeeMall-main/FrisbeeMall-main/frontpart/buyer.html");

	    // 验证是否显示“当前没有可购买的商品”
	    String noProductsMessageXPath = "//div[@id='productDetails']/p";
	    WebElement noProductsMessage = driver.findElement(By.xpath(noProductsMessageXPath));
	    assertEquals("当前没有可购买的商品", noProductsMessage.getText());

    }

}
