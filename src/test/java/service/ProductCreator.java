package service;

import model.Product;

public class ProductCreator {
    public static final String TESTDATA_PRODUCT_NAME = "testdata.product.name";
    public static final String TESTDATA_PRODUCT_PRICE = "testdata.product.price";
    public static final String TESTDATA_SPECIFIC_PRODUCT_NAME = "testdata.product.name_specific";
    public static final String TESTDATA_SPECIFIC_PRODUCT_PRICE = "testdata.product.price_specific";

    public static Product withCredentialsFromProperty(){
        return new Product(TestDataReader.getTestData(TESTDATA_PRODUCT_NAME),
                TestDataReader.getTestData(TESTDATA_PRODUCT_PRICE));
    }

    public static Product withCredentialsFromSpecificProperty() {
        return new Product(TestDataReader.getTestData(TESTDATA_SPECIFIC_PRODUCT_NAME),
                TestDataReader.getTestData(TESTDATA_SPECIFIC_PRODUCT_PRICE));
    }

}
