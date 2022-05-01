package Database;

public class InsertQueryBuilder extends AbstractQueryBuilder {

  public static InsertQueryBuilder create(String tableName) {
    return new InsertQueryBuilder(tableName);
  }

  private InsertQueryBuilder(String tableName) {
    super.tableName = tableName;
  }
  

  @Override
  public String getQuery() {
    String fields = "", values = "";
    String lastElement = (String) this.queryValues.get(this.queryValues.size() - 1)[0];

    for (Object[] e : this.queryValues) {
      boolean isLastElement = e[0] == lastElement;
      fields += e[0];
      values += "?";
      if (!isLastElement) {
        fields += ", ";
        values += ", ";
      }
    }

    return "INSERT INTO " + super.tableName
        + " (" + fields + ") VALUES ("
        + values + ")";
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
