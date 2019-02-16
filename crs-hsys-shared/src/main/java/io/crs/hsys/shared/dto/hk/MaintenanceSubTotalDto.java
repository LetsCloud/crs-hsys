/**
 * 
 */
package io.crs.hsys.shared.dto.hk;

/**
 * @author robi
 *
 */
public class MaintenanceSubTotalDto {

	private String title;
	private Integer col1;
	private Integer col2;
	private Integer col3;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCol1() {
		return col1;
	}

	public void setCol1(Integer col1) {
		this.col1 = col1;
	}

	public Integer getCol2() {
		return col2;
	}

	public void setCol2(Integer col2) {
		this.col2 = col2;
	}

	public Integer getCol3() {
		return col3;
	}

	public void setCol3(Integer col3) {
		this.col3 = col3;
	}

	public static class Builder {

		private String title;
		private Integer col1;
		private Integer col2;
		private Integer col3;

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder col1(Integer col1) {
			this.col1 = col1;
			return this;
		}

		public Builder col2(Integer col2) {
			this.col2 = col2;
			return this;
		}

		public Builder col3(Integer col3) {
			this.col3 = col3;
			return this;
		}

		public MaintenanceSubTotalDto build() {
			MaintenanceSubTotalDto dto = new MaintenanceSubTotalDto();
			dto.col1 = col1;
			dto.col2 = col2;
			dto.col3 = col3;
			dto.title = title;
			return dto;
		}
	}

}
