/**
 * 
 */
package io.crs.hsys.server.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.shared.constans.GlobalParam;

/**
 * @author robi
 *
 */
@Entity
public class GlobalConfig extends BaseEntity {
	private static final Logger logger = LoggerFactory.getLogger(GlobalConfig.class.getName());

	/**
	 * Key
	 */
	@Index
	private GlobalParam key;

	/**
	 * Value
	 */
	private String value;

	/**
	 * Objectify miatt
	 */
	public GlobalConfig() {
		logger.debug("GlobalConfig()");
	}

	public GlobalConfig(GlobalParam key) {
		this();
		this.key = key;
	}

	public GlobalParam getKey() {
		return key;
	}

	public void setKey(GlobalParam key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
