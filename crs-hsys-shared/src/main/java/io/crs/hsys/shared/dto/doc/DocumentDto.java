/**
 * 
 */
package io.crs.hsys.shared.dto.doc;

import java.util.Date;

import io.crs.hsys.shared.dto.common.AccountChildDto;
import io.crs.hsys.shared.dto.profile.OrganizationDtor;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class DocumentDto extends AccountChildDto {
	
	private String code;
	
	private String description;

	private Date postingDate;

	private OrganizationDtor organization;

	public DocumentDto() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public OrganizationDtor getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDtor organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "DocumentDto [code=" + code + ", postingDate=" + postingDate + ", organization=" + organization + "]";
	}

}
