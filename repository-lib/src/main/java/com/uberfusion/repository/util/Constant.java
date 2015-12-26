package com.uberfusion.repository.util;

public final class Constant {
    /**
     * Database
     */
    public static final class Database {
        public static final String TABLE_CACHE = "cache";
        public static final String TABLE_ERRORLOG = "error_log";
    }

    /**
     * Format
     */
    public static final class Format {
        public static final String DATE = "yyyy-MM-dd";
        public static final String DATETIME24 = DATE + " HH:mm:ss";
        public static final String DATETIME12 = DATE + " h:mm:ss a";
    }

    /**
     * Key
     */
    public static final class Key {
        public static final String VERSION = "version";
        public static final String REFERENCEID = "reference_id";
        public static final String DATA = "data";
        public static final String TRANSACTIONID = "transaction_id";
        public static final String ERRORCODE = "error_code";
        public static final String ERRORMESSAGE = "error_message";
        public static final String ERRORCATEGORY = "error_category";
        public static final String NAME = "name";
        public static final String SCREEN = "screen";
        public static final String WIDTH = "width";
        public static final String HEIGHT = "height";
        public static final String REGION = "region";
        public static final String BANNER = "banner";
        public static final String HREF = "href";
        public static final String HORIZONTALIMAGE = "horizontal_image";
        public static final String SRC = "src";
        public static final String VERTICALIMAGE = "vertical_image";
        public static final String LOCATION = "location";
        public static final String VALUE = "value";
        public static final String CATEGORYLIST = "category-list";
        public static final String CATEGORY = "category";
        public static final String CATEGORYNAME = "cat-name";
        public static final String CATEGORYVALUE = "cat-value";
        public static final String MERCHANTLIST = "merchant-list";
        public static final String PARTNER = "partner";
        public static final String MERCHANT = "merchant";
        public static final String LATEST = "latest";
        public static final String ID = "id";
        public static final String LOGO = "logo";
        public static final String IMAGE = "image";
        public static final String INFO = "info";
        public static final String DETAIL = "detail";
        public static final String TITLE = "title";
        public static final String TYPE = "type";
        public static final String DESCRIPTION = "desc";
        public static final String CAPTION = "caption";
        public static final String ROW = "row";
        public static final String COL = "col";
        public static final String ENTRY = "entry";
        public static final String CLASS = "att_class";
        public static final String LOCATOR = "locator";
        public static final String JOHOR = "johor";
        public static final String KEDAH = "kedah";
        public static final String KELANTAN = "kelantan";
        public static final String KL = "kl";
        public static final String MELAKA = "melaka";
        public static final String NS = "ns";
        public static final String PAHANG = "pahang";
        public static final String PENANG = "penang";
        public static final String PERAK = "perak";
        public static final String SABAH = "sabah";
        public static final String SARAWAK = "sarawak";
        public static final String SELANGOR = "selangor";
        public static final String TERENGGANU = "terengganu";
        public static final String ITEM = "item";
        public static final String SHOPTYPE = "shopType";
        public static final String ADDRESS = "address";
        public static final String OPERATINGHOUR = "operatingHour";
        public static final String IPHONE = "iPhone";
        public static final String SMARTPHONES = "Smartphones";
        public static final String BROADBAND = "Broadband";
        public static final String TWOFOURHOURPAYMENTKIOSK = "twoFourHourPaymentKiosk";
        public static final String SIMREPLACEMENT = "SIMReplacement";
        public static final String BILLPAYMENT = "BillPayment";
        public static final String LINEACTIVATION = "LineActivation";
        public static final String TRANSFEROWNERSHIP = "TransferOwnership";
        public static final String MNP = "MNP";
        public static final String CREDITCARDEASYPAYMENT = "CreditcardEasyPayment";
        public static final String BUSINESSSMESIGNUPS = "BusinessSMEsignups";
        public static final String CHANGEUPGRADEPLANS = "ChangeUpgradePlans";
        public static final String ACCOUNTTERMINATION = "AccountTermination";
        public static final String PHONEWARRANTYSERVICE = "PhoneWarrantyService";
        public static final String SIMPLEMASTERCARD = "SimpleMasterCard";
        public static final String LIVEDEMOPHONES = "LiveDemoPhones";
        public static final String ACCESSORIES = "Accessories";
        public static final String PHONEINSURANCE = "PhonesInsurance";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String ROWID = "_id";
        public static final String MODULE = "module";
        public static final String DATE = "date";
    }

    /**
     * Time
     */
    public static final class Time {
        public static final int ONE_SECOND = 1000;
        public static final int THIRTY_SECONDS = 30 * ONE_SECOND;
    }

    /**
     * Type
     */
    public static final class Type {
        public static final String REPOSITORY_PACKAGE = "com.uberfusion.repository";
    }

    /**
     * Url
     */
    public static final class Url {
        public static final String BASE = "http://www.uberfusion.com.my";
        public static final String BASEXML = "http://new.uberfusion.com.my";
        public static final String XML_BANNER = BASE + "/shared/ocs-moba-xml/banner/banner.xml";
        public static final String XML_REGION = BASEXML + "/cs/site_template/ecommerce/xml/privileges/regions.xml";
        public static final String XML_MERCHANT = BASEXML + "/cs/site_template/ecommerce/xml/privileges/merchants.xml";
        public static final String XML_LOCATOR = BASEXML + "/cs/site_template/ecommerce/xml/locator.xml";
    }

    /**
     * Value
     */
    public static final class Value {
        public static final int STREAM_BUFFERSIZE = 1024;
        public static final String XML_CACHE_DATE = "2014-06-05 08:00:00";
    }
}