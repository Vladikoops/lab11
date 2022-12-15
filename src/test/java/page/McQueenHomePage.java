package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaitUtils;

public class McQueenHomePage extends AbstractPage {
    public static String BIG_GEEK_HOME_PAGE_URL = "https://www.alexandermcqueen.com/en-us";
    private final String searchInputXpath = "//*[@id=\"searchPopin\"]/div/form/div[1]/input";
    private final String cookiesXpath = "//*[@id=\"onetrust-accept-btn-handler\"]";
    private final String filterAndSortXpath = "//*[@id=\"main-content\"]/div/div/div[3]/div[3]/div/div/div/button]";
    private final String searchButtonXpath = "//*[@id=\"mainnav\"]/div[1]/div[3]/div[1]/button";
    private final String searchButtonEnterXpath = "//*[@id=\"searchPopin\"]/div/form/div[1]/button[2]/span[1]";

    @FindBy(xpath = searchInputXpath)
    private WebElement searchInput;
    @FindBy(xpath = cookiesXpath)
    private WebElement cookies;
    @FindBy(xpath = searchButtonXpath)
    private WebElement searchButton;
    @FindBy(xpath = searchButtonEnterXpath)
    private WebElement searchButtonEnter;

    @FindBy(xpath = filterAndSortXpath)
    private WebElement filterAndSort;

    public McQueenHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public McQueenHomePage closeCookies() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(cookiesXpath, driver);
        cookies.click();
        return this;
    }

    public McQueenSearchResultPage searchForTerms(String searchQuery) {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(searchButtonXpath, driver);
        searchButton.click();
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(searchInputXpath, driver);
        searchInput.sendKeys(searchQuery);
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(searchButtonEnterXpath, driver);
        searchButtonEnter.click();
        //searchInput.sendKeys(Keys.ENTER);
        return new McQueenSearchResultPage(driver);
    }

    @Override
    public McQueenHomePage openPage() {
        driver.get(BIG_GEEK_HOME_PAGE_URL);
        return this;
    }
}
