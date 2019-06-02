/**
 * 
 */
package io.crs.hsys.client.fro.calendar;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialPanel;

/**
 * @author robi
 *
 */
public class CalendarView  extends ViewWithUiHandlers<CalendarUiHandlers> implements CalendarPresenter.MyView {
	private static Logger logger = Logger.getLogger(CalendarView.class.getName());

	MaterialPanel content = new MaterialPanel();
	
	@Inject
	CalendarView() {
		logger.log(Level.INFO, "CalendarView");
		initWidget(content);

//		    content.add(new GoogleCalendarPanel());
	}
}
