package com.barisaslan.trendyollinkconverter.common.constant;

public class Constants {

    public static final String DEEPLINK_PRODUCT_DETAIL_PAGE_PLACEHOLDER = "ty://?Page=Product&ContentId=%s";
    public static final String DEEPLINK_SEARCH_PAGE_PLACEHOLDER = "ty://?Page=Search&Query=%s";
    public static final String DEEPLINK_HOME_PAGE = "ty://?Page=Home";

    public static final String TRENDYOL_HOST_NAME_VALIDATOR_REGEX = "^(www\\.)?trendyol\\.com";
    public static final String URL_PRODUCT_DETAIL_PAGE_VALIDATOR_REGEX = "^/[a-zA-z]+/[a-zA-Z-_]+-p-[0-9]+";
    public static final String URL_PRODUCT_DETAIL_PAGE_CONTENT_ID_FINDER_REGEX = "^/[a-zA-z]+/[a-zA-Z-_]+-p-";
    public static final String URL_SEARCH_PAGE_VALIDATOR_REGEX = "^/sr\\?q=.+";
    public static final String URL_SEARCH_QUERY_FINDER_REGEX = "^/sr\\?q=";

    public static final String URL_MERCHANT_ID_KEY = "merchantId";
    public static final String URL_BOUTIQUE_ID_KEY = "boutiqueId";

    public static final String DEEPLINK_MERCHANT_ID_KEY = "MerchantId";
    public static final String DEEPLINK_BOUTIQUE_ID_KEY = "CampaignId";

    public static final String AMPERSAND_CHARACTER = "&";
    public static final String EQUAL_CHARACTER = "=";

}
