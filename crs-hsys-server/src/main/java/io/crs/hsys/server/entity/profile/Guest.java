/**
 * 
 */
package io.crs.hsys.server.entity.profile;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Subclass;

/**
 * @author CR
 *
 */
@Subclass(index = true)
public class Guest extends Person {

	private String nationality;

	/**
	 * Szoba hivatkozás.
	 */
	private Ref<VipLevel> vipLevelRef;

	public Guest() {}
	
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Ref<VipLevel> getVipLevelRef() {
		return vipLevelRef;
	}

	public void setVipLevelRef(Ref<VipLevel> vipLevelRef) {
		this.vipLevelRef = vipLevelRef;
	}

	/**
	 * 
	 * @author CR
	 *
	 */
	public static class Builder extends Person.Builder {

		private String nationality;

		public Builder() {
		}

		public Builder nationality(String nationality) {
			this.nationality = nationality;
			return this;
		}

		public Guest build() {
			return new Guest(this);
		}
	}

	protected Guest(Builder builder) {
		super(builder);
		this.nationality = builder.nationality;
	}

}