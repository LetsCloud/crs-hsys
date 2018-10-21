/**
 * 
 */
package io.crs.hsys.client.cfg.editor.profile;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
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

import gwt.material.design.client.ui.MaterialRow;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.i18n.CountryConstants;
import io.crs.hsys.shared.constans.WebPresenceType;
import io.crs.hsys.shared.dto.profile.WebPresenceDto;

/**
 * @author robi
 *
 */
public class WebPresenceEditor extends Composite implements Editor<WebPresenceDto> {
	private static Logger logger = Logger.getLogger(WebPresenceEditor.class.getName());

	interface Binder extends UiBinder<Widget, WebPresenceEditor> {
	}

	@Ignore
	@UiField
	HTMLPanel panel;

	@Ignore
	@UiField
	MaterialRow displayPanel, detailsPanel;

	@UiField
	MaterialTextBoxAdd url;

	@Ignore
	@UiField
	MaterialComboBox<WebPresenceType> labelCombo;
	TakesValueEditor<WebPresenceType> label;

	private int index;

	private final EventBus eventBus;

	private final CoreConstants i18nCoreCnst;

	private Boolean readOnly = true;

	@Inject
	WebPresenceEditor(Binder uiBinder, EventBus eventBus, CoreMessages i18nCore, CoreConstants i18nCoreCnst,
			CountryConstants i18nCountry) {
		logger.info("WebPresenceEditor()");
		initWidget(uiBinder.createAndBindUi(this));

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
						if ((WebPresenceEditor.this.index == event.getIndex()) || (event.getIndex() == -1)) {
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
		Arrays.asList(WebPresenceType.values())
				.forEach(type -> labelCombo.addItem(i18nCoreCnst.webPresenceTypeMap().get(type.toString()), type));

		labelCombo.addValueChangeHandler(new ValueChangeHandler<List<WebPresenceType>>() {
			@Override
			public void onValueChange(ValueChangeEvent<List<WebPresenceType>> event) {
				List<WebPresenceType> values = event.getValue();
				if ((values != null) && (!values.isEmpty()))
					setUrlLabel(values.get(0));
			}
		});
		labelCombo.setTabIndex(1);
		label = TakesValueEditor.of(new TakesValue<WebPresenceType>() {

			@Override
			public void setValue(WebPresenceType value) {
				labelCombo.setSingleValue(value);
				setUrlLabel(value);
				if (readOnly)
					setParamaterIcon(value);
			}

			@Override
			public WebPresenceType getValue() {
				return labelCombo.getSingleValue();
			}
		});
	}

	private void setUrlLabel(WebPresenceType type) {
		url.setLabel(i18nCoreCnst.webPresenceTypeMap().get(type.toString()));
	}

	private void setParamaterIcon(WebPresenceType type) {
		if (type.equals(WebPresenceType.WEB_URL))
			url.setRightIconType(AwesomeIconType.GLOBE);
		if (type.equals(WebPresenceType.BLOG))
			url.setRightIconType(AwesomeIconType.BLOGGER);
		if (type.equals(WebPresenceType.FACEBOOK))
			url.setRightIconType(AwesomeIconType.FACEBOOK);
		if (type.equals(WebPresenceType.FLICKR))
			url.setRightIconType(AwesomeIconType.FLICKR);
		if (type.equals(WebPresenceType.INSTAGRAM))
			url.setRightIconType(AwesomeIconType.INSTAGRAM);
		if (type.equals(WebPresenceType.LINKEDIN))
			url.setRightIconType(AwesomeIconType.LINKEDIN);
		if (type.equals(WebPresenceType.PINTEREST))
			url.setRightIconType(AwesomeIconType.PINTEREST);
		if (type.equals(WebPresenceType.REDDIT))
			url.setRightIconType(AwesomeIconType.REDDIT);
		if (type.equals(WebPresenceType.TRIPADVISOR))
			url.setRightIconType(AwesomeIconType.TRIPADVISOR);
		if (type.equals(WebPresenceType.TWITTER))
			url.setRightIconType(AwesomeIconType.TWITTER);
		if (type.equals(WebPresenceType.YOUTUBE))
			url.setRightIconType(AwesomeIconType.YOUTUBE);
	}

	private void initDeleteIcon() {
		url.getRightIcon().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (readOnly) {
					url.getRightIcon().addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							openUrl(url.getValue());
						}
					});
				} else
					eventBus.fireEvent(new CommunicationActionEvent(CommunicationActionEvent.Action.DELETE,
							WebPresenceEditor.this.getIndex()));
			}
		});
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
		url.setReadOnly(readOnly);
		detailsPanel.setVisible(!readOnly);

		if (!readOnly) {
			url.setRightIconType(AwesomeIconType.ERASER);
		}
	}

	private void openUrl(String url) {
		Window.open(url, "_blank", "");
	}
}
