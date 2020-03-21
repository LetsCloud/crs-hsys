/**
 * 
 */
package io.crs.hsys.client.inf.gps;

import java.util.Date;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.datepicker.client.CalendarUtil;

import io.crs.hsys.shared.cnst.cube.DataSource;
import io.crs.hsys.shared.cnst.cube.Dimension;
import io.crs.hsys.shared.dto.hotel.HotelDto;

import static io.crs.hsys.shared.api.ApiParameters.DATE_FORMAT;

/**
 * @author CR
 *
 */
public class GpsState {

	private HotelDto hotel;

	private Date fromCurrDate;

	private Date toCurrDate;

	private Date fromBaseDate;

	private Date toBaseDate;

	private GpsScale scale;

	public GpsState() {
		DateTimeFormat fmt = DateTimeFormat.getFormat(DATE_FORMAT);
		fromCurrDate = fmt.parse("2015-06-01");
		toCurrDate = fmt.parse("2015-06-01");
		
		fromBaseDate = CalendarUtil.copyDate(fromCurrDate);
		toBaseDate = CalendarUtil.copyDate(toCurrDate);

		scale = GpsScale.DAILY;
	}

	public HotelDto getHotel() {
		return hotel;
	}

	public void setHotel(HotelDto hotel) {
		this.hotel = hotel;
	}

	public Date getFromCurrDate() {
		return fromCurrDate;
	}

	public void setFromCurrDate(Date fromCurrDate) {
		this.fromCurrDate = fromCurrDate;
	}

	public Date getToCurrDate() {
		return toCurrDate;
	}

	public void setToCurrDate(Date toCurrDate) {
		this.toCurrDate = toCurrDate;
	}

	public Date getFromBaseDate() {
		return fromBaseDate;
	}

	public void setFromBaseDate(Date fromBaseDate) {
		this.fromBaseDate = fromBaseDate;
	}

	public Date getToBaseDate() {
		return toBaseDate;
	}

	public void setToBaseDate(Date toBaseDate) {
		this.toBaseDate = toBaseDate;
	}

	public GpsScale getScale() {
		return scale;
	}

	public void setScale(GpsScale scale) {
		this.scale = scale;
	}

	public Dimension getPeriodDimension(DataSource dataSource) {
		switch (scale) {
		case DAILY:
			if (dataSource.equals(DataSource.FRO_FCST_CAPY) || dataSource.equals(DataSource.FRO_FCST_PERF)
					|| dataSource.equals(DataSource.FRO_FCST_PERF)) {
				return Dimension.FCST_DATE;

			} else {
				return Dimension.BSNS_DATE;
			}
		case WEEKLY:
			if (dataSource.equals(DataSource.FRO_FCST_CAPY) || dataSource.equals(DataSource.FRO_FCST_PERF)
					|| dataSource.equals(DataSource.FRO_FCST_PERF)) {
				return Dimension.FCST_WEEK;

			} else {
				return Dimension.BSNS_WEEK;
			}
		case MONTHLY:
			if (dataSource.equals(DataSource.FRO_FCST_CAPY) || dataSource.equals(DataSource.FRO_FCST_PERF)
					|| dataSource.equals(DataSource.FRO_FCST_PERF)) {
				return Dimension.FCST_MONTH;

			} else {
				return Dimension.BSNS_MONTH;
			}
		case QUARTERLY:
			if (dataSource.equals(DataSource.FRO_FCST_CAPY) || dataSource.equals(DataSource.FRO_FCST_PERF)
					|| dataSource.equals(DataSource.FRO_FCST_PERF)) {
				return Dimension.FCST_QUARTER;

			} else {
				return Dimension.BSNS_QUARTER;
			}
		case HALFYEARLY:
			if (dataSource.equals(DataSource.FRO_FCST_CAPY) || dataSource.equals(DataSource.FRO_FCST_PERF)
					|| dataSource.equals(DataSource.FRO_FCST_PERF)) {
				return Dimension.FCST_HALFYEAR;

			} else {
				return Dimension.BSNS_HALFYEAR;
			}
		case YEARLY:
			if (dataSource.equals(DataSource.FRO_FCST_CAPY) || dataSource.equals(DataSource.FRO_FCST_PERF)
					|| dataSource.equals(DataSource.FRO_FCST_PERF)) {
				return Dimension.FCST_YEAR;

			} else {
				return Dimension.BSNS_YEAR;
			}
		default:
			return null;
		}
	}
}
