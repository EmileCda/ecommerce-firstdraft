package fr.ecommerce.utils;

import java.text.SimpleDateFormat;

public abstract class Dates {
	
	private static final String formatDate = "dd/MM/yyyy";
	
	public static final java.util.Date convertDateSqlToUtil(java.sql.Date dateSql) {
		return new java.util.Date(dateSql.getTime());
	}
	
	public static final java.sql.Date convertDateUtilToSql(java.util.Date dateUtil) {
		return new java.sql.Date(dateUtil.getTime());
	}

	public static final java.util.Date convertExpirationToDate(String dateStr) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
		return formatter.parse(dateStr);
	}
	
	
	
	public static final java.util.Date convertStringToDate(String dateStr) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(formatDate);
		return formatter.parse(dateStr);
	}
	
	public static final String convertDateToString(java.sql.Date date) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(formatDate);
		return formatter.format(date);
	}
}
