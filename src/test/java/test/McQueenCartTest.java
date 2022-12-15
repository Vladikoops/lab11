package test;

import model.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.McQueenCartPage;
import page.McQueenItemPage;
import service.ProductCreator;
import service.TestDataReader;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class McQueenCartTest extends CommonConditions {
    public final String APPROVAL_REMOVAL_MESSAGE = TestDataReader.getTestData("testdata.approval_removal_message");
    public final String DOUBLED_PRODUCT_PRICE  = TestDataReader.getTestData("testdata.product.doubled.price");
    public final String INVALID_PROMO_CODE = TestDataReader.getTestData("testdata.invalid_promo_code");
    public final String INVALID_PROMO_CODE_MESSAGE = TestDataReader.getTestData("testdata.invalid_promo_code_message");

    @Test
    public void addItemToCartTest() {
        Product testProduct = ProductCreator.withCredentialsFromProperty();

        new McQueenItemPage(driver)
                .openPage()
                .closeCookies()
                .clickSizeButton()
                .addItemToCart();

        McQueenCartPage mcQueenCartPage = new McQueenCartPage(driver);
        String addedProductName = mcQueenCartPage
                .openPage()
                .getProductName();
        String addedProductPrice = mcQueenCartPage
                .getProductPrice();

        assertThat(addedProductName, is(equalTo(testProduct.getName())));
        assertThat(addedProductPrice, is(equalTo(testProduct.getPrice())));
    }

    @Test
    public void addItemToCartWithoutSizeTest() {
        String messageAfterSelectingNoSize = new McQueenItemPage(driver)
                .openPage()
                .closeCookies()
                .tryGetErrorMessadge();
        assertThat(messageAfterSelectingNoSize, is(equalTo(APPROVAL_REMOVAL_MESSAGE)));
    }

    @Test(groups = {"addedItemToCartPreconditionIsNeeded"})
    public void priceChangeAfterAddingSecondSameItem() {
        String doubledPrice = new McQueenCartPage(driver)
                .openPage()
                .closeCookies()
                .addTheSameItem()
                .getProductPrice();
        assertThat(doubledPrice, is(equalTo(DOUBLED_PRODUCT_PRICE)));
    }

    @Test(groups = {"addedItemToCartPreconditionIsNeeded"})
    public void removeItemFromCartTest() {
        String messageAfterProductRemoval = new McQueenCartPage(driver)
                .openPage()
                .closeCookies()
                .removeItemFromCart()
                .getApprovalOfProductRemovalMessage();
        assertThat(messageAfterProductRemoval, is(equalTo(APPROVAL_REMOVAL_MESSAGE)));
    }

    @Test
    public void addItemsWithDifferentSizesToCartTest() {
        Product testProduct = ProductCreator.withCredentialsFromProperty();

        new McQueenItemPage(driver)
                .openPage()
                .closeCookies()
                .clickSizeButton()
                .addItemToCart()
                .closePopOutMenu()
                .clickSizeDiffButton()
                .addItemToCart();
        McQueenCartPage mcQueenCartPage = new McQueenCartPage(driver);
        String addedProductName = mcQueenCartPage
                .openPage()
                .closeCookies()
                .getProductName();
        String addedProductPrice = mcQueenCartPage
                .getProductPrice();

        assertThat(addedProductName, is(equalTo(testProduct.getName())));
        assertThat(addedProductPrice, is(equalTo(testProduct.getPrice())));
    }

    @Test(groups = {"addedItemToCartPreconditionIsNeeded"})
    public void restoreItemToCartTest() {
        Product testProduct = ProductCreator.withCredentialsFromProperty();

        McQueenCartPage bigGeekCartPage = new McQueenCartPage(driver)
                .openPage()
                .removeItemFromCart()
                .restoreItemToCart();
        String restoredProductName = bigGeekCartPage.getProductName();
        String restoredProductPrice = bigGeekCartPage.getProductPrice();

        assertThat(restoredProductName, is(equalTo(testProduct.getName())));
        assertThat(restoredProductPrice, is(equalTo(testProduct.getPrice())));
    }

    @Test(groups = {"addedItemToCartPreconditionIsNeeded"})
    public void useInvalidPromoCodeTest() {
        String promoCodeStatusMessage = new McQueenCartPage(driver)
                .openPage()
                .enterPromoCode(INVALID_PROMO_CODE)
                .getPromoCodeStatusMessage();
        Assert.assertTrue(promoCodeStatusMessage.contains(INVALID_PROMO_CODE_MESSAGE),
                "The error message of using invalid promo code was not shown!");
    }
}
