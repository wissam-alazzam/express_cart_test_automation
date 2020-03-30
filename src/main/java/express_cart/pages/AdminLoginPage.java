package express_cart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import express_cart.base.BasePageObject;

public class AdminLoginPage
    extends BasePageObject
{

    public AdminLoginPage( WebDriver driver )
    {
        super( driver );

    }

    String adminPageUrl = getConfigurationsByKey( "AdminUrl" );

    String dashboardUrl = "http://192.168.1.8:1111/admin/dashboard";

    private By signinHeadingLocator = By.xpath( "//h2[@class='form-signin-heading']" );

    private By emailInputFieldLocator = By.xpath( "//input[@id='email']" );

    private By passwordInputFieldLocator = By.xpath( "//input[@id='password']" );

    private By signinButtonLocator = By.xpath( "//button[@id='loginForm']" );

    private By errorMessageLocator = By.xpath( "//div[@id='notify_message']" );

    private By dashboardHeaderLocator = By.xpath( "//h2[contains(text(),'Dashboard')]" );

    // Open admin login page
    public void openAdminLoginPage()
    {
        openUrl( adminPageUrl );
    }

    // Wait for the page to load
    public void waitForAdminLoginPage()
    {
        waitForVisibilityOf( signinHeadingLocator, 5 );
    }

    // Function to fill in email in email input field
    public void fillInEmail( String email )
    {
        System.out.println( "Filling email" );
        driver.findElement( emailInputFieldLocator ).sendKeys( email );
        System.out.println( "Email filled:" + email );
    }

    // Function to fill in password in password input field
    public void fillInPassword( String password )
    {
        System.out.println( "Filling password" );
        driver.findElement( passwordInputFieldLocator ).sendKeys( password );
        System.out.println( "Email filled:" + password );
    }

    // Click on sign in button
    public void clickSignInButton()
    {
        System.out.println( "Clicking signin button" );
        driver.findElement( signinButtonLocator ).click();
        System.out.println( "Signin button clicked" );
    }

    // Wait for Error message that appears after inputing wrong information
    public void waitForErrorMessage()
    {
        System.out.println( "Waiting for error messaeg to appear" );
        waitForVisibilityOf( errorMessageLocator, 3 );
        if ( driver.findElement( errorMessageLocator ).isDisplayed() )
        {
            System.out.println( "Error message dispalyed:" + getErrorMessage() );
        }
    }

    // Function to return the text in the error messsage
    public String getErrorMessage()
    {
        return driver.findElement( errorMessageLocator ).getText();
    }

    // Function to verify if the signin was successful
    public void verifySigninSuccessful()
    {
        System.out.println( "Checking current URL" );
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals( currentUrl, dashboardUrl, "Current URL doesn't equal dashboardUrl" );
        System.out.println( "Current URL:" + currentUrl );
        Assert.assertTrue( driver.findElement( dashboardHeaderLocator ).isDisplayed(),
                           "Dashboard header text isn't visible" );
        System.out.println( "Dashboard header text is visible?"
            + driver.findElement( dashboardHeaderLocator ).isDisplayed() );
    }

}
