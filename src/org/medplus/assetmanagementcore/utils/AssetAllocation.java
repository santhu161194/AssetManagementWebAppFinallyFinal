package org.medplus.assetmanagementcore.utils;


	
	import java.util.HashMap;
	import java.util.Map;
	
	public enum AssetAllocation {


		Allocated("A"), 
		DeAllocated("D"); 


		public String value;

		// Reverse-lookup map for getting a day from an abbreviation
		private static final Map<String, AssetAllocation > lookup = new HashMap<String, AssetAllocation >();

		static {
			for (AssetAllocation  assetStatus :AssetAllocation .values()) {
				lookup.put(assetStatus.getValue(), assetStatus);
			}
		}

		AssetAllocation (String v) {
			value = v;
		}

		public String getValue() {
			return value;
		}

		public static AssetAllocation  getName(String assetAllocation) {
			return lookup.get(assetAllocation);
		}
	}