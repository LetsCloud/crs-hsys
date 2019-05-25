/**
 * 
 */
package io.crs.hsys.shared.exception;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
class OooRoomOverlapException extends Exception {

	private ExceptionSubType foreignKey;

	public OooRoomOverlapException(ExceptionSubType foreignKey) {
		super();
		this.foreignKey = foreignKey;
	}

	public ExceptionSubType getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(ExceptionSubType foreignKey) {
		this.foreignKey = foreignKey;
	}

	@Override
	public String toString() {
		return "ForeignKeyConflictException [foreignKey=" + foreignKey + "]";
	}
	
}
