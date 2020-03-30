package express_cart.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import express_cart.base.BasePageObject;

public class ProductPage
    extends BasePageObject
{

    public ProductPage( WebDriver driver )
    {
        super( driver );
    }

    String baseUrl = getConfigurationsByKey( "BaseUrl" );

    private By productImageLocator = By.xpath( "//img[@id='product-title-image']" );

    private By productTitleLocator = By.xpath( "//h1[@class='col-md-10 product-title text-truncate']" );

    private By productPriceLocator = By.xpath( "//h4[@class='col-md-10 product-price mp-0']" );

    private By productSizeDropdownLocator = By.xpath( "//select[@name='Size']" );

    private By productColorDropdownLocator = By.xpath( "//select[@name='Colour']" );

    private By productQuantityInputLocator = By.xpath( "//input[@id='product_quantity']" );

    private By productQuantityIncreaseButtonLocator = By.xpath( "//button[@class='btn btn-primary qty-btn-plus']" );

    private By addToCartButtonLocator = By.xpath( "//button[@class='btn btn-primary btn-block product-add-to-cart']" );

    private By productDescriptionLocator = By.xpath( "//div[@class='col-md-10 body_text']" );
    
    private By cartCounterLocator = By.xpath( "//span[@id='cart-count']" );
    
    private By addedToCartMessage  = By.xpath( "//div[@id='notify_message']" );
    
    //Wait for product page to load 
    public void waitForProductPageToLoad()
    {
        waitForVisibilityOf( productTitleLocator, 5 );
    }

    //Check if the product image is present 
    public void checkProductImage()
    {
        System.out.println( "Checking Image" );
        Boolean ImagePresent =
            (Boolean) ( (JavascriptExecutor) driver ).executeScript( "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                                                                     driver.findElement( productImageLocator ) );
        System.out.println( "Image is present?" + ImagePresent );
        Assert.assertTrue( ImagePresent, "Image isn't present" );
        System.out.println( "Image is showing" );
    }

    //Check if the product title present 
    public void CheckProductTitle()
    {
        String productTitle = driver.findElement( productTitleLocator ).getText();
        System.out.println( "Checking product title" );
        Assert.assertTrue( productTitle != null, "Title is empty" );
        System.out.println( "Title:" + productTitle );
    }

    //Check if the product has price 
    public void CheckProductPrice()
    {
        String productPrice = driver.findElement( productPriceLocator ).getText();
        System.out.println( "Checking product price" );
        Assert.assertTrue( productPrice != null, "Price is empty" );
        System.out.println( "Title:" + productPrice );
    }

    //Check if the product has description 
    public void checkProductDescription()
    {
        String productDescription = driver.findElement( productDescriptionLocator ).getText();
        System.out.println( "Checking product description" );
        Assert.assertTrue( productDescription != null, "Product description is empty" );
        System.out.println( "Title:" + productDescription );
    }

    // Check if the select dropdown has items and select one of them 
    public void checkAndSelectSize()
    {
        Select selectSizeDropDown = new Select( driver.findElement( productSizeDropdownLocator ) );

        List<WebElement> dropDownItems = selectSizeDropDown.getOptions();
        int numberOfItems = dropDownItems.size();
        Assert.assertTrue( numberOfItems != 0, "The list is empty" );
        for ( Iterator iterator = dropDownItems.iterator(); iterator.hasNext(); )
        {
            WebElement webElement = (WebElement) iterator.next();
            System.out.println( webElement.getText() );
            webElement.click();
        }
        System.out.println( selectSizeDropDown.getFirstSelectedOption().getText() );

    }

    //Check if the color dropdown has items and select one of them 
    public void checkAndSelectcolour()
    {
        Select selectColourDropDown = new Select( driver.findElement( productColorDropdownLocator ) );

        List<WebElement> dropDownItems = selectColourDropDown.getOptions();
        int numberOfItems = dropDownItems.size();
        Assert.assertTrue( numberOfItems != 0, "The list is empty" );
        for ( Iterator iterator = dropDownItems.iterator(); iterator.hasNext(); )
        {
            WebElement webElement = (WebElement) iterator.next();
            System.out.println( webElement.getText() );
            webElement.click();
        }
        System.out.println( selectColourDropDown.getFirstSelectedOption().getText() );

    }

    //Increases the number of item quantity 
    public void IncreaseQuantity()
    {
        System.out.println("Increasing quantity number by 1 ");
        String currentQuantity = driver.findElement( productQuantityInputLocator ).getAttribute( "value" );
        System.out.println( "Quantity:"+currentQuantity );
        driver.findElement( productQuantityIncreaseButtonLocator ).click();
        String afterIncreaseQuantity = driver.findElement( productQuantityInputLocator ).getAttribute( "value" );
        System.out.println("Quantity:"+afterIncreaseQuantity);
        Assert.assertTrue( Integer.parseInt( currentQuantity ) < Integer.parseInt( afterIncreaseQuantity ), "The quanitiy value is wrong" );
        
    }
    
    // Add the item to cart by clicking on add to cart button 
    public void addToCart(  )
    {
        System.out.println("Clicking on add to cart");
        driver.findElement( addToCartButtonLocator ).click();
        System.out.println("Add to cart clicked");
    }
    
   
    

    // Verify if the item was added
    public void verifySuccessMessage(  )
    {
        addToCart();
        String messageStyleAttribute = driver.findElement( addedToCartMessage ).getAttribute( "style" );
        if ( messageStyleAttribute != "display: none;" )
        {
            System.out.println("Message showing correctly:" + driver.findElement( addedToCartMessage).getText());
        } else
        {
            Assert.fail( "Message is hiddend probabley" );
        }
        
    }

}
