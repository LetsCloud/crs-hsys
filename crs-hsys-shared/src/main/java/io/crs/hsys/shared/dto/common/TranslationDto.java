/**
 * 
 */
package io.crs.hsys.shared.dto.common;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class TranslationDto extends AccountChildDto {
	private String language;
	private String text;

	public TranslationDto(Builder<?> builder) {
		super(builder);
		language = builder.language;
		text = builder.text;
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

	public static abstract class Builder<T extends Builder<T>> extends AccountChildDto.Builder<T> {
		private String language;
		private String text;

		public T language(String language) {
			this.language = language;
			return self();
		}

		public T text(String text) {
			this.text = text;
			return self();
		}

		public TranslationDto build() {
			return new TranslationDto(this);
		}
	}

	/**
	 * 
	 * @author robi
	 *
	 */
	protected static class Builder2 extends Builder<Builder2> {
		@Override
		protected Builder2 self() {
			return this;
		}
	}

	public static Builder<?> builder() {
		return new Builder2();
	}

}
