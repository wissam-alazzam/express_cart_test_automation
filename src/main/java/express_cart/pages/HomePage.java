package express_cart.pages;

import java.awt.Point;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import express_cart.base.BasePageObject;
import express_cart.base.BaseTest;

public class HomePage
    extends BasePageObject
{

    public HomePage( WebDriver driver )
    {
        super( driver );

    }

    private String homePageUrl = getConfigurationsByKey( "BaseUrl" );

    private String shopeName = getConfigurationsByKey( "ShopeName" );

    private By shopNameLocator = By.xpath( "//a[@class='navbar-brand']" );

    // only visible when screen is resized to half it's size
    private By menuButtonLocator = By.xpath( "//button[@class='navbar-toggler collapsed']" );

    private By languageSelectorButtonLocator = By.xpath( "//button[@id='dropdownMenuButton']" );

    private By languaeSelectorDropDownLocator = By.xpath( "//div[@class='dropdown-menu show']" );

    private By cartButtonLocator = By.xpath( "//a[@class='btn menu-btn']" );

    private By searchInputFieldLocator = By.xpath( "//input[@id='frm_search']" );

    private By searchButtonLocator = By.xpath( "//button[@id='btn_search']" );

    private By homeMenuItemLocator = By.xpath( "//a[contains(text(),'Home')]" );

    private By noProductsTextLocator = By.xpath( "//p[@class='text-danger']" );

    private By productContainerLocator =
        By.xpath( "//body/div[@id='container']/div[@class='row h-100']/div[@class='productsWrapper col-sm-12 col-md-8 offset-md-2']/div[@class='row product-layout']/*" );

    private By productImageLocator =
        By.xpath( "//div[@class='row product-layout']//div[1]//div[1]//div[1]//a[1]//div[1]//img[1]" );

    private By productTitleLocator =
        By.xpath( "//h3[@class='product-title product-title-home top-pad-10' and contains(text(),*)]" );

    private By productPriceleLocator =
        By.xpath( "//h3[@class='product-price mp-0 text-center' and contains(text(),*)]" );

    private By addToCartButtonLocator = By.xpath( "//div/p/a[@class='btn btn-primary add-to-cart']" );

    private By totalProductCount = By.xpath( "//input[@id='totalProductCount' and ]" );

    private By bodyTagLocator = By.xpath( "//html//body" );

    private By categoryMenuItemLocator = By.xpath( "//nav[@id='navbarMenu']//li[2]//a" );

    // private By productContainerLocator = By.xpath( "//body/div[@id='container']/div[@class='row
    // h-100']/div[@class='productsWrapper col-sm-12 col-md-8 offset-md-2']/div[@class='row product-layout']/div[1]" );

    public void openHomePage()
    {
        openUrl( homePageUrl );

    }

    public void waitForHomePageToLoad()
    {
        System.out.println( "Waiting for page to load" );
        waitForVisibilityOf( shopNameLocator, 3 );
        System.out.println( "Page loaded!" );
    }

    public boolean checkShopNameMatchesConfig()
    {
        System.out.println( "Checking shop name" );
        String currentPageName = find( shopNameLocator ).getText();
        if ( currentPageName.equalsIgnoreCase( shopeName ) )
        {
            System.out.println( "Shop name correct and is:" + currentPageName );
            return true;
        }
        else
        {
            System.out.println( "Shop name doesn't equal the name in project configurations" );
            return false;
        }
    }

    private void clickOnLanguageSelector()
    {
        find( languageSelectorButtonLocator ).click();
        System.out.println( "Language selector clicked" );
    }

    public void changeLanguage( String language )
        throws InterruptedException
    {

        clickOnLanguageSelector();
        System.out.println( "Changing language" );
        waitFor( ExpectedConditions.visibilityOfElementLocated( languaeSelectorDropDownLocator ), 2 );

        WebElement languageItemElement = driver.findElement( By.xpath( "//a[contains(text(),'" + language + "')]" ) );

        System.out.println( "Selecting:" + languageItemElement.getText() );

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript( "arguments[0].click();", languageItemElement );

        System.out.println( languageItemElement.getText() + " " + " Selected" );
        waitWithSleep();

        String englishText = "Cart";
        String italianText = "Carrello";

        switch ( language )
        {
            case "English":
                Assert.assertTrue( driver.getPageSource().contains( englishText ),
                                   "Page is not translated to english" );
                System.out.println( "Page translation is english" );
                break;
            case "Italiano":
                Assert.assertTrue( driver.getPageSource().contains( italianText ),
                                   "Page is not translated to italian" );
                System.out.println( "Page translation is italian" );
            default:
                break;
        }

    }

    public void checkLanguageSelector()
    {
        clickOnLanguageSelector();
        System.out.println( "Checking language selector items" );
        waitFor( ExpectedConditions.visibilityOfElementLocated( languaeSelectorDropDownLocator ), 2 );
        WebElement languageListItems = driver.findElement( languaeSelectorDropDownLocator );
        List<WebElement> list =
            languageListItems.findElements( By.xpath( "//div[@class='dropdown d-none d-sm-block show']//li" ) );
        System.out.println( list.size() );
        Assert.assertFalse( list.isEmpty(), "No language items in the dropedown, at least 2 should be listed" );
        for ( Iterator iterator = list.iterator(); iterator.hasNext(); )
        {
            WebElement webElement = (WebElement) iterator.next();
            System.out.println( webElement.getText() );

        }

    }

    public void clickOnCart()
    {
        find( cartButtonLocator ).click();
        System.out.println( "Cart button is clicked" );
    }

    public void checkCartIsOpen()
    {
        String expectedClassName = "pushy-open-right";
        clickOnCart();
        System.out.println( find( bodyTagLocator ).getAttribute( "class" ) );
        Assert.assertEquals( find( bodyTagLocator ).getAttribute( "class" ), expectedClassName );

    }

    public void checkProductContainer()
    {
//        List<WebElement> productsListLocators = driver.findElements( productContainerLocator );
//
//        for ( Iterator iterator = productsListLocators.iterator(); iterator.hasNext(); )
//        {
//
//            WebElement webElement = (WebElement) iterator.next();
//            List<WebElement> childElements = webElement.findElements( By.xpath( ".//div" ) );
//            for ( Iterator iterator2 = childElements.iterator(); iterator2.hasNext(); )
//            {
//                WebElement childWebElement = (WebElement) iterator2.next();
//
//                System.out.println( childWebElement.findElement( By.xpath( ".//*" ) ).getTagName() );
//                if ( childWebElement.findElement( By.xpath( ".//*" ) ).getTagName().contains( "img" ) )
//                {
//                    System.out.println( childWebElement.findElement( By.xpath( ".//img" ) ).getAttribute( "src" ) );
//                    System.out.println( childWebElement.findElement( By.xpath( ".//img" ) ).getCssValue( "height" ) );
//                    Assert.assertTrue( childWebElement.findElement( By.xpath( ".//img" ) ).getCssValue( "height" ) == "231.5px",
//                                       "Image is not showing on UI" );
//                    System.out.println( "IMAGE FOUNNND" );
//                }
//
//            }
//
//            System.out.println( webElement.getTagName() );
//            System.out.println( webElement.findElement( By.xpath( ".//*" ) ).getTagName() );
//            System.out.println( webElement.findElement( By.xpath( ".//*" ) ).getText() );
//
        waitForVisibilityOf( productContainerLocator, 5 );
        Dimension sizeOfImage = driver.findElement( productImageLocator ).getSize();
        System.out.println( "Image size:" + sizeOfImage );
        if ( sizeOfImage.width == 0 && sizeOfImage.height == 0 )
        {

            Assert.fail( "There is an issue with the product image" );
        }
        String productTitle = driver.findElement( productTitleLocator ).getText();
        System.out.println( "Product title:" + productTitle );
        if ( productTitle == null )
        {
            Assert.fail( "Prodcut title is missing" );
        }

        String productPrice = driver.findElement( productPriceleLocator ).getText();
        System.out.println( "Product price:" + productPrice );
        if ( productPrice == null )
        {
            Assert.fail( "Prodcut price is missing" );
        }
    }

    public void clickOnAddToCart()
        throws InterruptedException
    {
//        List<WebElement> productsListLocators = driver.findElements( productContainerLocator );
//        for ( Iterator iterator = productsListLocators.iterator(); iterator.hasNext(); )
//        {
//            WebElement webElement = (WebElement) iterator.next();
//            System.out.println( webElement );
//            // System.out.println( webElement.getTagName() );
//            System.out.println( webElement.findElement( By.xpath( ".//*" ) ).getTagName() );
//            System.out.println( webElement.findElement( By.xpath( ".//*" ) ).getText() );
//            if ( webElement.findElement( By.xpath( ".//*" ) ).getText().contains( "Add to cart" ) )
//            {
//
//                System.out.println( webElement.findElement( By.xpath( ".//*/p/a" ) ).getAttribute( "data-link" ) );
////                driver.getCurrentUrl();
//                webElement.findElement( By.xpath( ".//*/p/a" ) ).click();
//                waitForVisibilityOf( By.xpath( "//h1[@class='col-md-10 product-title text-truncate']" ), 5 );
//                waitWithSleep();
//                System.out.println( driver.getCurrentUrl() );
//
//                driver.navigate().back();
//                System.out.println( "WENT BACK" );
//                // driver.navigate().refresh();
//
////                waitWithSleep();
////                driver.getCurrentUrl();
////                waitWithSleep();
//            }
//
//        }

        String dataLink = driver.findElement( addToCartButtonLocator ).getAttribute( "data-link" );
        System.out.println( dataLink );
        driver.findElement( addToCartButtonLocator ).click();
        waitForVisibilityOf( By.xpath( "//h4[@class='col-md-10 product-option']" ), 5 );
        String currentUrl = driver.getCurrentUrl();
        System.out.println( currentUrl );
        System.out.println( "http://192.168.1.8:1111/product/" + dataLink );
        // System.out.println(currentUrl.equals( "http://192.168.1.8:1111/product/"+dataLink ));

        Assert.assertFalse( "http://192.168.1.8:1111/product/" + dataLink == currentUrl,
                            "Current Url doesn't match the prodcut we requested" );
    }
    
    
    public void clickOnNavigationMenuItem(  )
    {
        System.out.println("Clicking on a category from navigation menu");
        driver.findElement( categoryMenuItemLocator ).click();
        System.out.println(driver.findElement( categoryMenuItemLocator ).getText()+"\b is clicked");
    }
    
    public void checkCategoryOpened(  )
    {
        clickOnNavigationMenuItem();
        
        String dataLink = driver.findElement( categoryMenuItemLocator ).getAttribute( "href" );
        String currentUrl = driver.getCurrentUrl();
        System.out.println(dataLink);
        Assert.assertEquals( currentUrl, dataLink, "Current URL isn't as expected" );
        
    }

}
