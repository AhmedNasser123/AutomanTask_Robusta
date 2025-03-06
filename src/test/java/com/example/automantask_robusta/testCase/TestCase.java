package com.example.automantask_robusta.testCase;

import Pages.*;
import com.example.automantask_robusta.baseTest.BaseTest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import configReader.Constants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.List;
import java.util.concurrent.TimeoutException;

import static base.DriverManager.*;
import static configReader.ConfigReader.config;


public class TestCase extends BaseTest {

    //Optional Bonus Step: Use data provided by the JSONPlaceholder API to
    @DataProvider(name = "userData")
    public Object[][] provideUserData() {
        APIRequestContext request = playwright.get().request().newContext();
        APIResponse response = request.get((String)config.getProperty( "JSONPlaceholder")+config.getProperty("JSONPlaceholderUser")); // calling From Driver Manager
        String responseBody = response.text();
        JsonObject userData = JsonParser.parseString(responseBody).getAsJsonObject(); //convert by gson lib
        String fullName = userData.get("name").getAsString();
        String[] names = fullName.split(" ");
        String firstName = names[0];
        String lastName = names[1];
        String zipCode = userData.getAsJsonObject("address").get("zipcode").getAsString();

        return new Object[][]{
                {firstName, lastName, zipCode}
        };
    }

    @Test(dataProvider = "userData")
    public void Verify_SauceDemo(String firstName, String lastName, String zipCode) throws TimeoutException, InterruptedException {
        LoginPage loginPage = new LoginPage(page.get());
        InventoryPage inventoryPage = new InventoryPage(page.get());
        YourCartPage yourCartPage = new YourCartPage(page.get());
        CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage(page.get());
        OverViewPage overViewPage = new OverViewPage(page.get());
        // 1 - Login to the website using the standard user credentials on the login screen.
        loginPage.navigateToLoginPage(String.valueOf(config.getProperty("BaseURL")));
        String ReturnCurrentURL = loginPage.SubmitLoginByStandardUser(Constants.StandardUserName, Constants.Password); // LoginPage
        String currentURL = (String) config.getProperty("BaseURL") + config.getProperty("HomePageURl");
        Assert.assertEquals(ReturnCurrentURL, currentURL, "Expected Current URl Not Correct but Found : " + ReturnCurrentURL);
        // 2 - Add the product "Sauce Labs Fleece Jacket" to the cart.
        String ReturnCountCart = inventoryPage.AddFleeceJacketToCart();
        Assert.assertEquals(ReturnCountCart, "1", "Fleece Jacket has not been added to cart");
        // 3 - Open the "Sauce Labs Onesie" product details page and add it to the cart.
        boolean ReturnDisplayDetailsPage = inventoryPage.AddProductToCart();
        Assert.assertTrue(ReturnDisplayDetailsPage, "Details Page not displayed ");
        // 4 - Assert the number of products displayed on the cart icon (should be 2).
        String ReturnCountShoppingCart = inventoryPage.AddToCartFromDetails();
        Assert.assertEquals(ReturnCountShoppingCart, "2", "Return Count ShoppingCart Not 2 but found " + ReturnCountShoppingCart);
        //5 -  Navigate to the cart page and verify the names and prices of the added products.
        loginPage.navigateToLoginPage(String.valueOf(config.getProperty("BaseURL")) + config.getProperty("YourCart"));
        List<String> returnedNamesAndPrices = yourCartPage.getAllNamesAndPricesAddedProducts();
        Assert.assertEquals(returnedNamesAndPrices.get(0), "Sauce Labs Fleece Jacket - $49.99", "NamesAndPrices Not correct but found : " + returnedNamesAndPrices.get(0));
        Assert.assertEquals(returnedNamesAndPrices.get(1), "Sauce Labs Onesie - $7.99", "NamesAndPrices Not correct but found : " + returnedNamesAndPrices.get(1));
        //6 - Proceed to checkout, dynamically entering user data
        // Optional Bonus Step: Use data provided by the JSONPlaceholder API
        Assert.assertEquals(yourCartPage.Checkout(), "Checkout: Your Information", "Your Information not disabled");
        String ReturnCheckoutInfoPage = checkoutInfoPage.InsertUserData(firstName, lastName, zipCode);
        Assert.assertEquals(ReturnCheckoutInfoPage, "Checkout: Overview", "Checkout: Overview not disabled");
        // 7 - Assert the order details on the overview page
        List<String> returnedNamesAndPricesOverview = yourCartPage.getAllNamesAndPricesAddedProducts();
        Assert.assertEquals(returnedNamesAndPricesOverview.get(0), "Sauce Labs Fleece Jacket - $49.99", "NamesAndPrices Not correct but found : " + returnedNamesAndPrices.get(0));
        Assert.assertEquals(returnedNamesAndPricesOverview.get(1), "Sauce Labs Onesie - $7.99", "NamesAndPrices Not correct but found : " + returnedNamesAndPrices.get(1));
        // 8 - Complete the purchase by clicking Finish and verifying the "Complete" page.
        overViewPage.FinishOrder();
        Assert.assertTrue(overViewPage.CompleteOrder(), "Order not Completed but found " + overViewPage.CompleteOrder());

    }
}
