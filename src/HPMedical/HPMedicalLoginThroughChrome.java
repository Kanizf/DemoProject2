package HPMedical;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HPMedicalLoginThroughChrome {
	
	private static WebDriver driver;
	private static Wait<WebDriver> wait;

	
	public static void main  (String [] args) throws Exception {
		// TODO Auto-generated method stub

		//Here you will specify the driver type and its location 
		System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		
		try {
			
			driver = new ChromeDriver();
			
			//Maximize the browser			
			driver.manage().window().maximize();
			
			wait = new WebDriverWait(driver, 40);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			//
			// Navigate to the application
			driver.get("https://abac3.nttdatafed.com:8643/hpmedical");
				
			
			
			//Verify log in page title.
			
			String logInPageTitle = driver.getTitle();
			System.out.println("The Log in Page title is :"+logInPageTitle);
			
			if (logInPageTitle.equalsIgnoreCase("HP Medical Login"))
			{
				System.out.println("Processed to the Login");
				
				Thread.sleep(1000); 
				
				WebElement uName = driver.findElement(By.id("username"));
				
				uName.sendKeys("rjohnson");
				
				WebElement passWord = driver.findElement(By.id("password"));
				
				passWord.sendKeys("P@ssword1");
				
			//	waitSeconds(2);
				
				WebElement signOn = driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/fieldset/table/tbody/tr[3]/td[2]/input"));
				
				signOn.click();
				
				//Home page verification and do implement test steps
				
				//NTTDATA title verification
				// Richard id = 6
				
				String homePageUrl= driver.getCurrentUrl();
				System.out.println("The homepae url is: "+homePageUrl);
				
				if(homePageUrl.equalsIgnoreCase("https://abac3.nttdatafed.com:8643/hpmedical/policies"))
						{
					
					List<WebElement> policyTab = driver.findElements(By.xpath("/html/body/div[4]/table[1]//td"));
					
					int node_val = policyTab.size();
					
					System.out.println("The number of values are : "+node_val);
					
					// run a loop to find id 6 and Click
					
					for (int i=0; i< node_val; i++)
					{
						String policyTabVal = policyTab.get(i).getText();
								
								
						System.out.println(policyTabVal);
						
						if (policyTabVal.equals("6"))
								{
							
							Thread.sleep(4000);
							policyTab.get(i).click();
							break;						
							
								}						
						else
						{
							System.out.println("Not clicked");
							driver.quit();
						}
					}					
					
						} 		
	
				Thread.sleep(4000);
				
				// Sign out the page
				System.out.println("User is Signing out from the System");
				
				
				driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/input")).click();
				
				Thread.sleep(4000);
				
				// Quit driver
				
				System.out.println("Now user quit driver");
				driver.quit();
				
					
			}
			
			else {
				System.out.println("The Log in Process is failed");
				driver.quit();
			}

			
			//Wait
			
			
		}catch(Exception e)
		{
			System.out.println("Exception is :"+ e.getMessage());
			
		}
		
		
		
	}
	
	/*public void waitSeconds(int waitSec) throws Exception 
	{
		Thread.sleep(waitSec *1000);
	}
		
	public void quitDriver()
	{
		driver.quit();
	}*/
	
	
	}


