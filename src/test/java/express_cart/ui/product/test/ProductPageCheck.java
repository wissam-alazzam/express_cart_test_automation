package express_cart.ui.product.test;

import org.testng.annotations.Test;

import express_cart.base.BaseTest;
import express_cart.pages.HomePage;
import express_cart.pages.ProductPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class ProductPageCheck
    extends BaseTest
{
    @Test
    @Description( "Check prodcut infromation if they are showing" )
    @Step
    public void checkProductInformation()
    {
        ProductPage productPage = new ProductPage( driver );
        HomePage homePage = new HomePage( driver );

        try
        {
            homePage.openHomePage();
            homePage.waitForHomePageToLoad();
            homePage.clickOnAddToCart();
            productPage.checkProductImage();
            productPage.CheckProductTitle();
            productPage.CheckProductPrice();
            productPage.checkProductDescription();
            productPage.checkAndSelectSize();
            productPage.checkAndSelectcolour();
           
        }
        catch ( InterruptedException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    @Description( "Test if able to add product to cart" )
    @Step
    public void addProductToCart()
    {
        ProductPage productPage = new ProductPage( driver );
        HomePage homePage = new HomePage( driver );

        try
        {
            homePage.openHomePage();
            homePage.waitForHomePageToLoad();
            homePage.clickOnAddToCart();
            productPage.IncreaseQuantity();
            productPage.addToCart();
            productPage.verifySuccessMessage();
        }
        catch ( InterruptedException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
