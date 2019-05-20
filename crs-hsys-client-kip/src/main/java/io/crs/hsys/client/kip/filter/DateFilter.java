/**
 * 
 */
package io.crs.hsys.client.kip.filter;

import java.util.Date;

import javax.inject.Inject;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.ui.MaterialDatePicker;

import io.crs.hsys.client.core.filter.BaseFilter;

/**
 * @author robi
 *
 */
public class DateFilter extends BaseFilter {

	MaterialDatePicker datePicker = new MaterialDatePicker();

	@Inject
	DateFilter() {
		initWidget(datePicker);
		initDatePicker();
	}

	@Override
	protected MaterialWidget getFilterWidget() {
		return datePicker;
	}

	public Date getValue() {
		return datePicker.getValue();
	}

	public void setValue(Date value) {
		datePicker.setValue(value);
	}

	public void setFilterLabel(String label) {
		datePicker.setPlaceholder(label);
	}

	public void addValueChangeHandler(final ValueChangeHandler<Date> handler) {
		datePicker.addValueChangeHandler(handler);
	}

	private void initDatePicker() {
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				if (event.getValue() == null)
					setChipText(null);
				else
					setChipText(chipLabel);
			}
		});
	}

	public void setFilterHeight(double value, Unit unit) {
		datePicker.getDateInput().getElement().getStyle().setHeight(value, unit);
	}
}
