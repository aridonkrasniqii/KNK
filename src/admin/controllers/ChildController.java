
package admin.controllers;

import javafx.fxml.Initializable;

public abstract class ChildController implements Initializable {
	@SuppressWarnings("unused")
	private MainController parentController;

	public void setParentController(MainController parentController) {
		this.parentController = parentController;
	}

}
