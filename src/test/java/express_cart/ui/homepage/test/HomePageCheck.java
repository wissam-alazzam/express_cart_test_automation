package express_cart.ui.homepage.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import express_cart.base.BaseTest;
import express_cart.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

public class HomePageCheck
    extends BaseTest
{

    @Test( priority = 0 )
    @Description( "Test HomePage and general checks" )
    @Step
    public void checkHomePage()
    {
        HomePage homePage = new HomePage( driver );

        try
        {
            homePage.openHomePage();
            homePage.waitForHomePageToLoad();
            homePage.checkShopNameMatchesConfig();
            Thread.sleep( 2000 );
            homePage.checkLanguageSelector();
            Thread.sleep( 2000 );

        }
        catch ( Exception e )
        {

            e.printStackTrace();

            Assert.fail( e.getMessage(), e );
        }

    }

    @Test( priority = 1 )
    @Description( "Checks if the shop name showing on UI is the same one in configuration files" )
    @Step
    public void checkShopName()
    {
        HomePage homePage = new HomePage( driver );
        try
        {
            homePage.openHomePage();
            homePage.waitForHomePageToLoad();
            homePage.checkShopNameMatchesConfig();

        }
        catch ( Exception e )
        {
            e.printStackTrace();

            Assert.fail( e.getMessage(), e );
        }

    }

    @Test( priority = 2 )
    @Description( "Try to change the UI language to Itlain and checks if it actually chnaged" )
    @Flaky
    @Step
    @Severity( SeverityLevel.CRITICAL )
    public void changeLanguageToItalian()
    {

        HomePage homePage = new HomePage( driver );
        try
        {
            homePage.openHomePage();
            Thread.sleep( 2000 );
            homePage.waitForHomePageToLoad();
            Thread.sleep( 2000 );
            homePage.changeLanguage( "Italiano" );
            Thread.sleep( 2000 );

        }
        catch ( Exception e )
        {

            e.printStackTrace();

            Assert.fail( e.getMessage(), e );
        }

    }

    @Test( priority = 3 )
    @Description( "Check if cart is clickable and the side panel opens" )
    @Step
    public void checkCart()
    {
        HomePage homePage = new HomePage( driver );
        try
        {
            homePage.openHomePage();
            homePage.waitForHomePageToLoad();
            Thread.sleep( 2000 );
            homePage.checkCartIsOpen();
            Thread.sleep( 2000 );
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail( e.getMessage(), e );
        }
    }

    @Test( priority = 4 )
    @Description( "Check if product is clickable and showing all elements" )
    @Step
    public void checkProduct()
    {
        HomePage homePage = new HomePage( driver );
        try
        {
            homePage.openHomePage();
            homePage.waitForHomePageToLoad();
            Thread.sleep( 2000 );
            homePage.checkProductContainer();
            Thread.sleep( 2000 );
            homePage.clickOnAddToCart();
            Thread.sleep( 2000 );
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail( e.getMessage(), e );
        }
    }

    @Test( priority = 5 )
    @Description( "Check if product is clickable and showing all elements" )
    @Step
    public void checkNavigationMenu()
    {
        HomePage homePage = new HomePage( driver );
        try
        {
            homePage.openHomePage();
            homePage.waitForHomePageToLoad();
            Thread.sleep( 2000 );
            homePage.checkCategoryOpened();
            Thread.sleep( 2000 );
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail( e.getMessage(), e );
        }
    }

}
