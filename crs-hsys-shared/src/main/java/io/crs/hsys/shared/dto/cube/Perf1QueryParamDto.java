/**
 * 
 */
package io.crs.hsys.shared.dto.cube;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.crs.hsys.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class Perf1QueryParamDto implements Dto {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fromDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date toDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fromBaseDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date toBaseDate;

	public Perf1QueryParamDto() {
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
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

	@Override
	public String toString() {
		String result = "CubeQueryParamDto=[fromDate:" + fromDate + ", toDate:" + toDate + ", fromBaseDate:"
				+ fromBaseDate + ", toBaseDate:" + toBaseDate + "]";
		return result;
	}
}
