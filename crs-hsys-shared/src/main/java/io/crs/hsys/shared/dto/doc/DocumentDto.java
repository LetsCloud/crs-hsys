/**
 * 
 */
package io.crs.hsys.shared.dto.doc;

import java.util.Date;

import io.crs.hsys.shared.dto.common.AccountChildDto;
import io.crs.hsys.shared.dto.common.AppUserDtor;
import io.crs.hsys.shared.dto.profile.OrganizationDtor;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class DocumentDto extends AccountChildDto {

	private String code;

	private String description;

	private Date issueDate;

	private AppUserDtor issuedBy;

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

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public AppUserDtor getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(AppUserDtor issuedBy) {
		this.issuedBy = issuedBy;
	}

	public OrganizationDtor getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDtor organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "DocumentDto [code=" + code + ", issueDate=" + issueDate + ", organization=" + organization + "]";
	}

}
