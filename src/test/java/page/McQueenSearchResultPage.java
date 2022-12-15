package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaitUtils;

import java.util.*;

public class McQueenSearchResultPage extends AbstractPage {
    private final String filterAndSortXpath = "//*[@id=\"main-content\"]/div/div/div[3]/div[3]/div/div/div/button]";
    private final String cookiesXpath = "//*[@id=\"onetrust-accept-btn-handler\"]";
    private final String productOfSpecificSearchResultXpath = "//div[@class=\"digi-product\"]";
    private final String productsSearchResultListXpath = "//*[@id=\"main-content\"]/div";
    private final String emptySearchResultMessageXpath = "//*[@id=\"main-content\"]/div/div/div[1]/div/h1";
    private final String obtainedProductNameXpath = "//*[@id=\"586398WHX521000\"]/a/span";
    private final String obtainedProductPriceXpath = "//div[@class=\"digi-product__price\"]";
    private final String priceRangeFiltersXpath = "//input[@class=\"digi-facet-slider__input\"]";

    @FindBy(xpath = filterAndSortXpath)
    private WebElement filterAndSort;
    @FindBy(xpath = productsSearchResultListXpath)
    private List<WebElement> productsSearchResultList;

    @FindBy(xpath = cookiesXpath)
    private WebElement cookies;

    @FindBy(xpath = emptySearchResultMessageXpath)
    private WebElement emptySearchResultMessage;

    @FindBy(xpath = obtainedProductNameXpath)
    private WebElement obtainedProductName;

    @FindBy(xpath = obtainedProductPriceXpath)
    private WebElement obtainedProductPrice;

    @FindBy(xpath = priceRangeFiltersXpath)
    private List<WebElement> priceRangeFilters;

    public McQueenSearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public List<String> getSearchedItemsText() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(productsSearchResultListXpath, driver);
        ArrayList<String> itemTextList = new ArrayList<>();
        for (WebElement productItem : productsSearchResultList) {
            itemTextList.add(productItem.getText());
        }
        return itemTextList;
    }

    public McQueenSearchResultPage closeCookies() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(cookiesXpath , driver);
        cookies.click();
        return this;
    }

    public String getProductName() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(obtainedProductNameXpath, driver);
        return obtainedProductName.getText();
    }

    public String getProductPrice() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(obtainedProductPriceXpath, driver);
        return obtainedProductPrice.getText().substring(0, obtainedProductPrice.getText().length() - 1);
    }

    public String getEmptySearchResultMessage() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(emptySearchResultMessageXpath, driver);
        return emptySearchResultMessage.getText();
    }

    public McQueenSearchResultPage enterPriceRange(String sizeRange) {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(filterAndSortXpath, driver);
        for (WebElement priceRangeFilter : priceRangeFilters) {
            int numberOfDigitsToClean = 7;
            for (int i = 0; i < numberOfDigitsToClean; i++) {
                priceRangeFilter.sendKeys(Keys.BACK_SPACE);
            }
            priceRangeFilter.sendKeys(sizeRange);
            priceRangeFilter.sendKeys(Keys.ENTER);
        }

        WaitUtils.waitForNumberOfElementsLocatedByXpathToBe(productOfSpecificSearchResultXpath, 1, driver);
        return this;
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}

