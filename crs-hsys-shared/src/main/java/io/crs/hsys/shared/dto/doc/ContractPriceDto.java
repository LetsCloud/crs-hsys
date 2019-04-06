/**
 * 
 */
package io.crs.hsys.shared.dto.doc;

import io.crs.hsys.shared.dto.Dto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class ContractPriceDto implements Dto {

	private ProductTypeDto type;

	private Double price;

	public ContractPriceDto() {
	}

	public ProductTypeDto getType() {
		return type;
	}

	public void setType(ProductTypeDto type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ContractPriceDto [type=" + type + ", price=" + price + "]";
	}

}
