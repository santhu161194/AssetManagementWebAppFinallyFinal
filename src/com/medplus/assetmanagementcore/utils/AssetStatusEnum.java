/*package com.medplus.assetmanagementcore.utils;

public enum AssetStatusEnum 
	{Available,NotAvailable}
	
	 

	*/
package com.medplus.assetmanagementcore.utils;

import java.util.HashMap;
import java.util.Map;

/*import java.util.HashMap;
import java.util.Map;

public enum Gender {
male,female
}*/
public enum AssetStatusEnum {

	Available("A"), 
	NotAvailable("N"); 


	public String value;

	// Reverse-lookup map for getting a day from an abbreviation
	private static final Map<String, AssetStatusEnum > lookup = new HashMap<String, AssetStatusEnum >();

	static {
		for (AssetStatusEnum  assetStatus : AssetStatusEnum .values()) {
			lookup.put(assetStatus.getValue(), assetStatus);
		}
	}

	AssetStatusEnum (String v) {
		value = v;
	}

	public String getValue() {
		return value;
	}

	public static AssetStatusEnum  getName(String assetStatus) {
		return lookup.get(assetStatus);
	}
}