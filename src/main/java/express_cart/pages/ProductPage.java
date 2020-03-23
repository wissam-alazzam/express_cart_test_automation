package express_cart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import express_cart.base.BasePageObject;

public class ProductPage
    extends BasePageObject
{

    public ProductPage( WebDriver driver )
    {
        super( driver );
        // TODO Auto-generated constructor stub
    }

    String baseUrl = getConfigurationsByKey( "BaseUrl" );

    private By productImageLocator = By.xpath( "//img[@id='product-title-image']" );

    private By productTitleLocator = By.xpath( "//h1[@class='col-md-10 product-title text-truncate']" );

    private By productPriceLocator = By.xpath( "//h4[@class='col-md-10 product-price mp-0']" );

    private By productSizeDropdownLocator = By.xpath( "//select[@name='Size']" );

    private By productColorDropdownLocator = By.xpath( "//select[@name='Colour']" );

    private By productQuantityInputLocator = By.xpath( "//input[@id='product_quantity']" );

    private By addToCartButtonLocator = By.xpath( "//button[@class='btn btn-primary btn-block product-add-to-cart']" );

    private By productDescriptionLocator = By.xpath( "//div[@class='col-md-10 body_text']" );

    public void waitForProductPageToLoad()
    {
        waitForVisibilityOf( productTitleLocator, 5 );
    }

    public void checkProductImage()
    {
        System.out.println( "Checking Image" );
        Boolean ImagePresent =
            (Boolean) ( (JavascriptExecutor) driver ).executeScript( "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                                                                     driver.findElement( productImageLocator ) );
        System.out.println( "Image is present?" + ImagePresent );
        Assert.assertTrue( ImagePresent, "Image isn't present" );
    }

}
