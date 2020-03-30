package express_cart.ui.cartpage.test;

import org.testng.annotations.Test;

import express_cart.base.BaseTest;
import express_cart.pages.CartPage;
import express_cart.pages.HomePage;

public class CartPageCheck extends BaseTest
{
    /*
     * TODO 
     */
    @Test
    public void test() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage( driver );
        homePage.openHomePage();
        cartPage.addRandomItemToCart();
        
    }

}
