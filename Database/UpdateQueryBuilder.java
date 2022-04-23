package Database;

public class UpdateQueryBuilder extends AbstractQueryBuilder {

  public static UpdateQueryBuilder create(String tableName) {
    return new UpdateQueryBuilder(tableName);
  }

  private UpdateQueryBuilder(String tableName) {
    super.tableName = tableName;
  }

  @Override
  public String getQuery() {
    String lastElement = (String) this.queryValues.get(this.queryValues.size() - 1)[0];
    String updateQuery = "UPDATE TABLE " + super.tableName + " SET";

    for (Object[] e : this.queryValues) {
      boolean isLastElement = e[0] == lastElement;
      updateQuery += " " + e[0] + " = ?";

      if (!isLastElement) {
        updateQuery += ", ";
      }
    }

    updateQuery += " WHERE ";
    lastElement = (String) this.whereValues.get(this.whereValues.size() - 1)[0];
    for (Object[] e : this.whereValues) {
      boolean isLastElement = e[0] == lastElement;
      updateQuery += e[0] + " = ?";

      if (!isLastElement) {
        updateQuery += "AND ";
      }
    }

    return updateQuery;
  }

  public String getTypes() {
    return this.queryTypes;
  }

  public Object[] getValues() {
    Object[] values = new Object[this.queryValues.size()];
    for (int i = 0; i < values.length; i++) {
      values[i] = this.queryValues.get(i)[1];
    }
    return values;
  }

}
