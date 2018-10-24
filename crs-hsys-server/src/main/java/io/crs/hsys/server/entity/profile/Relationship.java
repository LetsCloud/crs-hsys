/**
 * 
 */
package io.crs.hsys.server.entity.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;

import io.crs.hsys.server.entity.common.AccountChild;

/**
 * @author robi
 *
 */
@Entity
public class Relationship extends AccountChild {
	private static final Logger logger = LoggerFactory.getLogger(Relationship.class);

	private String forward;

	private String reverse;

	/**
	 * Aktív, Inaktív
	 */
	private Boolean active;

	public Relationship() {
		logger.info("Relationship()");
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public String getReverse() {
		return reverse;
	}

	public void setReverse(String reverse) {
		this.reverse = reverse;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	/*
	 * toString
	 */
	@Override
	public String toString() {
		String ret = "Relationship:{" + "forward=" + forward + ", reverse=" + reverse + ", active=" + active + ", "
				+ super.toString() + "}";
		return ret;
	}

}
