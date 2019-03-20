/**
 * 
 */
package io.crs.hsys.server.entity.task;

import io.crs.hsys.shared.constans.Language;

/**
 * @author robi
 *
 */
public class Translation {
	private Language language;
	private String text;

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
