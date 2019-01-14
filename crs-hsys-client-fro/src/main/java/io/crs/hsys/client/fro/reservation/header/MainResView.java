/**
 * 
 */
package io.crs.hsys.client.fro.reservation.header;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addext.client.ui.MaterialAutoCompleteAdd;
import gwt.material.design.addext.client.ui.MaterialComboBoxAdd;
import gwt.material.design.addext.client.ui.constants.AwesomeIconType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

/**
 * @author robi
 *
 */
public class MainResView extends ViewWithUiHandlers<MainResUiHandlers> implements MainResPresenter.MyView {
	private static Logger logger = Logger.getLogger(MainResView.class.getName());

	interface Binder extends UiBinder<Widget, MainResView> {
	}

	@UiField
	MaterialTextBox customer;

	@UiField
	MaterialComboBoxAdd<String> agent;

	@UiField
	MaterialAutoCompleteAdd source;

	/**
	*/
	@Inject
	MainResView(Binder binder) {
		logger.log(Level.INFO, "MainResView()");
		initWidget(binder.createAndBindUi(this));

		MaterialIcon customerIcon = customer.getIcon();

		customerIcon.addClickHandler(e -> MaterialToast.fireToast("I Love Material Design"));

		agent.setIconType(AwesomeIconType.PLUS_SQUARE);

		UserOracle oracle = new UserOracle();
		oracle.addContacts(getAllUsers());
		source.setSuggestions(oracle);

		source.addSelectionHandler(new SelectionHandler<UserOracle.Suggestion>() {

			@Override
			public void onSelection(SelectionEvent<UserOracle.Suggestion> event) {
				MaterialToast.fireToast(event.getSelectedItem().getDisplayString() + " Selected");
			}
		});

		source.addValueChangeHandler(new ValueChangeHandler<List<? extends UserOracle.Suggestion>>() {

			@Override
			public void onValueChange(ValueChangeEvent<List<? extends Suggestion>> event) {
				for (UserOracle.Suggestion user : event.getValue()) {
					MaterialToast.fireToast(user.getDisplayString());
				}
			}
		});
	}

	@Override
	public void open() {
	}

	public List<User> getAllUsers() {
		List<User> list = new ArrayList<>();
		list.add(new User("https://s3.amazonaws.com/uifaces/faces/twitter/stevedesigner/128.jpg",
				User.Position.DEVELOPER, true, "Luis Hoppe", "luis@mail.com", "123123123", "718-555-7654",
				"Makati City, Philippines", "Gwt Material"));
		list.add(new User("https://s3.amazonaws.com/uifaces/faces/twitter/yassiryahya/128.jpg", User.Position.DEVELOPER,
				true, "Irwin Mueller", "irwin@mail.com", "123123123", "718-432-7654", "Makati City, Philippines",
				"Gwt Material"));
		list.add(new User("https://s3.amazonaws.com/uifaces/faces/twitter/lebinoclard/128.jpg", User.Position.DEVELOPER,
				true, "Levin Card", "levin@mail.com", "123123123", "432-555-7654", "Makati City, Philippines",
				"Gwt Material"));
		list.add(new User("https://s3.amazonaws.com/uifaces/faces/twitter/lmjabreu/128.jpg", User.Position.DEVELOPER,
				false, "Dr. Cassie Keeling", "cassie@mail.com", "123123123", "432-555-7654", "Makati City, Philippines",
				"Gwt Material"));
		list.add(new User("https://s3.amazonaws.com/uifaces/faces/twitter/ariil/128.jpg", User.Position.DEVELOPER,
				false, "Dr. Madelynn Schamberger", "madelyn@mail.com", "123123123", "543-555-7654",
				"Makati City, Philippines", "Gwt Material"));
		list.add(new User("https://s3.amazonaws.com/uifaces/faces/twitter/devankoshal/128.jpg", User.Position.MARKETING,
				false, "Dominique Schmidt", "dom@mail.com", "123123123", "718-657-7654", "Makati City, Philippines",
				"Gwt Material"));
		list.add(new User("https://s3.amazonaws.com/uifaces/faces/twitter/karthipanraj/128.jpg", User.Position.CTO,
				false, "Rowland Heller", "rowland@mail.com", "123123123", "718-765-7654", "Makati City, Philippines",
				"Gwt Material"));
		list.add(new User("https://s3.amazonaws.com/uifaces/faces/twitter/GavicoInd/128.jpg", User.Position.CEO, false,
				"Quincy Schimmel", "quincy@mail.com", "123123123", "46-555-876", "Makati City, Philippines",
				"Gwt Material"));
		list.add(new User("https://s3.amazonaws.com/uifaces/faces/twitter/roybarberuk/128.jpg", User.Position.MARKETING,
				false, "Tierra VonRueden", "tierra@mail.com", "123123123", "654-56-7654", "Makati City, Philippines",
				"Gwt Material"));
		list.add(new User("https://s3.amazonaws.com/uifaces/faces/twitter/kimcool/128.jpg", User.Position.MARKETING,
				false, "Travis Larson", "travis@mail.com", "123123123", "465-456-7654", "Makati City, Philippines",
				"Gwt Material"));
		list.add(new User("https://s3.amazonaws.com/uifaces/faces/twitter/tonymillion/128.jpg", User.Position.MARKETING,
				false, "Clint Heller", "clint@mail.com", "123123123", "645-555-65", "Makati City, Philippines",
				"Gwt Material"));
		return list;
	}
}
