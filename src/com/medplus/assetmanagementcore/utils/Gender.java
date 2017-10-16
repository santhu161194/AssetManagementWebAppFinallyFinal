package com.medplus.assetmanagementcore.utils;

import java.util.HashMap;
import java.util.Map;

/*import java.util.HashMap;
import java.util.Map;

public enum Gender {
male,female
}*/
public enum Gender{

	MALE("M"), 
	FEMALE("F"); 


	public String value;

	// Reverse-lookup map for getting a day from an abbreviation
	private static final Map<String, Gender> lookup = new HashMap<String, Gender>();

	static {
		for (Gender invoiceType : Gender.values()) {
			lookup.put(invoiceType.getValue(), invoiceType);
		}
	}

	Gender(String v) {
		value = v;
	}

	public String getValue() {
		return value;
	}

	public static Gender getName(String invoiceType) {
		return lookup.get(invoiceType);
	}
}