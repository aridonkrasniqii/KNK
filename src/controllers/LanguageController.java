package controllers;

import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import utilities.SessionManager;

public abstract class LanguageController implements Initializable {

	protected ResourceBundle getLangBundle() {
		return ResourceBundle.getBundle("resources.messages", SessionManager.getLocale());
	}

	public abstract void loadLangTexts(ResourceBundle langBundle);
}
