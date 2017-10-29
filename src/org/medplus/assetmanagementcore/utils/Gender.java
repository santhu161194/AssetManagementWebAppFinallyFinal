package org.medplus.assetmanagementcore.utils;

import java.util.HashMap;
import java.util.Map;


public enum Gender{

	MALE("M"), 
	FEMALE("F"); 


	public String value;

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