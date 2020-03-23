package express_cart.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.qameta.allure.Allure;

public class BaseTest
{
    /*
     * This class contains basic functionality to be used in many tests
     */
    protected WebDriver driver;
    String name = "";
    
    public void name( String name )
    {
        this.name = name; 
    }
    
    // Get configurations from resources/configurations.properties by suppling the key
    protected String getConfigurationsByKey(String key)
    {
        String value = "Initial value";
        try
        {
            InputStream inputConfiguration =
                BaseTest.class.getClassLoader().getResourceAsStream( "configurations.properties" );

            Properties prop = new Properties();
            prop.load( inputConfiguration );
            System.out.println( prop.getProperty( key ) );
            value= prop.getProperty( key );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        return value;
    }

    // This method will run before each test and setup a browser
    @BeforeTest( alwaysRun = true )
    @Parameters( { "browser" } )
    protected void setUp( @Optional( "firefox" ) String browser )
        throws InterruptedException
    {
        BrowserDriverFactory factory = new BrowserDriverFactory( browser );
        driver = factory.createDriver();

        driver.manage().window().maximize();

    }

    // This method will run after each test and close the browser
   @AfterTest( alwaysRun = true )
    protected void tearDown()
    {
        System.out.println( "[Closing driver]" );
        driver.quit();
    }

    // Takes a screenshot and save it in test-output folder
    protected void takeScreenShot( String fileName )
        throws IOException, InterruptedException
    {
        File scrFile = ( (TakesScreenshot) driver ).getScreenshotAs( OutputType.FILE );
        String path = System.getProperty( "user.dir" ) + "//test-output//screenshots//" + fileName + ".png";
        FileUtils.copyFile( scrFile, new File( path ) );

    }

    // returns current date formatted
    
    protected String getTime()
    {
        SimpleDateFormat formatter = new SimpleDateFormat( "ddMMyyyyHHmmss" );
        Date date = new Date();
        return formatter.format( date );

    }

    // Uses screen shot from takeScreenShot function and display them in Allure report
    @AfterMethod
    public void allureScreenShot()
        throws IOException, InterruptedException
    {
        String time = getTime();
        takeScreenShot( time );

        Path content = Paths.get( System.getProperty( "user.dir" ) + "//test-output//screenshots//" + time + ".png" );
        try ( InputStream is = Files.newInputStream( content ) )
        {
            Allure.addAttachment( "My attachment", is );
        }
    }

    /*
     * This function grabs logs from Chrome using capabilities and attach them to allure test, for Firefox we can't set
     * logging using capabilities so i'm saving the output to FirefoxLogs.txt and reading the output to allure
     * attachment.
     */
    @AfterMethod
    public void allureLog()
    {
        Capabilities cap = ( (RemoteWebDriver) driver ).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        System.out.println( browserName );
        String osName = cap.getPlatform().toString();
        System.out.println( osName );
        String versionName = cap.getVersion().toString();
        System.out.println( versionName );

        switch ( browserName )
        {
            case "chrome":
                LogEntries logEntries = driver.manage().logs().get( LogType.BROWSER );
                StringBuilder chromeLogs = new StringBuilder();
                for ( org.openqa.selenium.logging.LogEntry entry : logEntries )
                {
                    chromeLogs.append( "Browser:" + browserName + "\n" );
                    chromeLogs.append( "Version:" + versionName + "\n" );
                    chromeLogs.append( "OS:" + osName + "\n" );

                    chromeLogs.append( new Date( entry.getTimestamp() ) + " " + entry.getLevel() + " "
                        + entry.getMessage() );
                    chromeLogs.append( System.lineSeparator() );

                }

                System.out.println( chromeLogs );

                Allure.addAttachment( "Browser logs", chromeLogs.toString() );
                break;
            case "firefox":
                System.out.println( "Firefox Log" );
                StringBuilder firefoxLogs = new StringBuilder();
                try
                {
                    File myObj = new File( "./FirefoxLogs.txt" );
                    Scanner myReader = new Scanner( myObj );
                    while ( myReader.hasNextLine() )
                    {
                        String data = myReader.nextLine();
                        System.out.println( data );
                        firefoxLogs.append( data + "\n" );

                    }
                    firefoxLogs.append( "Browser:" + browserName + "\n" );
                    firefoxLogs.append( "Version:" + versionName + "\n" );
                    firefoxLogs.append( "OS:" + osName + "\n" );
                    myReader.close();
                    Allure.addAttachment( "Browser logs", firefoxLogs.toString() );
                }
                catch ( FileNotFoundException e )
                {
                    System.out.println( "An error occurred." );
                    e.printStackTrace();
                }

            default:
                break;
        }

    }

}
