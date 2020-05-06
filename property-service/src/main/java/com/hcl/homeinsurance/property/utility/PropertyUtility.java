package com.hcl.homeinsurance.property.utility;

public class PropertyUtility {
	private PropertyUtility() {
		throw new IllegalStateException("ProductsUtility class");
	}

	public static final String SAVE_ERROR = "save failure";
	public static final int SAVE_ERROR_STATUS = 606;

	public static final String UPDATE_ERROR = "update failure";
	public static final int UPDATE_ERROR_STATUS = 660;

	public static final String ADDRESS_ERROR = "address details not found";
	public static final int ADDRESS_ERROR_STATUS = 610;
	
	public static final String PROPERTY_ERROR = "property details not found";
	public static final int PROPERTY_ERROR_STATUS = 615;

	
}
