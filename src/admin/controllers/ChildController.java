
package admin.controllers;

import javafx.fxml.Initializable;

public abstract class ChildController implements Initializable {
  private MainController parentController;
  public void setParentController(MainController parentController) {
    this.parentController = parentController;
  } 

}
