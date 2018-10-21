/**
 * 
 */
package io.crs.hsys.client.cfg.editor.profile;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

import gwt.material.design.addext.client.ui.MaterialTextBoxAdd;
import gwt.material.design.addext.client.ui.constants.AwesomeIconType;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialRow;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.i18n.CountryConstants;
import io.crs.hsys.shared.constans.CommMode;
import io.crs.hsys.shared.dto.profile.CommunicationDto;

/**
 * @author robi
 *
 */
public class CommunicationEditor extends Composite implements Editor<CommunicationDto> {
	private static Logger logger = Logger.getLogger(CommunicationEditor.class.getName());

	interface MyStyle extends CssResource {
		String collapsed();
	}

	@UiField
	MyStyle style;

	interface Binder extends UiBinder<Widget, CommunicationEditor> {
	}

	@Ignore
	@UiField
	HTMLPanel panel;

	@Ignore
	@UiField
	MaterialRow fullPanel, detailsPanel;

	@UiField
	MaterialTextBoxAdd parameter;

	@UiField
	MaterialCheckBox primary;

	@Ignore
	@UiField
	MaterialComboBox<CommMode> modeCombo;
	TakesValueEditor<CommMode> mode;

	private int index;

	private final EventBus eventBus;

	private final CoreConstants i18nCoreCnst;

	private Boolean readOnly = true;

	@Inject
	CommunicationEditor(Binder uiBinder, EventBus eventBus, CoreMessages i18nCore, CoreConstants i18nCoreCnst,
			CountryConstants i18nCountry) {
		logger.info("CommunicationEditor()");
		initWidget(uiBinder.createAndBindUi(this));

		primary.getElement().getStyle().setMarginTop(20, Unit.PX);

		this.i18nCoreCnst = i18nCoreCnst;
		this.eventBus = eventBus;

		initLabelCombo();

		initDeleteIcon();

		eventBus.addHandler(CommunicationActionEvent.TYPE,
				new CommunicationActionEvent.CommunicationActionEventHandler() {

					@Override
					public void onCommunicationAction(CommunicationActionEvent event) {
						logger.info("CommunicationEditor().onCommu=nicationAction()->event" + event.getAction() + " / "
								+ event.getIndex());
						if ((CommunicationEditor.this.index == event.getIndex()) || (event.getIndex() == -1)) {
							logger.info("CommunicationEditor().onCommu=nicationAction()->OK");
							if (event.getAction().equals(CommunicationActionEvent.Action.OPEN))
								setReadOnly(false);

							if (event.getAction().equals(CommunicationActionEvent.Action.CLOSE)) {
								setReadOnly(true);
							}
						}
					}

				});
	}

	private void initLabelCombo() {
		Arrays.asList(CommMode.values())
				.forEach(type -> modeCombo.addItem(i18nCoreCnst.communicationModeMap().get(type.toString()), type));

		modeCombo.addValueChangeHandler(new ValueChangeHandler<List<CommMode>>() {
			@Override
			public void onValueChange(ValueChangeEvent<List<CommMode>> event) {
				List<CommMode> values = event.getValue();
				if ((values != null) && (!values.isEmpty()))
					setParamaterLabel(values.get(0));
			}
		});
		modeCombo.setTabIndex(1);
		mode = TakesValueEditor.of(new TakesValue<CommMode>() {

			@Override
			public void setValue(CommMode value) {
				modeCombo.setSingleValue(value);
				setParamaterLabel(value);
				if (readOnly)
					setParamaterIcon(value);
			}

			@Override
			public CommMode getValue() {
				return modeCombo.getSingleValue();
			}
		});
	}

	private void setParamaterLabel(CommMode mode) {
		parameter.setLabel(i18nCoreCnst.communicationModeMap().get(mode.toString()));
	}

	private void setParamaterIcon(CommMode mode) {
		if (isPhone(mode))
			parameter.setRightIconType(AwesomeIconType.PHONE);
		if (isEmail(mode))
			parameter.setRightIconType(AwesomeIconType.ENVELOPE);
		if (mode.equals(CommMode.SKYPE))
			parameter.setRightIconType(AwesomeIconType.SKYPE);
	}

	private void initDeleteIcon() {
		parameter.getRightIcon().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (readOnly) {
					if (isEmail(mode.getValue())) {
						parameter.getRightIcon().addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								openEmailClient(parameter.getValue());
							}
						});
					}
				} else
					eventBus.fireEvent(new CommunicationActionEvent(CommunicationActionEvent.Action.DELETE,
							CommunicationEditor.this.getIndex()));
			}
		});
	}

	private Boolean isPhone(CommMode mode) {
		switch (mode) {
		case MOBILE:
			return true;
		case WORK_PHONE:
			return true;
		case HOME_PHONE:
			return true;
		case OTHER_PHONE:
			return true;
		default:
			return false;
		}
	}

	private Boolean isEmail(CommMode mode) {
		switch (mode) {
		case WORK_EMAIL:
			return true;
		case HOME_EMAIL:
			return true;
		case OTHER_EMAIL:
			return true;
		default:
			return false;
		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setBackgeoundColor() {
		panel.getElement().getStyle().setBackgroundColor("#f5f5f5");
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
		parameter.setReadOnly(readOnly);
		detailsPanel.setVisible(!readOnly);

		if (!readOnly) {
			parameter.setRightIconType(AwesomeIconType.ERASER);
		}
	}

	private void openEmailClient(String address) {
		Window.open("mailto:" + address, "_blank", "");
	}
}
