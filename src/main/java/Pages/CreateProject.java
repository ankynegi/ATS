package Pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class CreateProject {

	WebDriver drvr;
	public CreateProject(WebDriver driver){
		this.drvr=driver;
		PageFactory.initElements(drvr, this);
	}
	
	@FindBy(xpath="//*[starts-with(text(),'New Project')]")
	private WebElement newPrjct;
	
	@FindBy(xpath="//*[starts-with(text(),'Project Name')]/parent::div/descendant::input")
	private WebElement prjctName;
	
	@FindBy(xpath="//h6[text()='Create New Project']")
	private WebElement createNewProject;
	
	
	@FindBy(xpath="//*[starts-with(text(),'Country')]/parent::div/descendant::input")
	private WebElement country;
	
	@FindBy(xpath="//*[contains(@id,'react-autowhatever-') and contains(@id,'item-0')]")
	private WebElement listOFCountry;
	
	@FindBy(xpath="//*[starts-with(text(),'State')]/parent::div/descendant::input")
	private WebElement state;
	
	@FindBy(xpath="//*[contains(@id,'react-autowhatever-') and contains(@id,'item-0')]")
	private WebElement listOFStates;
	
	@FindBy(xpath="//*[starts-with(text(),'City')]/parent::div/descendant::input")
	private WebElement city;
	
	@FindBy(xpath="//*[contains(@id,'react-autowhatever-') and contains(@id,'item-0')]")
	private WebElement listOfCity;
	
	@FindBy(xpath="//*[starts-with(text(),'Address')]/parent::div/descendant::textarea[@aria-invalid='false']")
	private WebElement addrs;
	
	@FindBy(xpath="//*[starts-with(text(),'Bid Date')]/parent::div/descendant::input")
	private WebElement date;
	
	@FindBy(xpath="//button[@class='MuiButtonBase-root-934 MuiIconButton-root-976']//*[@class='MuiSvgIcon-root-938']")
	private WebElement datepicker;
	
	@FindBy(xpath="//*[starts-with(text(),'Area')]/parent::div/descendant::input")
	private WebElement area;
	
	@FindBy(xpath = "//span[contains(text(),'Next')]")
	private WebElement next;
	
	@FindBy(xpath = "//span[contains(text(),'Finish')]")
	private WebElement finish;
	
	@FindBy(xpath="//*[@data-icon='building']/following-sibling::p")
	private WebElement commercialBuilding;
	
	@FindBy(xpath="//*[contains(@value,'Mall')]/parent::*")
	private WebElement mall;
	
	Faker faker = new Faker();
	private static String prjct;
	
	public void clickOnNewPoject(){
		newPrjct.click();
	}
	public void enterBasicProjectDetails(){
		prjctName.sendKeys(getProjectName());
	} 
	
	public void enterBasicProjectDetails(String projectname){
		prjctName.sendKeys(projectname);
	} 
	private String getProjectName(){
		prjct = faker.lastName()+" " + "Solution";
		return prjct;
	}
	public boolean verifyDashboard(){
		boolean b=false;
		try{
			b=newPrjct.isDisplayed();
		}catch(Exception e){
			
		}
			return b;
	}
	
	public boolean verifyCreateProjectScreean(){
		boolean b=false;
		try{
			waitForElementVisible(createNewProject);
			b=createNewProject.isDisplayed();
		}catch(Exception e){
			
		}
			return b;
	}
	
	
	public void enterCountry(String cntry)
	{

		country.sendKeys(Keys.chord(cntry));
		waitForElementVisible(listOFCountry);
		listOFCountry.click();
	}
	
	public void enterState(String states)
	{

		state.sendKeys(Keys.chord(states));
		waitForElementVisible(listOFStates);
		listOFStates.click();
	}
	
	public void enterCity(String cty) throws InterruptedException
	{

		boolean b=false;
		city.sendKeys(Keys.chord(cty));
		
		try {
			waitForElementVisible(listOfCity);
			b=listOfCity.isDisplayed();
		}catch(Exception e) {}
		if(b==false) {
			new Actions(drvr).sendKeys(Keys.BACK_SPACE);
		}
		waitForElementVisible(listOfCity);
		listOfCity.click();
		
	}
	
	public void enterDate(String dt)
	{

		Date curdat=new Date();
		SimpleDateFormat sft = new SimpleDateFormat("yyyy/MM/dd");
		String s=sft.format(curdat);
		date.sendKeys(s);
	}
	public void enterArea(String a) {
		area.sendKeys(a);
	}
	public void enterAddress(String addr) {
		addrs.sendKeys(addr);
	}
	public void otherBasicDetails(String addr,String date, String city,String state, String country, String a) throws InterruptedException {
		enterCountry(country);
		enterState(state);
		enterCity(city);
		enterAddress(addr);
		enterDate(date);
		enterArea(a);
	}
	
	public void clickNext() {
		next.click();
	}
	
	private void clickOnCommercialProperty() {
		commercialBuilding.click();
	}
	private void selectRadioTypeMAll() {
		mall.click();
	}
	
	public void selectBuildingClass() {
		clickOnCommercialProperty();
		selectRadioTypeMAll();
	}
	
	public void clickOnFinish() {
		waitForElementClickable(finish);
		new Actions(drvr).pause(10000).perform();
		finish.click();
	}
	public void waitForElementVisible(WebElement element) {
		
		WebDriverWait wait=new WebDriverWait(drvr, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
public void waitForElementClickable(WebElement element) {
		
		WebDriverWait wait=new WebDriverWait(drvr, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
public String readProjectName() {
	return prjct;
}

}
