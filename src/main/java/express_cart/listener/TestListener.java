package express_cart.listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener
    implements ITestListener
{
    WebDriver driver = null;

    @Override
    public void onFinish( ITestContext arg0 )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart( ITestContext arg0 )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage( ITestResult arg0 )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailure( ITestResult arg0 )
    {
        // TODO Auto-generated method stub
        System.out.println( "FAIL:IM COMING FROM LISTNERE" );
        try
        {
            Thread.sleep( 3000 );
        }
        catch ( InterruptedException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped( ITestResult arg0 )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestStart( ITestResult arg0 )
    {
        // TODO Auto-generated method stub
        System.out.println("=======>"+arg0.getTestName());

    }
    
    
    @Override
    public void onTestSuccess( ITestResult arg0 )
    {
        
        
        System.out.println( "PASS:IM COMING FROM LISTNERE" );
        try
        {
            Thread.sleep( 3000 );
        }
        catch ( InterruptedException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    // Takes a screenshot and save it in test-output folder
//    protected void takeScreenShot( String fileName , WebDriver driver )
//        throws IOException, InterruptedException
//    {
//        File scrFile = ( (TakesScreenshot) driver ).getScreenshotAs( OutputType.FILE );
//        String path = System.getProperty( "user.dir" ) + "//test-output//screenshots//" + fileName + ".png";
//        FileUtils.copyFile( scrFile, new File( path ) );
//
//    }
//
//    // returns current date formatted
//    protected String getTime()
//    {
//        SimpleDateFormat formatter = new SimpleDateFormat( "ddMMyyyyHHmmss" );
//        Date date = new Date();
//        return formatter.format( date );
//
//    }


}
