package lm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by limin on 16/01/07.
 */
public class Sql {
	public static String querySql(
			String table, String[] columns, String selection, Object[] selectionArgs,
			String groupBy, String having, String orderBy, String limit) {

		StringBuilder builder = new StringBuilder();

		String columnsPart = columns == null ? "*" : combination(columns);
		String selectionPart = selection == null ? "" :
				combinationWithArgs(selection, selectionArgs);

		builder.append("SELECT ").append(columnsPart.isEmpty() ? "*" : columnsPart);
		builder.append(" FROM ").append(table);

		if(selectionPart != null && !selectionPart.isEmpty()) {
			builder.append(" WHERE ").append(selectionPart);
		}

		if(groupBy != null && !groupBy.isEmpty()) {
			builder.append(" GROUP BY ").append(groupBy);
		}

		if(having != null && !having.isEmpty()) {
			builder.append(" HAVING ").append(having);
		}

		if(orderBy != null && !orderBy.isEmpty()) {
			builder.append(" ORDER BY ").append(orderBy);
		}

		if(limit != null && !limit.isEmpty()) {
			builder.append(" LIMIT ").append(limit);
		}

		return builder.toString();
	}

	public static String querySql(
			String table, String[] columns, String selection, Object[] selectionArgs,
			String groupBy, String having, String orderBy) {
		return querySql(table, columns, selection, selectionArgs, groupBy, having, orderBy, null);
	}

	public static String querySql(
			String table, String[] columns, String selection, Object[] selectionArgs,
			String groupBy, String having) {
		return querySql(table, columns, selection, selectionArgs, groupBy, having, null);
	}

	public static String querySql(
			String table, String[] columns, String selection, Object[] selectionArgs,
			String groupBy) {
		return querySql(table, columns, selection, selectionArgs, groupBy, null);
	}

	public static String querySql(
			String table, String[] columns, String selection, Object[] selectionArgs) {
		return querySql(table, columns, selection, selectionArgs, null);
	}

	private static String combination(String[] parts) {
		StringBuilder builder = new StringBuilder();

		for(String part : parts) {
			if(builder.length() == 0) {
				builder.append(part);
			}
			else {
				builder.append(',').append(part);
			}
		}

		return builder.toString();
	}

	private static String combinationWithArgs(String string, Object[] args) {
		if(string == null || args == null || args.length == 0) {
			return string;
		}

		Pattern pat = Pattern.compile("\\?");
		Matcher matcher = pat.matcher(string);

		StringBuffer sb = new StringBuffer();

		int argsIndex = 0;
		while(matcher.find() && argsIndex < args.length) {
			Object arg = args[argsIndex];
			if(arg instanceof Number) {
				matcher.appendReplacement(sb, String.valueOf(arg));
			}
			else {
				matcher.appendReplacement(sb, arg.toString());
			}
		}

		return sb.toString();
	}
}