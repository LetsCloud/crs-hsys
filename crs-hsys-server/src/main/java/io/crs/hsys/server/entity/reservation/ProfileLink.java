/**
 * 
 */
package io.crs.hsys.server.entity.reservation;

import java.util.List;

import com.googlecode.objectify.Ref;

import io.crs.hsys.server.entity.profile.Organization;
import io.crs.hsys.shared.constans.ProfileType;

/**
 * @author CR
 *
 */
public class ProfileLink {

	/**
	 * 
	 * @param links
	 * @param type
	 * @return
	 */
	public static Organization getCustomerByType(List<ProfileLink> links, final ProfileType type) {
		return null;
	}

	private ProfileType type;

	private Ref<Organization> customerRef;

	public ProfileType getType() {
		return type;
	}

	public void setType(ProfileType type) {
		this.type = type;
	}

	public Ref<Organization> getCustomerRef() {
		return customerRef;
	}

	public void setCustomerRef(Ref<Organization> customerRef) {
		this.customerRef = customerRef;
	}

	public Organization getCustomer() {
		return customerRef.get();
	}

	public void setCustomer(Organization customer) {
		this.customerRef = Ref.create(customer);
	}

}
