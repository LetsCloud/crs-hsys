/**
 * 
 */
package io.crs.hsys.server.entity.doc;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Subclass;

/**
 * @author robi
 *
 */
@Subclass(index = true)
public class Quotation extends Document {
	public static final String PROPERTY_STATUS = "status";

	private Ref<QuotationStatus> status;

	public Quotation() {
	}

	public QuotationStatus getStatus() {
		if (status == null)
			return null;
		return status.get();
	}

	public void setStatus(QuotationStatus type) {
		if (type.getId() != null)
			this.status = Ref.create(type);
	}

	@Override
	public String toString() {
		return "Quotation [type=" + getStatus() + "]";
	}

}
