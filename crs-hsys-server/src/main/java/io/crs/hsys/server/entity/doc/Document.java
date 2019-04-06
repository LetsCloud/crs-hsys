/**
 * 
 */
package io.crs.hsys.server.entity.doc;

import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;

import io.crs.hsys.server.entity.common.AccountChild;
import io.crs.hsys.server.entity.profile.Organization;

/**
 * @author robi
 *
 */
@Entity
public class Document extends AccountChild {
	public static final String PROPERTY_CODE = "code";

	private String code;

	private Date postingDate;

	private Ref<Organization> organization;

	public Document() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
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
		return "Document [code=" + code + ", postingDate=" + postingDate + ", organization=" + getOrganization() + "]"
				+ super.toString();
	}

}
