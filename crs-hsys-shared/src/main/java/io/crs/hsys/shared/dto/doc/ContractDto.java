/**
 * 
 */
package io.crs.hsys.shared.dto.doc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class ContractDto extends DocumentDto {

	private ContractTypeDto type;
	private List<ContractPriceDto> prices = new ArrayList<ContractPriceDto>();
	private Date contractDate;

	public ContractDto() {
	}

	public ContractTypeDto getType() {
		return type;
	}

	public void setType(ContractTypeDto type) {
		this.type = type;
	}

	public List<ContractPriceDto> getPrices() {
		return prices;
	}

	public void setPrices(List<ContractPriceDto> prices) {
		this.prices = prices;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	@Override
	public String toString() {
		return "ContractDto [type=" + type + ", prices=" + prices + ", contractDate=" + contractDate + "]";
	}

}
