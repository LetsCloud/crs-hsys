/**
 * 
 */
package io.crs.hsys.client.kip.editor.translate;

import java.util.Arrays;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialTextBox;
import io.crs.hsys.client.core.editor.room.DeleteEvent.DeleteEventHandler;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.shared.constans.Language;
import io.crs.hsys.shared.dto.common.TranslationDto;

/**
 * @author robi
 *
 */
public class TranslateEditor extends Composite implements Editor<TranslationDto> {
	private static Logger logger = Logger.getLogger(TranslateEditor.class.getName());

	interface Binder extends UiBinder<Widget, TranslateEditor> {
	}

	@Ignore
	@UiField
	MaterialComboBox<Language> languageComboBox;
	TakesValueEditor<Language> language;

	@UiField
	MaterialTextBox text;

	private final CoreConstants coreCnst;

	/**
	 * 
	 */
	@Inject
	TranslateEditor(Binder uiBinder, CoreConstants coreCnst) {
		logger.info("TranslateEditor()");
		initWidget(uiBinder.createAndBindUi(this));
		this.coreCnst = coreCnst;
		initLanguageCombo();
	}

	private void initLanguageCombo() {
		languageComboBox.clear();
		Arrays.asList(Language.values())
				.forEach(lng -> languageComboBox.addItem(coreCnst.languageMap().get(lng.toString()), lng));

		language = TakesValueEditor.of(new TakesValue<Language>() {

			@Override
			public void setValue(Language value) {
				languageComboBox.setSingleValue(value);
			}

			@Override
			public Language getValue() {
				return languageComboBox.getSingleValue();
			}
		});
	}

	public void addDeleteHandler(DeleteEventHandler deleteEventHandler) {
		// TODO Auto-generated method stub

	}

}
