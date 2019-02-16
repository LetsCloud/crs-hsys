/**
 * 
 */
package io.crs.hsys.shared.dto.hk;

import java.util.ArrayList;
import java.util.List;

/**
 * @author robi
 *
 */
public class MaintenanceSumDto {

	private String title;
	private List<MaintenanceSubTotalDto> subTotals = new ArrayList<MaintenanceSubTotalDto>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<MaintenanceSubTotalDto> getSubTotals() {
		return subTotals;
	}

	public void setSubTotals(List<MaintenanceSubTotalDto> subTotal) {
		this.subTotals = subTotal;
	}

	public static class Builder {

		private String title;
		private List<MaintenanceSubTotalDto> subTotals = new ArrayList<MaintenanceSubTotalDto>();

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder addSubTotal(MaintenanceSubTotalDto subTotal) {
			this.subTotals.add(subTotal);
			return this;
		}

		public MaintenanceSumDto build() {
			MaintenanceSumDto dto = new MaintenanceSumDto();
			dto.title = title;
			dto.subTotals = subTotals;
			return dto;
		}
	}

}
