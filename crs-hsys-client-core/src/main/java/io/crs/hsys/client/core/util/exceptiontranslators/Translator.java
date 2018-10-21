/**
 * 
 */
package io.crs.hsys.client.core.util.exceptiontranslators;

/**
 * @author CR
 *
 */
public interface Translator {
	Boolean isMatching();

	String getTranslatedMessage();
}