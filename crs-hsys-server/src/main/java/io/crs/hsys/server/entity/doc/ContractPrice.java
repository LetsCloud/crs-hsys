/**
 * 
 */
package io.crs.hsys.server.entity.doc;

import com.googlecode.objectify.Ref;

/**
 * @author robi
 *
 */
public class ContractPrice {

	private Ref<ProductType> type;

	private Double price;

	public ContractPrice() {
	}

	public ProductType getType() {
		if (type == null)
			return null;
		return type.get();
	}

	public void setType(ProductType type) {
		if (type.getId() != null)
			this.type = Ref.create(type);
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ContractPrice [type=" + type + ", price=" + price + "]";
	}

}
