

import static org.junit.Assert.assertEquals;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import edu.log.LogFactory;


public class TestLoginLogout {
	private WebDriver driver;
	private static final Logger logger = LogFactory.getLogger();
	private WebDriverWait wait;
@Before
	public void setUp() {
			logger.info("connect...");
			System.setProperty("webdriver.gecko.driver","..\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get("http://10.15.12.148:9090/#/login");
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("email"))));
			
		}
@Test
	public void testCaseLoginLogout() throws InterruptedException {
		wait = new WebDriverWait(driver, 20);
		WebElement email = driver.findElement(By.id("email"));
		email.clear();
		//user = admin
		email.sendKeys("admin");
		
		WebElement password = driver.findElement(By.id("password"));
		password.clear();
		// password = ssdc@vnpt
		password.sendKeys("ssdc@vnpt");
		// enter
		password.submit();
		new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith("dashboard");
			}
		});
		logger.info("Login succeed");
		//check login passed
		assertEquals("Dashboard",driver.getTitle());
		
		testTransferDetail();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.className("user_action_image"))));
		WebElement elementLogout = driver.findElement(By.className("user_action_image"));		
		elementLogout.click();
		WebElement logout = driver.findElement(By.xpath("//*[@id=\"header_main\"]/div[1]/nav/div[3]/ul/li[4]/div/ul/li[3]/a"));
		logout.click();
		logger.warn("Logout");
		new WebDriverWait(driver, 4).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith("login");
			}
		});
		// check logout passed
		assertEquals("Login", driver.getTitle());			
	}
	public void testTransferDetail() throws InterruptedException {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"sidebar_main\"]/div/div[1]/div[2]/ul/li[2]/a"))));
		WebElement elementOperation = driver.findElement(By.xpath("//*[@id=\"sidebar_main\"]/div/div[1]/div[2]/ul/li[2]/a"));
		elementOperation.click();
		WebElement elementTransfer = driver.findElement(By.xpath("//*[@id=\"sidebar_main\"]/div/div[1]/div[2]/ul/li[2]/ul/li[1]/a"));
		elementTransfer.click();
		new WebDriverWait(driver, 20).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith("transfers");
			}
		});
		assertEquals("Transfers", driver.getTitle());
		
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"filterRow\"]/td[2]/input"))));
		WebElement elementSelectType = driver.findElement(By.xpath("//*[@id=\"filterRow\"]/td[2]/input"));
		elementSelectType.click();
		elementSelectType.sendKeys("INT");
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.partialLinkText("INT"))));
		 WebElement elementClickToSelect = driver.findElement((By.partialLinkText("INT")));
		elementClickToSelect.click();
		
		new WebDriverWait(driver, 20).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d2) {
				return d2.getTitle().toLowerCase().startsWith("transfer detail");
			}
		});
		// check select a transfer
		assertEquals("Transfer Detail", driver.getTitle());
		
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"transfer_detail\"]/div/div[3]/div[2]/div/div[1]/div[1]"))));
		//check operation type
		WebElement operationType = driver.findElement(By.xpath("//*[@id=\"transfer_detail\"]/div/div[3]/div[2]/div/div[1]/div[1]"));
		assertEquals("Operation Type", operationType.getText());
		
		//check partner
		WebElement partner = driver.findElement(By.xpath("//*[@id=\"transfer_detail\"]/div/div[3]/div[2]/div/div[1]/div[2]"));
		assertEquals("Partner", partner.getText());
		
		//check Source Location
		WebElement sourceLocation = driver.findElement(By.xpath("//*[@id=\"transfer_detail\"]/div/div[3]/div[2]/div/div[1]/div[3]"));
		assertEquals("Source Location", sourceLocation.getText());
		
		//check Destination Location
		WebElement destinationLocation = driver.findElement(By.xpath("//*[@id=\"transfer_detail\"]/div/div[3]/div[2]/div/div[1]/div[4]"));
		assertEquals("Destination Location", destinationLocation.getText());
		
		//check Project
		WebElement project = driver.findElement(By.xpath("//*[@id=\"transfer_detail\"]/div/div[3]/div[2]/div/div[1]/div[7]"));
		assertEquals("Project", project.getText());
		
		//check owner
		WebElement owner = driver.findElement(By.xpath("//*[@id=\"transfer_detail\"]/div/div[3]/div[2]/div/div[3]/div[1]"));
		assertEquals("Owner", owner.getText());
		
		//check Schedule Date
		WebElement scheduleDate = driver.findElement(By.xpath("//*[@id=\"transfer_detail\"]/div/div[3]/div[2]/div/div[3]/div[2]"));
		assertEquals("Schedule Date", scheduleDate.getText());
		
		//check Source Document
		WebElement sourceDocument = driver.findElement(By.xpath("//*[@id=\"transfer_detail\"]/div/div[3]/div[2]/div/div[3]/div[3]"));
		assertEquals("Source Document", sourceDocument.getText());
		
		//check Assignee
		WebElement assignee = driver.findElement(By.xpath("//*[@id=\"transfer_detail\"]/div/div[3]/div[2]/div/div[3]/div[4]"));
		assertEquals("Assignee", assignee.getText());
		
		//check SC BoM
		WebElement sCBoM = driver.findElement(By.xpath("//*[@id=\"transfer_detail\"]/div/div[3]/div[2]/div/div[3]/div[7]"));
		assertEquals("SC BoM", sCBoM.getText());
		
		//check PO
		WebElement pO = driver.findElement(By.xpath("//*[@id=\"transfer_detail\"]/div/div[3]/div[2]/div/div[3]/div[8]"));
		assertEquals("PO", pO.getText());
		
		//check internal transfers
		WebElement typeTransfer = driver.findElement(By.xpath("//*[@id=\"transfer_detail\"]/div/div[3]/div[2]/div/div[2]/div[1]/a"));
		logger.info(typeTransfer.getText());
		String[] wordTransfer = typeTransfer.getText().split("\\s");
		logger.info(wordTransfer[wordTransfer.length-1]);
		//check transfer type is internal transfers
		assertEquals("Internal Transfers",wordTransfer[wordTransfer.length-2] + " " + wordTransfer[wordTransfer.length-1] );
		
		
		WebElement status = driver.findElement(By.className("wizard"));
		logger.info(status.getText());
		//split
		String[] wordStatus = status.getText().split("\\s");
		//check status
		assertEquals("DRAFT", wordStatus[0]);
		assertEquals("WAITING", wordStatus[1]);
		assertEquals("OTHER WAITING", wordStatus[2] + " " + wordStatus[3]);
		assertEquals("READY", wordStatus[4]);
		assertEquals("DONE", wordStatus[5]);
	}
		
@After
	public void tearDown() throws Exception {
		// Close the browser
		driver.quit();
	}
}
