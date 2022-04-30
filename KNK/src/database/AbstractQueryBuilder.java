package database;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractQueryBuilder {

  protected String tableName;
  protected String queryTypes = "";

  protected List<Object[]> queryValues = new ArrayList<Object[]>();

  protected String whereTypes = "";
  protected List<Object[]> whereValues = new ArrayList<Object[]>();

  abstract public String getQuery();

  abstract public String getTypes();

  abstract public Object[] getValues();

  public AbstractQueryBuilder add(String field, Object value, String type) throws Exception {
    field = field.trim();
    if (this.fieldExists(field, this.queryValues)) {
      throw new Exception("Field already exists!");
    }

    this.queryTypes += type;
    this.queryValues.add(new Object[] { field, value });
    return this;
  }

  private boolean fieldExists(String field, List<Object[]> list) {
    for (Object[] e : list) {
      if (e[0] == field) {
        return true;
      }
    }
    return false;
  }

  public AbstractQueryBuilder addWhere(String field, Object value, String type) throws Exception {
    field = field.trim();
    if (this.fieldExists(field, this.whereValues)) {
      throw new Exception("Field already exists!");
    }

    this.whereTypes += type;
    this.whereValues.add(new Object[] { field, value });
    return this;
  }

}
