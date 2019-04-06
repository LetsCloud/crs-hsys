/**
 * 
 */
package io.crs.hsys.shared.dto.doc;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class QuotationDto extends DocumentDto {

	private QuotationStatusDto status;

	public QuotationDto() {
	}

	public QuotationStatusDto getStatus() {
		return status;
	}

	public void setStatus(QuotationStatusDto status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "QuotationDto [status=" + status + "]";
	}
}
