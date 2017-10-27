
package org.medplus.assetmanagementcore.utils;

import java.util.HashMap;
import java.util.Map;


public enum AssetStatus {

	Available("A"), 
	NotAvailable("N"); 


	public String value;

	
	private static final Map<String, AssetStatus > lookup = new HashMap<String, AssetStatus >();

	static {
		for (AssetStatus  assetStatus : AssetStatus .values()) {
			lookup.put(assetStatus.getValue(), assetStatus);
		}
	}

	AssetStatus (String v) {
		value = v;
	}

	public String getValue() {
		return value;
	}

	public static AssetStatus  getName(String assetStatus) {
		return lookup.get(assetStatus);
	}
}