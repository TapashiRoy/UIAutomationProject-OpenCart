package com.qa.OpenCartE2EAutomationProject.Constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	public static final int DEFAULT_SHORT_TIME_OUT = 5;
	public static final int DEFAULT_MEDIUM_TIME_OUT = 7;
	public static final int DEFAULT_LONG_TIME_OUT = 12;

	// ********PAGE CONSTANTS********************
	public static final String LOGIN_PAGE_TITLE ="Account Login";
	public static final String LOGIN_PAGE_URL ="account/login";

	public static final String ACCOUNT_PAGE_TITLE ="My Account";
	public static final String ACCOUNT_PAGE_URL ="account/account";

	public static final String LANDING_PAGE_TITLE ="Your Store";
	public static final String LANDING_PAGE_URL ="common/home";

	public static final String REGISTER_PAGE_TITLE ="Register Account";
	public static final String REGISTER_PAGE_URL ="account/register";

	public static final String FORGOTTENPASSWORD_PAGE_TITLE ="Forgot Your Password?";
	public static final String FORGOTTENPASSWORD_PAGE_URL ="account/forgotten";

	public static final String ADDRESSBOOK_PAGE_TITLE ="Address Book";
	public static final String ADDRESSBOOK_PAGE_URL ="account/address";

	public static final String EDITACCOUNT_PAGE_TITLE ="My Account Information";
	public static final String EDITACCOUNT_PAGE_URL ="account/edit";

	public static final String CHANGEPASSWORD_PAGE_TITLE ="Change Password";
	public static final String CHANGEPASSWORD_PAGE_URL ="account/password";

	public static final String WISHLIST_PAGE_TITLE ="My Wish List";
	public static final String WISHLIST_PAGE_URL ="account/wishlist";

	public static final String ORDERHISTORY_PAGE_TITLE ="Order History";
	public static final String ORDERHISTORY_PAGE_URL ="account/order";

	public static final Object ACCOUNTPAGE_HEADER_COUNT = 4;
	public static final List<String> CURRENCY_DROPDOWN_LIST = Arrays.asList("€ Euro", "£ Pound Sterling","$ US Dollar");
	public static final List<String> MYACCOUNT_MENU_ITEMS = Arrays.asList("Register", "Login");
	public static final List<String> HEADER_MENU_ITEMS = Arrays.asList("Desktops", "Laptops & Notebooks", "Components", "Tablets",
			"Software", "Phones & PDAs", "Cameras", "MP3 Players");
	public static final int FEATURED_PRODUCTS_COUNT =4;
	public static final int FOOTER_MENU_COUNT =15 ;
	public static final String FOOTER_TEXT ="naveenopencart © 2024";

	// ********ERROR/SUCCESS MESSAGES********************
	public static final String CUSTOMER_REGISTRATION_SUCCESS_MSG ="Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "register";


}
