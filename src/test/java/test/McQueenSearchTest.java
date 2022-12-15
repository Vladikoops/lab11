package test;

import model.Product;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.McQueenHomePage;
import page.McQueenSearchResultPage;
import service.ProductCreator;
import service.TestDataReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class McQueenSearchTest extends CommonConditions {
    public static String SEARCH_QUERY_FOR_COMMON_RESULTS = TestDataReader.getTestData("testdata.search_query_for_common_results");
    public static String INVALID_SEARCH_QUERY = TestDataReader.getTestData("testdata.invalid_search_query");
    public static String SEARCH_QUERY_FOR_SPECIFIC_SEARCH = TestDataReader.getTestData("testdata.search_query_for_specific_search");
    public static String SUBSTRING_OF_SEARCHED_ITEMS = TestDataReader.getTestData("testdata.substring_of_searched_items");
    public static String EMPTY_SEARCH_RESULT_MESSAGE = TestDataReader.getTestData("testdata.empty_search_result_message");

    @Test
    public void handleCommonSearchResultTest() {
        List<String> textOfSearchedItems = new McQueenHomePage(driver)
                .openPage()
                .closeCookies()
                .searchForTerms(SEARCH_QUERY_FOR_COMMON_RESULTS)
                .getSearchedItemsText();

        assertThat(textOfSearchedItems, everyItem(containsString(SUBSTRING_OF_SEARCHED_ITEMS)));
    }

    @Test
    public void handleIncorrectSearchQueryResultTest() {
        String searchResultMessage = new McQueenHomePage(driver)
                .openPage()
                .closeCookies()
                .searchForTerms(INVALID_SEARCH_QUERY)
                .getEmptySearchResultMessage();

        Assert.assertTrue(searchResultMessage.contains(EMPTY_SEARCH_RESULT_MESSAGE),
                "SEARCH RESULTS FOR ");
    }

    @Test
    public void handleSpecificSearchResultTest() {
        Product testProduct = ProductCreator.withCredentialsFromSpecificProperty();

        McQueenSearchResultPage mcQueenSearchResultPage = new McQueenHomePage(driver)
                .openPage()
                .closeCookies()
                .searchForTerms(SEARCH_QUERY_FOR_SPECIFIC_SEARCH)
                .enterPriceRange(testProduct.getPrice());

        String obtainedProductName = mcQueenSearchResultPage
                .getProductName();
        String obtainedProductPrice = mcQueenSearchResultPage
                .getProductPrice();

        assertThat(obtainedProductName, is(equalTo(testProduct.getName())));
        assertThat(obtainedProductPrice, is(equalTo(testProduct.getPrice())));
    }

}
