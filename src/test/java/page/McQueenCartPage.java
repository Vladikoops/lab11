package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaitUtils;

public class McQueenCartPage extends AbstractPage {
    public static String BIG_GEEK_CART_PAGE_URL = "https://www.alexandermcqueen.com/en-us/cart";
    private final String cookiesXpath = "//*[@id=\"onetrust-accept-btn-handler\"]";
    private final String closeModalWarningWindowButtonXpath = "//button[@class=\"we-closed-modal__close\"]";
    private final String productNameXpath = "//a[@class=\"c-lineitem__infos\" && @data-ref=\"lineitemName\"]";
    private final String productPriceXpath = "//p[@class=\"c-price__value--current\"]/b";
    private final String removeItemButtonXpath = "//button[@class=\"c-lineitem__removebtn\"]";
    private final String approvalOfProductRemovalMessageXpath = "//div[@class=\"u-align--center\"]";
    private final String restoreItemButtonXpath = "//button[@class=\"c-button c-button--secondary c-button--animation\"]";
    private final String promoCodeInputId = "\"zipCode\"";
    private final String promoCodeSubmitButtonXpath = "//*[@id=\"taxForm\"]/div[1]/div[2]/button]";
    private final String promoCodeStatusMessageXpath = "//*[@id=\"c-summary__zipcodeerror--message\"]";
    private final String addSameItemXpath = "//*[@id=\"main-content\"]/div/div[2]/div/div[1]/div[1]/div[1]/div[2]/div[2]/div/button[2]]";

    @FindBy(xpath = closeModalWarningWindowButtonXpath)
    private WebElement closeModalWarningWindowButton;

    @FindBy(xpath = cookiesXpath)
    private WebElement cookies;

    @FindBy(xpath = productNameXpath)
    private WebElement productName;

    @FindBy(xpath = productPriceXpath)
    private WebElement productPrice;

    @FindBy(xpath = removeItemButtonXpath)
    private WebElement removeItemButton;

    @FindBy(xpath = approvalOfProductRemovalMessageXpath)
    private WebElement approvalOfProductRemovalMessage;

    @FindBy(xpath = restoreItemButtonXpath)
    private WebElement restoreItemButton;

    @FindBy(xpath = promoCodeInputId)
    private WebElement promoCodeInput;

    @FindBy(xpath = promoCodeSubmitButtonXpath)
    private WebElement promoCodeSubmitButton;

    @FindBy(xpath = promoCodeStatusMessageXpath)
    private WebElement promoCodeStatusMessage;

    @FindBy(xpath = addSameItemXpath)
    private WebElement addSameItem;

    public McQueenCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getProductName() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(productNameXpath, driver);
        return productName.getText();
    }

    public String getProductPrice() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(productPriceXpath, driver);
        return productPrice.getText().substring(0, productPrice.getText().length() - 1);
    }

    public McQueenCartPage closeCookies() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(cookiesXpath , driver);
        cookies.click();
        return this;
    }

    public McQueenCartPage removeItemFromCart() {
        removeItemButton.click();
        return this;
    }

    public String getApprovalOfProductRemovalMessage() {
        return approvalOfProductRemovalMessage.getText();
    }

    public McQueenCartPage restoreItemToCart() {
        restoreItemButton.click();
        return this;
    }

    public McQueenCartPage enterPromoCode(String promoCode) {
        promoCodeInput.sendKeys(promoCode);
        promoCodeSubmitButton.click();
        return this;
    }

    public McQueenCartPage addTheSameItem() {
        restoreItemButton.click();
        return this;
    }

    public String getPromoCodeStatusMessage() {
        return promoCodeStatusMessage.getText();
    }

    @Override
    public McQueenCartPage openPage() {
        driver.get(BIG_GEEK_CART_PAGE_URL);
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(closeModalWarningWindowButtonXpath, driver);
        closeModalWarningWindowButton.click();
        return this;
    }

}
