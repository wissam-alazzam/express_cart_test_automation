package express_cart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import express_cart.base.BasePageObject;

public class CartPage extends BasePageObject
{

    public CartPage( WebDriver driver )
    {
        super( driver );
    }
    
    String baseUrl = getConfigurationsByKey( "BaseUrl" );
    
    private By cartButtonLocator = By.xpath( "//a[@class='btn menu-btn']" );
    
    private By cartItemsCounter = By.xpath( "//span[@id='cart-count']" );
    
    private By cartCloseButtonLocator = By.xpath( "//button[@class='pushy-link btn btn-primary']" );
    
    private By cartItemsContainterLocator = By.xpath( "//div[@class='card-body cart-body']" );
    
    private By emptyCartStirngLocator = By.xpath( "//div[@class='cart-contents-shipping col-md-12 no-pad-left']" );
    
    private By cartItemLocator = By.xpath( "//body[@class='pushy-open-right']/div[@id='cart']/div[@class='row']/div[@id='cart']/div[@class='card top-marg-15 bottom-marg-15']/div[@class='card-body cart-body']/div[@class='cartBodyWrapper']/div" );
    
    private By productImageLocator = By.xpath( "//div[@class='img-fluid']" );
    
    private By productTitleLocator = By.xpath( "//a[contains(text(),*)]" );
    
    private By productSizeLocator = By.xpath( "//strong[contains(text(),'Size:')]" );
    
    private By productColorLocator = By.xpath( "//strong[contains(text(),'Colour')]" );
    
    private By productPriceLcoator = By.xpath( "//div[@class='col-8 col-md-4 align-self-center text-right']" );
    
    private By productDeleteItem = By.xpath( "//div[@class='col-4 col-md-2 no-pad-left']" );
    
    private By productQuantityCountLocator = By.xpath( "//input[@class='form-control cart-product-quantity text-center']" );
    
    private By productQuantityIncreaseButtonLocator = By.xpath( "//button[@class='btn btn-primary btn-qty-add']" );
    
    private By productQuantityDecreaseButtonLocator = By.xpath( "//button[@class='btn btn-primary btn-qty-minus']" );
    
    private By emptyCartButtonLocator = By.xpath( "//button[@id='empty-cart']" );
    
    private By checkoutButtonLocator = By.xpath( "//a[@class='btn btn-primary float-right']" );
    
    private By addToCartButtonHomePageLocator = By.xpath( "//a[@class='btn btn-primary add-to-cart']" );
   
    
    /*
     * TODO - Keep add functions to this class
     */
    
    public void addRandomItemToCart(  )
    {
        System.out.println("Adding random item to cart,Clicking on add to cart");
        
        List<WebElement> addToCartButtonsList = driver.findElements(addToCartButtonHomePageLocator);
        System.out.println("Currently there are "+addToCartButtonsList.size()+"Items");
        
        
    }
    
    
}

