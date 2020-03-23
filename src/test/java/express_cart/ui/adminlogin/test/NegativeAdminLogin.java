package express_cart.ui.adminlogin.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import express_cart.base.BaseTest;
import express_cart.pages.AdminLoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class NegativeAdminLogin
    extends BaseTest
{
    String userEmail = "owner@test.com";

    String userPassword = "test";

    String incorrectUserEmail = "wrong@test.com";

    String incorrectUserPassword = "wrongPassword";

    @Test
    @Description( "Test Admin Login page with incorrect user email" )
    @Step
    public void loginWithIncorrectEmail()
    {
        AdminLoginPage adminLoginPage = new AdminLoginPage( driver );
        try
        {

            adminLoginPage.openAdminLoginPage();
            adminLoginPage.waitForAdminLoginPage();
            adminLoginPage.fillInEmail( incorrectUserEmail );
            adminLoginPage.fillInPassword( userPassword );
            adminLoginPage.clickSignInButton();
            adminLoginPage.waitForErrorMessage();
            String currentErrorMessage = adminLoginPage.getErrorMessage();
            String expectedErrorMessage = "A user with that email does not exist.";
            Assert.assertEquals( currentErrorMessage, expectedErrorMessage,
                                 "Error message is incorrect or didn't appear" );
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            Assert.fail( e.getMessage(), e );
        }

    }

    @Test
    @Description( "Test Admin Login page with incorrect user password" )
    @Step
    public void loginWithIncorrectPassword()
    {

        AdminLoginPage adminLoginPage = new AdminLoginPage( driver );
        try
        {

            adminLoginPage.openAdminLoginPage();
            adminLoginPage.waitForAdminLoginPage();
            adminLoginPage.fillInEmail( userEmail );
            adminLoginPage.fillInPassword( incorrectUserPassword );
            adminLoginPage.clickSignInButton();
            adminLoginPage.waitForErrorMessage();
            String currentErrorMessage = adminLoginPage.getErrorMessage();
            String expectedErrorMessage = "Access denied. Check password and try again.";
            Assert.assertEquals( currentErrorMessage, expectedErrorMessage,
                                 "Error message is incorrect or didn't appear" );
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            Assert.fail( e.getMessage(), e );
        }

    }

    @Test
    @Description( "Test Admin Login page with incorrect user email and password" )
    @Step
    public void loginWithIncorrectPasswordAndEmail()
    {
        AdminLoginPage adminLoginPage = new AdminLoginPage( driver );
        try
        {
            adminLoginPage.openAdminLoginPage();
            adminLoginPage.waitForAdminLoginPage();
            adminLoginPage.fillInEmail( incorrectUserEmail );
            adminLoginPage.fillInPassword( incorrectUserPassword );
            adminLoginPage.clickSignInButton();
            adminLoginPage.waitForErrorMessage();
            String currentErrorMessage = adminLoginPage.getErrorMessage();
            String expectedErrorMessage = "A user with that email does not exist.";
            Assert.assertEquals( currentErrorMessage, expectedErrorMessage,
                                 "Error message is incorrect or didn't appear" );
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            Assert.fail( e.getMessage(), e );
        }

    }

}
