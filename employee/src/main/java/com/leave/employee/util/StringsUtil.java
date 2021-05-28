package com.leave.employee.util;

/**
 * Class having basic utility methods for processing on strings.
 * 
 * @author Rajasekhar.D
 *
 */
public class StringsUtil {

	private StringsUtil() {

	}

	/**
	 * Check whether two strings are equal. </br>
	 * Strings equality check are case insensitive.
	 * 
	 * @param str1
	 * @param str2
	 * @return true if equal else false
	 */
	public static boolean isEqualIgnoreCase(String str1, String str2) {
		if (ObjectsUtil.isNull(str1) || ObjectsUtil.isNull(str2)) {
			return false;
		}
		return str1.trim().equalsIgnoreCase(str2.trim());
	}

	public static StringBuilder appendIfNotNull(StringBuilder sb, String strToAppend) {
		if (sb == null) {
			return null;
		}
		if (strToAppend != null) {
			sb.append(", ").append(strToAppend);
		}
		return sb;
	}

	public static StringBuilder appendIfNotNull(StringBuilder sb, Long longToAppend) {
		if (sb == null) {
			return null;
		}
		if (longToAppend != null) {
			sb.append(longToAppend);
		}
		return sb;
	}

	public static boolean isNullOrEmpty(String str) {
		return (str == null) || str.trim().equals("");
	}

}
