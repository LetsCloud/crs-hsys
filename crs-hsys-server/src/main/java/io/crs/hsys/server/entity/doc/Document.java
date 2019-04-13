/**
 * 
 */
package io.crs.hsys.server.entity.doc;

import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.server.entity.common.AccountChild;
import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.entity.profile.Organization;

/**
 * @author robi
 *
 */
@Entity
public class Document extends AccountChild {
	public static final String PROPERTY_CODE = "code";

	@Index
	private String code;

	private String description;

	private Date issueDate;

	@Index
	private Ref<AppUser> issuedBy;

	@Index
	private Ref<Organization> organization;

	public Document() {
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

	public AppUser getIssuedBy() {
		if (issuedBy == null)
			return null;
		return issuedBy.get();
	}

	public void setIssuedBy(AppUser issuedBy) {
		if (issuedBy.getId() != null)
			this.issuedBy = Ref.create(issuedBy);
	}

	public Organization getOrganization() {
		if (organization == null)
			return null;
		return organization.get();
	}

	public void setOrganization(Organization organization) {
		if (organization.getId() != null)
			this.organization = Ref.create(organization);
	}

	@Override
	public String toString() {
		return "Document [code=" + code + ", issueDate=" + issueDate + ", organization=" + getOrganization() + "]"
				+ super.toString();
	}

}
