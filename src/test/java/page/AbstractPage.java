package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected abstract AbstractPage openPage();
    public static final Duration WAIT_TIMEOUT_SECONDS = Duration.ofSeconds(15);

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
