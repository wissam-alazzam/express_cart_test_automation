package express_cart.ui.adminlogin.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import express_cart.base.BaseTest;
import express_cart.pages.AdminLoginPage;
import io.qameta.allure.Description;

public class PositiveAdminLogin
    extends BaseTest
{

    String userEmail = "owner@test.com";
    String userPassword = "test";

    @Test
    @Description("Testing login functionality with positive credentials")
    public void Login()
    {
        try
        {
            AdminLoginPage adminLogingPage = new AdminLoginPage( driver );
            adminLogingPage.openAdminLoginPage();
            adminLogingPage.waitForAdminLoginPage();
            adminLogingPage.fillInEmail( userEmail );
            adminLogingPage.fillInPassword( userPassword );
            adminLogingPage.clickSignInButton();
            adminLogingPage.verifySigninSuccessful();
        }
        catch ( Exception e )
        {
           
            Assert.fail( e.getMessage(), e );
        }

    }

}
