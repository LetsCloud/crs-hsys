/**
 * 
 */
package io.crs.hsys.shared.dto.profile;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class GuestDto extends PersonDto {

	private String nationality;

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

}
