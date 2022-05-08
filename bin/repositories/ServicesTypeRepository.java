package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import helpers.Service_Type;

public class ServicesTypeRepository {

  private DBConnection connection;

  public ServicesTypeRepository() {
    this.connection = DBConnection.getConnection();
  }

  public ArrayList<Service_Type> findAll() throws Exception {
    String query = "select * from services_type";
    ResultSet res = this.connection.executeQuery(query);
    ArrayList<Service_Type> service_types = new ArrayList<Service_Type>();

    while (res.next()) {
      System.out.println("Serice_Type id : " + res.getInt("id"));
      service_types.add(Service_Type.fromResultSet(res));
    }
    return service_types;
  }

  
  // public static List<Service_Type> selectAll() throws Exception {
  //   ArrayList<Service_Type> list = new ArrayList<>();
  //   Connection conn = DBConnect.getConnection();
  //   PreparedStatement stmt = conn.prepareStatement("SELECT * FROM services_type");
  //   ResultSet res = stmt.executeQuery();
  //   while (res.next()) {
  //     list.add(parseFromRes(res));
  //   }
  //   return list;
  // }

  // public static Service_Type find(int id) throws Exception {
  //   Connection conn = DBConnect.getConnection();
  //   PreparedStatement stmt = conn.prepareStatement("SELECT * FROM services_type WHERE id = ?");
  //   stmt.setInt(1, id);
  //   ResultSet res = stmt.executeQuery();
  //   if (!res.next())
  //     return null;
  //   return parseFromRes(res);
  // }

  // public static Service_Type insert(Service_Type model) throws Exception {
  //   Connection conn = DBConnect.getConnection();

  //   PreparedStatement stmt = conn
  //       .prepareStatement("INSERT INTO services_type (service_name, price, quantity) VALUES (?, ?, ?)");
  //   stmt.setString(1, model.getService_name());
  //   stmt.setDouble(2, model.getPrice());
  //   stmt.setInt(3, model.getQuantity());
  //   stmt.executeUpdate();

  //   ResultSet res = conn.prepareStatement("SELECT id FROM services_type ORDER BY id DESC LIMIT 1").executeQuery();
  //   res.next();
  //   int id = res.getInt("id");

  //   return find(id);
  // }

  // public static Service_Type update(Service_Type model) throws Exception {
  //   Connection conn = DBConnect.getConnection();

  //   PreparedStatement stmt = conn
  //       .prepareStatement("UPDATE services_type SET service_name = ?, price = ?, quantity = ?");

  //   stmt.setString(1, model.getService_name());
  //   stmt.setDouble(2, model.getPrice());
  //   stmt.setInt(3, model.getQuantity());
  //   stmt.executeUpdate();

  //   return find(model.getId());
  // }

  // public static boolean remove(int id) throws Exception {
  //   Connection conn = DBConnect.getConnection();

  //   PreparedStatement stmt = conn.prepareStatement("DELETE FROM services_type WHERE id = ?");
  //   stmt.setInt(1, id);
  //   return stmt.executeUpdate() >= 1;
  // }

  // private static Service_Type parseFromRes(ResultSet res) throws Exception {
  //   int id = res.getInt("id");
  //   String service_name = res.getString("service_name");
  //   double price = res.getDouble("price");
  //   int quantity = res.getInt("quantity");

  //   return new Service_Type(id, service_name, price, quantity);
  // }

  // public static int getLastID() throws Exception {
  //   Connection conn = DBConnect.getConnection();
  //   PreparedStatement stmt = conn.prepareStatement("select max(id) from services_type\n");
  //   ResultSet res = stmt.executeQuery();

  //   if (res.next()) {
  //     return res.getInt("max(id)");
  //   }
  //   return 0;
  // }

  // public static List<ServiceChartModel> selectAllChart() throws Exception {
  //   ArrayList<ServiceChartModel> list = new ArrayList<>();
  //   Connection conn = DBConnect.getConnection();
  //   PreparedStatement stmt = conn
  //       .prepareStatement("SELECT service_name,COUNT(*) FROM services_type GROUP BY service_name");
  //   ResultSet res = stmt.executeQuery();
  //   while (res.next()) {
  //     list.add(new ServiceChartModel(res.getString("service_name"), res.getInt("COUNT(*)")));
  //   }
  //   return list;
  // }


}
