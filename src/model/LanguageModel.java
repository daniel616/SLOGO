package model;

import java.io.File;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;

/**
 * Contains all the info that is specific to each running SLogo application. All
 * data structures are observable. Returns read only structures.
 * 
 * Needs to throw exceptions for unfound names.
 * 
 * @author Daniel
 *
 */
public class LanguageModel {
	private ReadOnlyStringWrapper language;
	private final String DEFAULT_LANG = "English";
	private final String RESOURCE_PACKAGE = "resources" + File.separator + "languages" + File.separator;

	public LanguageModel(){
		instantiate();
	}

	public ObservableValue<String> readOnlyLanguage() {
		return language.getReadOnlyProperty();
	}
	
	public ResourceBundle makeResourceBundle(){
		return ResourceBundle.getBundle(RESOURCE_PACKAGE+language.get());
	}

	public void setLanguage(String s) {
		language.set(s);
	}

	private void instantiate() {
		language = new ReadOnlyStringWrapper(DEFAULT_LANG);
	}
}