/**
 * 
 */
package io.crs.hsys.shared.dto.common;

import io.crs.hsys.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class TranslationDto implements Dto {
	private String language;
	private String text;

	public TranslationDto() {
	}

	public TranslationDto(String language, String text) {
		this.language = language;
		this.text = text;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
