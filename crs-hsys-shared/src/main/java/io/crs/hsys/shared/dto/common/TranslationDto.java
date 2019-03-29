/**
 * 
 */
package io.crs.hsys.shared.dto.common;

import io.crs.hsys.shared.cnst.Language;
import io.crs.hsys.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class TranslationDto implements Dto {
	private Language language;
	private String text;

	public TranslationDto() {
	}

	public TranslationDto(Language language, String text) {
		this.language = language;
		this.text = text;
	}

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

	@Override
	public String toString() {
		return "TranslationDto [language=" + language + ", text=" + text + "]";
	}
	
}
