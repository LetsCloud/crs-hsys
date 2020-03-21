/**
 * 
 */
package io.crs.hsys.shared.dto.cube.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.crs.hsys.shared.cnst.cube.Dimension;
import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.hotel.HotelDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class CubeQueryParamDto implements Dto {

	private HotelDto hotelDto;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fromDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date toDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fromBaseDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date toBaseDate;

	private List<Dimension> dimensions = new ArrayList<Dimension>();

	private List<CubeMeasureParamDto> measures = new ArrayList<CubeMeasureParamDto>();

	public CubeQueryParamDto() {
	}

	public HotelDto getHotelDto() {
		return hotelDto;
	}

	public void setHotelDto(HotelDto hotelDto) {
		this.hotelDto = hotelDto;
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

	public List<Dimension> getDimensions() {
		return dimensions;
	}

	public void setDimensions(List<Dimension> dimensions) {
		this.dimensions = dimensions;
	}

	public void addDimension(Dimension dimension) {
		this.dimensions.add(dimension);
	}

	public List<CubeMeasureParamDto> getMeasures() {
		return measures;
	}

	public void setMeasures(List<CubeMeasureParamDto> measures) {
		this.measures = measures;
	}

	public void addMeasure(CubeMeasureParamDto measure) {
		measures.add(measure);
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

	@JsonIgnore
	public String getKey() {
		String key = hotelDto.getCode() + fromDate + toDate + fromBaseDate + toBaseDate;
		for (Dimension d : dimensions) {
			key += d;
		}
		return key;
	}

	@Override
	public String toString() {
		String result = "CubeQueryParamDto=[hotelDto:" + hotelDto + ", fromDate:" + fromDate + ", toDate:" + toDate
				+ ", fromBaseDate:" + fromBaseDate + ", toBaseDate:" + toBaseDate + ", dimensions:"
				+ dimensions.toString() + ", measures:" + measures.toString() + "]";
		return result;
	}
}
