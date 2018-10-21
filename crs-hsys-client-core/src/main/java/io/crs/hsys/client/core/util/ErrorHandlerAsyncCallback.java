/**
 * 
 */
package io.crs.hsys.client.core.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;

import io.crs.hsys.client.core.event.DisplayMessageEvent;
import io.crs.hsys.client.core.ui.message.Message;
import io.crs.hsys.client.core.ui.message.MessageStyle;
import io.crs.hsys.client.core.util.exceptiontranslators.ForeignTranslator;
import io.crs.hsys.client.core.util.exceptiontranslators.NotNullTranslator;
import io.crs.hsys.client.core.util.exceptiontranslators.Translator;

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
		logger.log(Level.INFO,
				"onFailure()->caught.getMessage()=" + caught.getMessage());
		Message message = new Message(translateCauses(caught), MessageStyle.ERROR);
		DisplayMessageEvent.fire(hasHandlers, message);
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
