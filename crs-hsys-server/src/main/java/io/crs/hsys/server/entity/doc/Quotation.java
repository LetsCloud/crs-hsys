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

	private Ref<QuotationType> type;

	public Quotation() {
	}

	public QuotationType getType() {
		if (type == null)
			return null;
		return type.get();
	}

	public void setType(QuotationType type) {
		if (type.getId() != null)
			this.type = Ref.create(type);
	}

	@Override
	public String toString() {
		return "Quotation [type=" + getType() + "]";
	}

}
