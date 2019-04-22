/**
 * 
 */
package io.crs.hsys.client.cfg.editor.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import gwt.material.design.addext.client.ui.MaterialAutoCompleteAdd;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialRow;

import io.crs.hsys.client.cfg.editor.profile.AddressActionEvent.AddressActiEventHandler;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.datasource.RelationshipDataSource;
import io.crs.hsys.client.core.message.callback.AbstractAsyncCallback;
import io.crs.hsys.shared.api.ProfileResource;
import io.crs.hsys.shared.dto.profile.ProfileDtor;
import io.crs.hsys.shared.dto.profile.ProfileLinkDto;
import io.crs.hsys.shared.dto.profile.RelationshipDtor;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

/**
 * @author robi
 *
 */
public class ProfileLinkEditor extends Composite implements Editor<ProfileLinkDto> {
	private static Logger logger = Logger.getLogger(ProfileLinkEditor.class.getName());

	interface MyStyle extends CssResource {
		String collapsed();
	}

	@UiField
	MyStyle style;

	interface Binder extends UiBinder<Widget, ProfileLinkEditor> {
	}

	@Ignore
	@UiField
	HTMLPanel panel;

	@Ignore
	@UiField
	MaterialRow displayPanel, editorPanel;

	@Ignore
	@UiField
	MaterialLink profileLink;

	@Ignore
	@UiField
	MaterialAutoCompleteAdd profileAutoComplete;
	TakesValueEditor<ProfileDtor> profile;

	@Ignore
	@UiField
	MaterialComboBox<RelationshipDtor> relationshipCombo;
	TakesValueEditor<RelationshipDtor> relationship;

	@UiField
	MaterialCheckBox switched;

	@Ignore
	@UiField
	MaterialIcon deleteIcon;

	private int index;
	private final EventBus eventBus;
	private final PlaceManager placeManager;
	private final RelationshipDataSource relationshipDataSource;
	private final ResourceDelegate<ProfileResource> profileResource;

	@Inject
	ProfileLinkEditor(Binder uiBinder, EventBus eventBus, PlaceManager placeManager,
			ResourceDelegate<ProfileResource> profileResource, RelationshipDataSource relationshipDataSource) {

		logger.info("ProfileLinkEditor()");
		initWidget(uiBinder.createAndBindUi(this));

		switched.getElement().getStyle().setMarginTop(20, Unit.PX);

		this.eventBus = eventBus;
		this.placeManager = placeManager;
		this.relationshipDataSource = relationshipDataSource;
		this.profileResource = profileResource;

		initProfileSearch();
		initRelationshipCombo();
		initDeleteIcon();

		eventBus.addHandler(AddressActionEvent.TYPE, new AddressActiEventHandler() {

			@Override
			public void onAddressAction(AddressActionEvent event) {
				if (event.getAction().equals(AddressActionEvent.Action.OPEN))
					openDetails(ProfileLinkEditor.this.index == event.getIndex());

				if (event.getAction().equals(AddressActionEvent.Action.CLOSE)) {
					if (ProfileLinkEditor.this.index == event.getIndex())
						openDetails(false);
				}
			}

		});
	}

