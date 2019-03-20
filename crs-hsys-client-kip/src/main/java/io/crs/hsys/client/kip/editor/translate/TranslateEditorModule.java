/**
 * 
 */
package io.crs.hsys.client.kip.editor.translate;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class TranslateEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bind(TranslateListEditor.class);
		bind(TranslateEditor.class);
	}
}
