/**
 * 
 */
package io.crs.hsys.server.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.googlecode.objectify.stringifier.Stringifier;

/**
 * @author robi
 *
 */
public class DateStringifier implements Stringifier<Date> {
	@Override
	public String toString(Date obj) {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy.MM dd");
		return simpleDateformat.format(obj);
	}

	@Override
	public Date fromString(String str) {
		Calendar calendar = new GregorianCalendar(Integer.parseInt(str.substring(0, 4)),
				Integer.parseInt(str.substring(5, 7))-1, Integer.parseInt(str.substring(8, 10)));
		return calendar.getTime();
	}
}
