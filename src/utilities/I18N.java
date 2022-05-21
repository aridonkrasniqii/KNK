package utilities;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * I18N utility class..
 */
public final class I18N {

	private static final ObjectProperty<Locale> locale;

	static {
		locale = new SimpleObjectProperty<>(getDefaultLocale());
		locale.addListener((observable, oldValue, newValue) -> Locale.setDefault(newValue));
	}

	public static List<Locale> getSupportedLocales() {
		return new ArrayList<>(Arrays.asList(Locale.ENGLISH, Locale.GERMAN));
	}

	public static Locale getDefaultLocale() {
		Locale sysDefault = Locale.getDefault();
		return getSupportedLocales().contains(sysDefault) ? sysDefault : Locale.ENGLISH;
	}

	public static Locale getLocale() {
		return locale.get();
	}

	public static void setLocale(Locale locale) {
		localeProperty().set(locale);
		Locale.setDefault(locale);
	}

	public static ObjectProperty<Locale> localeProperty() {
		return locale;
	}

	public static String get(final String key, final Object... args) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("resources.messages", getLocale());
			return MessageFormat.format(bundle.getString(key), args);
		} catch (java.util.MissingResourceException e) {
			System.out.println("Could not load Resources");
			return null;
		}

	}

	public static StringBinding createStringBinding(final String key, Object... args) {
		return Bindings.createStringBinding(() -> get(key, args), locale);
	}

	public static StringBinding createStringBinding(Callable<String> func) {
		return Bindings.createStringBinding(func, locale);
	}

	public static Label labelForValue(Callable<String> func) {
		Label label = new Label();
		label.textProperty().bind(createStringBinding(func));
		return label;
	}

	public static Button buttonForKey(final String key, final Object... args) {
		Button button = new Button();
		button.textProperty().bind(createStringBinding(key, args));
		return button;
	}
}