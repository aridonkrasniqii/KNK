package controllers;

import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import utils.SessionManager;

public abstract class LanguageController implements Initializable {

	protected ResourceBundle getLangBundle() {
		return ResourceBundle.getBundle("sample.Languages.LangBundle", SessionManager.getLocale());
	}

	public abstract void loadLangTexts(ResourceBundle langBundle);
}
