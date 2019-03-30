/**
 * 
 */
package io.crs.hsys.client.core.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;

import io.crs.hsys.client.core.event.DisplayMessageEvent;
import io.crs.hsys.client.core.gin.CustomActionException;
import io.crs.hsys.client.core.message.MessageData;
import io.crs.hsys.client.core.message.MessageStyle;
import io.crs.hsys.client.core.util.exceptiontranslators.ForeignTranslator;
import io.crs.hsys.client.core.util.exceptiontranslators.NotNullTranslator;
import io.crs.hsys.client.core.util.exceptiontranslators.Translator;
import io.crs.hsys.shared.dto.ErrorResponseDto;
import io.crs.hsys.shared.exception.ExceptionType;

/**
 * @author CR
 *
 */
public abstract class ErrorHandlerAsyncCallback<R> implements AsyncCallback<R> {
	private static Logger logger = Logger.getLogger(ErrorHandlerAsyncCallback.class.getName());

	private final HasHandlers hasHandlers;

	public ErrorHandlerAsyncCallback(HasHandlers hasHandlers) {
		this.hasHandlers = hasHandlers;
	}

	@Override
	public void onFailure(Throwable caught) {
		logger.log(Level.INFO, "onFailure()->caught.getMessage()=" + caught.getMessage());
		if (caught instanceof CustomActionException) {
			CustomActionException e = (CustomActionException) caught;
			ErrorResponseDto erd = e.getErDto();
			ExceptionType et = erd.getExceptionType();
			switch (erd.getExceptionType()) {
			case CANNOT_BE_DELETED:
				MessageData message = new MessageData(MessageStyle.ERROR, erd.getExceptionType().toString(),
						erd.getExceptionSubType().toString());
				DisplayMessageEvent.fire(hasHandlers, message);
				break;
			default:
				break;
			}
		}
//		MessageData message = new MessageData(translateCauses(caught), MessageStyle.ERROR);
	}

	private String translateCauses(Throwable caught) {
		StringBuilder sb = new StringBuilder(translateCause(caught));

		for (Throwable t = caught.getCause(); t != null; t = t.getCause()) {
			sb = sb.append(translateCause(t)).append("<br />");
		}

		return sb.toString();
	}

	private String translateCause(Throwable caught) {
		String message = caught.getMessage();

		Translator translator = new NotNullTranslator(message);
		if (translator.isMatching()) {
			return translator.getTranslatedMessage();
		}

		translator = new ForeignTranslator(message);
		if (translator.isMatching()) {
			return translator.getTranslatedMessage();
		}

		return message;
	}
}
