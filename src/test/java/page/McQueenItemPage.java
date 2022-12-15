package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WaitUtils;

public class McQueenItemPage extends AbstractPage{
    public static String MC_QUEEN_ITEM_PAGE_URL = "https://www.alexandermcqueen.com/en-us/boots/hybrid-chelsea-boot-586198WHX521000.html";
    private final String buttonSizeXpath = "//option[@data-attr-value='44']";
    private final String closePopOutMenuXpath = "//*[@id=\"onetrust-accept-btn-handler\"]";
    private final String buttonSizeDiffXpath = "//option[@data-attr-value='46']";
    private final String selectOpenXpath = "//select[@data-action='selectProductSize']";
    private final String cookiesXpath = "//*[@id=\"onetrust-accept-btn-handler\"]";
    private final String addToCartButtonXpath = "//a[@class=\"prod-info-price__cart-btn button cart-modal-open\"]";
    private final String errorMessadgeXpath = "//span[@data-msg='Please select a size']";
    private final String  amountOfProductsInCartXpath = "//span[@class='c-minicart__quantity']";

    @FindBy(xpath = buttonSizeXpath)
    private WebElement buttonSize;

    @FindBy(xpath = closePopOutMenuXpath)
    private WebElement closePopOutMenu;

    @FindBy(xpath = buttonSizeDiffXpath)
    private WebElement buttonSizeDiff;

    @FindBy(xpath = selectOpenXpath)
    private WebElement selectOpen;

    @FindBy(xpath = cookiesXpath)
    private WebElement cookies;

    @FindBy(xpath = addToCartButtonXpath)
    private WebElement addToCartButton;

    @FindBy(xpath = errorMessadgeXpath)
    private WebElement errorMessadge;

    @FindBy(xpath = amountOfProductsInCartXpath)
    private WebElement amountOfProductsInCart;

    public McQueenItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public McQueenItemPage closePopOutMenu() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(closePopOutMenuXpath, driver);
        closePopOutMenu.click();
        return this;
    }

    public McQueenItemPage closeCookies() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(cookiesXpath , driver);
        cookies.click();
        return this;
    }

    public McQueenItemPage clickSizeDiffButton() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(selectOpenXpath, driver);
        selectOpen.click();
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(buttonSizeDiffXpath, driver);
        buttonSize.click();
        //добавить просто ождиание
        return this;
    }

    public McQueenItemPage clickSizeButton() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(selectOpenXpath, driver);
        selectOpen.click();
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(buttonSizeXpath, driver);
        buttonSize.click();
        //добавить просто ождиание
        return this;
    }

    public McQueenItemPage addItemToCart() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(addToCartButtonXpath, driver);
        addToCartButton.click();
        return this;
    }

    public String tryGetErrorMessadge() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(errorMessadgeXpath, driver);
        return errorMessadge.getText();
    }

    public String getAmountOfProducts() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(amountOfProductsInCartXpath ,driver);
        WaitUtils.waitForNumberOfElementsLocatedByXpathToBe(amountOfProductsInCartXpath , 1 , driver );
        return amountOfProductsInCart.getText();
    }

    @Override
    public McQueenItemPage openPage() {
        driver.get(MC_QUEEN_ITEM_PAGE_URL);
        return this;
    }

}
