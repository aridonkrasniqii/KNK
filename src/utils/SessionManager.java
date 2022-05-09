package utils;

import java.util.Locale;

import helpers.LanguageEnum;

public class SessionManager {
	private static LanguageEnum lang;

	public static Locale getLocale() {
		Locale locale = lang == LanguageEnum.AL ? new Locale("al", "AL") : new Locale("en", "EN");
		return locale;
	}

	public static void setLang(LanguageEnum lang) {
		SessionManager.lang = lang;
	}
}