	private void initProfileSearch() {

		profileResource.withCallback(new AbstractAsyncCallback<List<ProfileDtor>>() {
			@Override
			public void onSuccess(List<ProfileDtor> result) {
				logger.info("initProfileSearch().onSuccess()");
				for (ProfileDtor profile : result) {
					logger.info("initProfileSearch().onSuccess()-> " + profile.getName() + "/" + profile.getId() + "/"
							+ profile.getAccount().getId());
				}
				ProfileOracle profileOracle = new ProfileOracle();
				profileOracle.addProfiles(result);
				profileAutoComplete.setSuggestions(profileOracle);
			}
		}).getAll();

		profileAutoComplete.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
			@Override
			public void onSelection(SelectionEvent<SuggestOracle.Suggestion> event) {
//				ProfileSuggestion ps = (ProfileSuggestion) profileSearch.getValue();
			}
		});

		profile = TakesValueEditor.of(new TakesValue<ProfileDtor>() {

			@Override
			public void setValue(ProfileDtor value) {
				logger.info("profile.setValue()->value=" + value);
				if (value != null) {
					List<ProfileSuggestion> values = new ArrayList<ProfileSuggestion>();
					values.add(new ProfileSuggestion(value));

					Timer t = new Timer() {
						@Override
						public void run() {
							profileAutoComplete.setValue(values);
							setProfileText(value);
						}
					};
					t.schedule(50);
				}
			}

			@Override
			public ProfileDtor getValue() {
				logger.info("profile.getValue()");
				return getSelectedProfile();
			}
		});
	}

	private void setProfileText(ProfileDtor p) {
		logger.info("setProfileText->p=" + p);
		RelationshipDtor r = relationship.getValue();
		if (p != null) {
			profileLink.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					logger.info("setProfileText->p.getWebSafeKey()=" + p.getWebSafeKey());
					PlaceRequest placeRequest = new PlaceRequest.Builder()
							.nameToken(CoreNameTokens.ORGANIZATION_DISPLAY)
							.with(WEBSAFEKEY, String.valueOf(p.getWebSafeKey())).build();
					placeManager.revealPlace(placeRequest);
				}
			});
			profileLink.setText(p.getName());

			if (r != null) {
				if (switched.getValue())
					profileLink.setText(profileLink.getText() + ", " + r.getReverse());
				else
					profileLink.setText(profileLink.getText() + ", " + r.getForward());
			}
		}

	}

	private ProfileDtor getSelectedProfile() {
		logger.info("getSelectedProfile()");
		List<? extends SuggestOracle.Suggestion> values = profileAutoComplete.getValue();
		for (SuggestOracle.Suggestion value : values) {
			logger.info("getSelectedProfile()->value=" + value);
			if (value instanceof ProfileSuggestion) {
				ProfileSuggestion profileSuggestion = (ProfileSuggestion) value;
				ProfileDtor profile = profileSuggestion.getProfile();
				logger.info("getSelectedProfile()->profile=" + profile);
				return profile;
			}
		}
		return null;
	}

	private void initRelationshipCombo() {
		LoadCallback<RelationshipDtor> loadCallback = new LoadCallback<RelationshipDtor>() {
			@Override
			public void onSuccess(LoadResult<RelationshipDtor> loadResult) {
				loadResult.getData().forEach(
						object -> relationshipCombo.addItem(object.getForward() + "/" + object.getReverse(), object));
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		relationshipDataSource.load(new LoadConfig<RelationshipDtor>(0, 0, null, null), loadCallback);

		relationship = TakesValueEditor.of(new TakesValue<RelationshipDtor>() {

			@Override
			public void setValue(RelationshipDtor value) {
				logger.info("relationship.setValue()->value=" + value);
				if (value != null) {
					relationshipCombo.setSingleValue(value);
					if (switched.getValue())
						setRelationText(value.getReverse());
					else
						setRelationText(value.getForward());
				}
			}

			@Override
			public RelationshipDtor getValue() {
				logger.info("relationship.getValue()");
				RelationshipDtor value = relationshipCombo.getSingleValue();
				logger.info("relationship.getValue()->value=" + value);
				return value;
			}
		});

	}

	private void setRelationText(String relation) {
		ProfileDtor p = profile.getValue();
		if (p != null)
			relation = p.getName() + " " + relation;

		profileLink.setText(relation);
	}

	private void initDeleteIcon() {
		deleteIcon.getElement().getStyle().setMarginTop(20, Unit.PX);
		deleteIcon.getElement().getStyle().setPadding(0, Unit.PX);

		deleteIcon.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.info("AddressEditor()->deleteIcon.onClick()");
				eventBus.fireEvent(
						new AddressActionEvent(AddressActionEvent.Action.DELETE, ProfileLinkEditor.this.getIndex()));
			}
		});
	}

	public void openDetails(Boolean open) {

		if (open) {
			displayPanel.getElement().addClassName(style.collapsed());

			Timer t = new Timer() {
				@Override
				public void run() {
					editorPanel.getElement().removeClassName(style.collapsed());
				}
			};
			t.schedule(1000);
		} else {
			editorPanel.getElement().addClassName(style.collapsed());

			Timer t = new Timer() {
				@Override
				public void run() {
					displayPanel.getElement().removeClassName(style.collapsed());
				}
			};
			t.schedule(1000);
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
		displayPanel.setVisible(readOnly);
		editorPanel.setVisible(!readOnly);
	}
}
