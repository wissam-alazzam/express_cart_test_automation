package express_cart.ui.product.test;

import org.testng.annotations.Test;

import express_cart.base.BaseTest;
import express_cart.pages.HomePage;
import express_cart.pages.ProductPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class ProductPageCheck extends BaseTest
{
    @Test
    @Description
    @Step
    public void checkProductInformation() {
        ProductPage productPage = new ProductPage( driver );
        HomePage homePage = new  HomePage( driver );
        
        try
        {
            homePage.openHomePage();
            homePage.waitForHomePageToLoad();
            homePage.clickOnAddToCart();
            productPage.checkProductImage();
        }
        catch ( InterruptedException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
