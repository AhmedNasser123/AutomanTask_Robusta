package Pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import utils.LoggerUtil;

public class InventoryPage extends BasePage {

    private final LoggerUtil logger;

    public InventoryPage(Page page) {
        super(page);
        logger = new LoggerUtil(InventoryPage.class);
    }

    private static final String Btn_AddCartFleeceJacket = "#add-to-cart-sauce-labs-fleece-jacket";
    private static final String CountShoppingCart = ".shopping_cart_badge";
   // private static final String label_inventory_item_name = "//div[@class='inventory_item_name']";
    private static final String inventory_item_name = "//div[normalize-space()='Sauce Labs Onesie']";
    private static final String inventory_item_nameDetails = "#back-to-products";
    private static final String btn_AddToCart = "#add-to-cart";

    public String AddFleeceJacketToCart() {
        click(Btn_AddCartFleeceJacket);
        logger.info("CountShoppingCart" + getText(CountShoppingCart));
        return getText(CountShoppingCart);
    }

    public Boolean AddProductToCart() {
        // Method 1
//        List<Locator> valueNumber = page.locator(label_inventory_item_name).all();
//        for (Locator value : valueNumber) {
//            String valueText = value.innerText().trim();
//            if (valueText.equals("Sauce Labs Onesie")) {
//               click(String.valueOf(value));
//            }
//        }
        //Method 2
         click(inventory_item_name);
        return isDisplayed(inventory_item_nameDetails);
    }

    public String AddToCartFromDetails() {
        click(btn_AddToCart);
        logger.info("CountShoppingCart" + getText(CountShoppingCart));
        return getText(CountShoppingCart);
    }


}
