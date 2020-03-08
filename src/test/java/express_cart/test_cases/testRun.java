package express_cart.test_cases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import express_cart.base.BaseTest;
import io.qameta.allure.Flaky;
import io.qameta.allure.Step;

public class testRun
    extends BaseTest
{

    @Test
    @Step
    public void testCase()
    {
        System.out.println( "WUZZZZZUP" );
        getConfigurationsByKey("BaseUrl");
    }

    @Test
    @Step
    @Flaky
    public void tryUI()
        throws InterruptedException, IOException
    {
        Reporter.log( "Browser Opened" );
        driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );
        // maximize window
        driver.manage().window().maximize();
        Reporter.log( "Browser Maximized" );
        // open browser with desried URL
         driver.get( getConfigurationsByKey( "BaseUrl" ) );
        //driver.get( "http://google.com/" );
        Reporter.log( "Page  Opened" );
         driver.findElement( By.xpath( "//div[@class='row product-layout']//div[1]//div[1]//p[1]//a[1]" ) ).click();
        Reporter.log( "Product Clciked" );
        // Thread.sleep( 3000 );
        allureScreenShot();
        allureLog();

    }

}
