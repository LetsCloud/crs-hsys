/**
 * 
 */
package io.crs.hsys.shared.exception;

import io.crs.hsys.shared.dto.ErrorResponseDto;
import io.crs.hsys.shared.exception.cnst.ErrorMessageCode;
import io.crs.hsys.shared.exception.cnst.ErrorTitleCode;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class ForeignKeyConflictException extends BaseException {

	private ErrorMessageCode foreignKey;

	public ForeignKeyConflictException(ErrorMessageCode foreignKey) {
		super();
		this.foreignKey = foreignKey;
	}

	public ErrorMessageCode getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(ErrorMessageCode foreignKey) {
		this.foreignKey = foreignKey;
	}

	@Override
	public String toString() {
		return "ForeignKeyConflictException [foreignKey=" + foreignKey + "]";
	}

	@Override
	public ErrorResponseDto getErrorResponse() {
		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage(ErrorTitleCode.UNKNOWN.toString() + "-" + getMessage());
		error.setTitleCode(ErrorTitleCode.CRUD_CANNOT_BE_DELETED);
		error.setMessageCode(getForeignKey());
		error.setProperty(getForeignKey().toString());
		error.setMessage(ErrorTitleCode.CRUD_CANNOT_BE_DELETED.toString() + "-" + getMessage());
		return error;
	}

}
