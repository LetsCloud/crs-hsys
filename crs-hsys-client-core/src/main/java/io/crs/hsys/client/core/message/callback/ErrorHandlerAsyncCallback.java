/**
 * 
 */
package io.crs.hsys.client.core.message.callback;

import java.util.logging.Logger;

import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;

import io.crs.hsys.client.core.event.DisplayMessageEvent;
import io.crs.hsys.client.core.event.DisplayMessageEvent.MessageTarget;
import io.crs.hsys.client.core.gin.CustomActionException;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.message.MessageData;
import io.crs.hsys.client.core.message.MessageStyle;
import io.crs.hsys.shared.exception.ExceptionSubType;
import io.crs.hsys.shared.exception.ExceptionType;

/**
 * @author CR
 *
 */
public abstract class ErrorHandlerAsyncCallback<R> implements AsyncCallback<R> {
	private static Logger logger = Logger.getLogger(ErrorHandlerAsyncCallback.class.getName());

	private final HasHandlers hasHandlers;
	private final MessageTarget target;
	private final CoreMessages i18n;

	public ErrorHandlerAsyncCallback(HasHandlers hasHandlers, MessageTarget target, CoreMessages i18n) {
		logger.info("ErrorHandlerAsyncCallback()");
		this.hasHandlers = hasHandlers;
		this.target = target;
		this.i18n = i18n;
	}

	@Override
	public void onFailure(Throwable caught) {
		if (caught instanceof CustomActionException) {
			CustomActionException exception = (CustomActionException) caught;
			MessageData message = new MessageData(MessageStyle.ERROR,
					translateTitle(exception.getErDto().getExceptionType()), translateDescription(
							exception.getErDto().getExceptionSubType(), exception.getErDto().getProperty()));
			logger.info("ErrorHandlerAsyncCallback().onFailure()->target=" + target);
			DisplayMessageEvent.fire(hasHandlers, target, message);
		}
	}

	private String translateTitle(ExceptionType exception) {
		switch (exception) {
		case CRUD_CANNOT_BE_SAVED:
			return i18n.CRUD_CANNOT_BE_SAVED();
		case CRUD_CANNOT_BE_DELETED:
			return i18n.CRUD_CANNOT_BE_DELETED();
		default:
			return exception.toString();
		}

	}

	private String translateDescription(ExceptionSubType exception, String value) {
		switch (exception) {
		case TASKGROUP_CODE_ALREADY_EXISTS:
			return i18n.TASKGROUP_CODE_ALREADY_EXISTS(value);
		case TASKGROUP_ID_USED_BY_TASKTODO:
			return i18n.TASKGROUP_ID_USED_BY_TASKTODO();
		case TASKGROUP_ID_USED_BY_TASKTYPE:
			return i18n.TASKGROUP_ID_USED_BY_TASKTYPE();
		default:
			return exception.toString();
		}
	}
}
