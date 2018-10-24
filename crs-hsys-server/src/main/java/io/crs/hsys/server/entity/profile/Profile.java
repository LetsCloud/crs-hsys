/**
 * 
 */
package io.crs.hsys.server.entity.profile;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;

import io.crs.hsys.server.entity.common.AccountChild;

/**
 * A Profil entitás a Customer, Contact és Guest entitások őse.
 * 
 * @author CR
 *
 */
@Entity
public class Profile extends AccountChild {
	private static final Logger logger = LoggerFactory.getLogger(Profile.class);

	/**
	 * A Profil neve.
	 */
	private String name;

	/**
	 * A Profil csoportja.
	 */
	private Ref<ProfileGroup> profileGroup;

	private List<Communication> communications = new ArrayList<Communication>();

	private List<Address> addresses = new ArrayList<Address>();

	private List<WebPresence> webPresences = new ArrayList<WebPresence>();

	public Profile() {
		logger.info("Profile()");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProfileGroup getProfileGroup() {
		if (profileGroup == null)
			return null;
		return profileGroup.get();
	}

	public void setProfileGroup(ProfileGroup profileGroup) {
		if (profileGroup.getId() != null)
			this.profileGroup = Ref.create(profileGroup);
	}

	public List<Communication> getCommunications() {
		return communications;
	}

	public void setCommunications(List<Communication> communications) {
		this.communications = communications;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<WebPresence> getWebPresences() {
		return webPresences;
	}

	public void setWebPresences(List<WebPresence> webPresences) {
		this.webPresences = webPresences;
	}

	@Override
	public String toString() {
		return "Profile:[name=" + name + ", profileGroup=" + getProfileGroup() + "]>>" + super.toString();
	}

	/**
	 * 
	 * @author CR
	 *
	 */
	public static class Builder extends AccountChild.Builder<Builder> {

		private String name;

		private Ref<ProfileGroup> profileGroup;

		private List<Communication> communications = new ArrayList<Communication>();

		private List<Address> addresses = new ArrayList<Address>();

		private List<WebPresence> webPresences = new ArrayList<WebPresence>();

		public Builder() {
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder profileGroup(ProfileGroup profileGroup) {
			if (profileGroup.getId() != null)
				this.profileGroup = Ref.create(profileGroup);
			return this;
		}

		public Builder communications(List<Communication> communications) {
			this.communications = communications;
			return this;
		}

		public Builder addcommunication(Communication communication) {
			this.communications.add(communication);
			return this;
		}

		public Builder addresses(List<Address> addresses) {
			this.addresses = addresses;
			return this;
		}

		public Builder addAddress(Address address) {
			this.addresses.add(address);
			return this;
		}

		public Builder webPresences(List<WebPresence> webPresences) {
			this.webPresences = webPresences;
			return this;
		}

		public Builder addWebPresence(WebPresence webPresence) {
			this.webPresences.add(webPresence);
			return this;
		}

		public Profile build() {
			return new Profile(this);
		}
	}

	protected Profile(Builder builder) {
		super(builder);
		this.setName(builder.name);
	}
}
