/**
 * 
 */
package io.crs.hsys.server.entity.doc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Subclass;

/**
 * @author robi
 *
 */
@Subclass(index = true)
public class Contract extends Document {

	private Ref<ContractType> type;
	private List<ContractPrice> prices = new ArrayList<ContractPrice>();
	private Date contractDate;

	public Contract() {
	}

	public ContractType getType() {
		if (type == null)
			return null;
		return type.get();
	}

	public void setType(ContractType type) {
		if (type.getId() != null)
			this.type = Ref.create(type);
	}

	public List<ContractPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ContractPrice> prices) {
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
		return "Contract [type=" + getType() + ", prices=" + prices + ", contractDate=" + contractDate + "]";
	}

}
