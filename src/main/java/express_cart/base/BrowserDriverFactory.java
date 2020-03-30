package express_cart.base;

import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserDriverFactory
{
    /*
     * This class will create a browser driver based on the passed value of "browser" i.e firefox, a driver object is
     * returned with the selected browser WebDriverManager dependency is used to handle the driver binaries, it
     * automatically downloads them without the need to hardcode the driver's path.
     */

    private WebDriver driver;

    private String browser;
    

    public BrowserDriverFactory( String browser )
    {
        this.browser = browser.toLowerCase();
    }

    // Creates and initialize a driver based on the name of the browser passed to this function
    public WebDriver createDriver()
    {
        System.out.println( "[Setting up driver: " + browser + "]" );

        switch ( browser )
        {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                LoggingPreferences chromeLogPrefs = new LoggingPreferences();
                chromeLogPrefs.enable( LogType.BROWSER, Level.ALL );
                chromeOptions.setCapability( CapabilityType.LOGGING_PREFS, chromeLogPrefs );
                driver = new ChromeDriver( chromeOptions );

                break;

            case "firefox":
                System.setProperty( FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "./FirefoxLogs.txt" );
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();

                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        return driver;
    }

}
