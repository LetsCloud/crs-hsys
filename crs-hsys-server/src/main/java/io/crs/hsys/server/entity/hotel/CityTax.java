/**
 * 
 */
package io.crs.hsys.server.entity.hotel;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import io.crs.hsys.shared.cnst.CityTaxBase;

/**
 * IFA mérték.
 * 
 * @author robi
 *
 */
public class CityTax {

	/**
	 * Mettől érvényes.
	 */
	private Date validFrom;

	/**
	 * Számítás alapja.
	 */
	private CityTaxBase based;

	/**
	 * Szolgáltatgáskódja.
	 */
//	private Ref<Service> serviceRef;

	/**
	 * Mértéke, a számítás alapjától függően százalék vagy fix összeg.
	 */
	private Float value;

	/**
	 * Objectify miatt
	 */
	public CityTax() {
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public CityTaxBase getBased() {
		return based;
	}

	public void setBased(CityTaxBase type) {
		this.based = type;
	}
/*
	public Ref<Service> getServiceRef() {
		return serviceRef;
	}

	public void setServiceRef(Ref<Service> serviceRef) {
		this.serviceRef = serviceRef;
	}

	public Service getService() {
		return serviceRef.get();
	}

	public void setService(Service service) {
		this.serviceRef = Ref.create(service);
	}
*/
	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	/**
	 * Az IFA mértékek érvényeségi dátum szerint rendezését szolgáló Comparator
	 */
	public static Comparator<CityTax> ORDER_BY_VALIDFROM = new Comparator<CityTax>() {
		public int compare(CityTax one, CityTax other) {
			return one.getValidFrom().compareTo(other.getValidFrom());
		}
	};

	/**
	 * A megadott dátum alkalmaával érvényes IFA mértéket adja vissza.
	 * 
	 * @param cityTaxes Az IFA mértékek listája amelyből választ az eljárás.
	 * @param date      A megadott dátum.
	 * @return Az érvényes CityTax objektum.
	 */
	public static CityTax getValidCityTax(List<CityTax> cityTaxes, final Date date) {
		return cityTaxes.stream().filter(cityTax -> cityTax.getValidFrom().compareTo(date) >= 0).sorted().findFirst()
				.orElse(null);
	}

}
