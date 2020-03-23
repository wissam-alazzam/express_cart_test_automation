package express_cart.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject
{

    protected WebDriver driver;

    public BasePageObject( WebDriver driver )
    {
        this.driver = driver;
    }

    // Get configurations from resources/configurations.properties by suppling the key
    protected String getConfigurationsByKey( String key )
    {
        String value = "Initial value";
        try
        {
            InputStream inputConfiguration =
                BaseTest.class.getClassLoader().getResourceAsStream( "configurations.properties" );

            Properties prop = new Properties();
            prop.load( inputConfiguration );
            value = prop.getProperty( key );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        return value;
    }

    // Open any URL passed to this
    protected void openUrl( String url )
    {
        System.out.println( "Opening page:" + url );
        driver.get( url );
        System.out.println( "Page opened!" );
    }

    // Find element in page by passing locator
    protected WebElement find( By locator )
    {
        return driver.findElement( locator );

    }
    
    protected void waitWithSleep(  ) throws InterruptedException
    {
        Thread.sleep( 3000 );
        
    }

    // waits for 30 seconds or by set time
    public void waitFor( ExpectedCondition<WebElement> condition, Integer timeOutInSeconds )
    {
        System.out.println( "Waiting from inside waitFor function" );
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait( driver, timeOutInSeconds );
        System.out.println( "Printing the condetion:" + condition );

        wait.until( condition );

    }

    // waits for visibility of an element
    protected void waitForVisibilityOf( By locator, Integer... timeOutInSeconds )
    {
        int attempts = 0;
        while ( attempts < 2 )
        {
            System.out.println( "Waiting from inside waitForVisibilityOf function" );
            waitFor( ExpectedConditions.visibilityOfElementLocated( locator ),
                     timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null );
            break;

        }
//        try
//        {
//
//        }
//        catch ( StaleElementReferenceException e )
//        {
//            // TODO: handle exception
//            System.out.println( "Wait for visibility of failed reason:" + e.getMessage() );
//        }

        attempts++;
    }
    
   
    // closes alert and gets it's text
    void closeAlertAndGetItsText( WebDriver driver )
        throws Exception
    {
        String alertText = null;
        try
        {
            Alert alert = driver.switchTo().alert();
            alertText = alert.getText();
            alert.accept();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

}
