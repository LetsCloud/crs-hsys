/**
 * 
 */
package io.crs.hsys.shared.exception;

import io.crs.hsys.shared.cnst.ForeignKey;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class ForeignKeyConflictException extends Exception {

	private ForeignKey foreignKey;

	public ForeignKeyConflictException(ForeignKey foreignKey) {
		super();
		this.foreignKey = foreignKey;
	}

	public ForeignKey getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(ForeignKey foreignKey) {
		this.foreignKey = foreignKey;
	}
	
}
