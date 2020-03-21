/**
 * 
 */
package io.crs.hsys.shared.dto.cube.dw;

import io.crs.hsys.shared.cnst.cube.Aggregation;
import io.crs.hsys.shared.cnst.cube.DataSource;
import io.crs.hsys.shared.cnst.cube.DataWidgetFieldType;
import io.crs.hsys.shared.cnst.cube.Dimension;
import io.crs.hsys.shared.cnst.cube.Measure;
import io.crs.hsys.shared.cnst.cube.QueryBreakdown;
import io.crs.hsys.shared.cnst.cube.QueryPeriodType;
import io.crs.hsys.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class DataWidgetFieldDto implements Dto {
	private static final String DEFAULT_FORMAT = "#,##0";
	
	/**
	 * Az adatcsempe mező típusa.
	 */
	private DataWidgetFieldType fieldType;

	/**
	 * Felirata.
	 */
	private String title;

	/**
	 * A mező által reprezentált mutató vagy annak első tagja.
	 */
	private Measure measure1;

	/**
	 * A mező által reprezentált mutató második tagja.
	 */
	private Measure measure2;

	/**
	 * Adat összesítő függvény.
	 */
	private Aggregation aggregation;

	/**
	 * Az adatforrás meghatározza azt vagy azokat az entitás(oka)t vagy
	 * táblá(ka)t amelyekből gyűjtendők az adatok.
	 */
	private DataSource dataSource;

	/**
	 * Adatgyűjtés időszakának típusa.
	 */
	private QueryPeriodType queryPeriodType;

	/**
	 * Adatgyűjtés időszakát meghatározó dimenzió. Általában
	 * Dimension.BSNS_DATE, de bizonyos esetekben Dimension.FCST_DATE is lehet.
	 */
	private Dimension periodDimension;

	/**
	 * A visszadott lekérdezés részletezését határozza meg. Azaz milyen idő
	 * alapú dimenzió képezze a részletezés/alábontás (group by) alapját.
	 */
	private QueryBreakdown breakdown;

	/**
	 * Megjelenítés formátuma.
	 */
	private String format;

	/**
	 * Megjelenítés előtagja.
	 */
	private String prefix;

	/**
	 * Megjelenítés utótagja.
	 */
	private String sufix;

	/**
	 * 
	 */
	private Float multiplier;

	/**
	 * 
	 */
	private String remark;

	public DataWidgetFieldDto() {
		this.aggregation = Aggregation.SUM;
		this.periodDimension = Dimension.BSNS_DATE;
		this.breakdown = QueryBreakdown.NONE;
		this.format = DEFAULT_FORMAT;
		this.multiplier = 1f;
	}

	public DataWidgetFieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(DataWidgetFieldType fieldType) {
		this.fieldType = fieldType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Measure getMeasure1() {
		return measure1;
	}

	public void setMeasure1(Measure measure1) {
		this.measure1 = measure1;
	}

	public Measure getMeasure2() {
		return measure2;
	}

	public void setMeasure2(Measure measure2) {
		this.measure2 = measure2;
	}

	public Aggregation getAggregation() {
		return aggregation;
	}

	public void setAggregation(Aggregation aggregation) {
		this.aggregation = aggregation;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public QueryPeriodType getQueryPeriodType() {
		return queryPeriodType;
	}

	public void setQueryPeriodType(QueryPeriodType queryPeriodType) {
		this.queryPeriodType = queryPeriodType;
	}

	public Dimension getPeriodDimension() {
		return periodDimension;
	}

	public void setPeriodDimension(Dimension periodDimension) {
		this.periodDimension = periodDimension;
	}

	public QueryBreakdown getBreakdown() {
		return breakdown;
	}

	public void setBreakdown(QueryBreakdown breakdown) {
		this.breakdown = breakdown;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSufix() {
		return sufix;
	}

	public void setSufix(String sufix) {
		this.sufix = sufix;
	}

	public Float getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(Float multiplier) {
		this.multiplier = multiplier;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 
	 * @author CR
	 *
	 */
	public static class Builder {
		private DataWidgetFieldDto field;

		public Builder() {
			field = new DataWidgetFieldDto();
		}

		public DataWidgetFieldDto build() {
			return field;
		}

		public Builder fieldType(DataWidgetFieldType fieldType) {
			field.setFieldType(fieldType);
			return this;
		}

		public Builder title(String title) {
			field.setTitle(title);
			return this;
		}

		public Builder measure1(Measure measure1) {
			field.setMeasure1(measure1);
			return this;
		}

		public Builder measure2(Measure measure2) {
			field.setMeasure1(measure2);
			return this;
		}

		public Builder aggregation(Aggregation aggregation) {
			field.setAggregation(aggregation);
			return this;
		}

		public Builder dataSource(DataSource dataSource) {
			field.setDataSource(dataSource);
			return this;
		}

		public Builder collectionPeriod(QueryPeriodType collectionPeriod) {
			field.setQueryPeriodType(collectionPeriod);
			return this;
		}

		public Builder periodField(Dimension periodField) {
			field.setPeriodDimension(periodField);
			return this;
		}

		public Builder breakdown(QueryBreakdown breakdown) {
			field.setBreakdown(breakdown);
			return this;
		}

		
		public Builder format(String format) {
			field.setFormat(format);
			return this;
		}

		
		public Builder prefix(String prefix) {
			field.setPrefix(prefix);
			return this;
		}

		public Builder sufix(String sufix) {
			field.setSufix(sufix);
			return this;
		}

		public Builder multiplier(Float multiplier) {
			field.setMultiplier(multiplier);
			return this;
		}

		public Builder remark(String remark) {
			field.setRemark(remark);
			return this;
		}
	}

}
