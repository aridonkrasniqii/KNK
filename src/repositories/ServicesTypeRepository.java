package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import components.ErrorPopupComponent;
import database.DBConnection;
import database.InsertQueryBuilder;
import helpers.Service_Type;

public class ServicesTypeRepository {

    private static DBConnection connection = DBConnection.getConnection();


    public static Service_Type parseFromResult(ResultSet result) throws Exception {
        int id = result.getInt("id");
        String service_name = result.getString("service_name");
        double price = result.getDouble("price");
        int quantity = result.getInt("quantity");

        return new Service_Type(id, service_name, price, quantity);
    }

    public static Service_Type find(int id) throws Exception {

        String query = "select * from services_type where id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1 , id);
        ResultSet res = stmt.executeQuery();

        if (!res.next()) {
            return null;
        }
        return parseFromResult(res);
    }

    public static Service_Type create(Service_Type service) throws Exception {
        InsertQueryBuilder query = (InsertQueryBuilder) InsertQueryBuilder.create("services_type")
                .add("id", service.getId(), "i")
                .add("service_name", service.getService_name(), "s")
                .add("price", (float) service.getPrice(), "f")
                .add("quantity", service.getQuantity(), "i");

        int lastInsertedId = connection.execute(query);
        Service_Type service_tp = find(lastInsertedId);

        if (service_tp != null) {
            return service_tp;
        }
        ErrorPopupComponent.show("Service could not be created!");
        return null;
    }


    public static Service_Type update(Service_Type model) throws Exception {

        String query = "update services_type set service_name = ?, price = ? , quantity = ? where id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1,model.getService_name());
        stmt.setDouble(2,model.getPrice());
        stmt.setInt(3, model.getQuantity());
        stmt.setInt(4,model.getId());

        int affectedRows = stmt.executeUpdate();
        if(affectedRows != 1) {
            throw new Exception("ERR_NO_ROW_CHANGE");
        }

        return find(model.getId());

    }


    public static boolean remove(int id) throws Exception {
        String query = "delete from services_type where id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);

        return stmt.executeUpdate() == 1;
    }


    public ArrayList<Service_Type> findAll() throws Exception {
        String query = "select * from services_type";
        ResultSet res = this.connection.executeQuery(query);
        ArrayList<Service_Type> service_types = new ArrayList<Service_Type>();

        while (res.next()) {
            System.out.println("Service_Type id : " + res.getInt("id"));
            service_types.add(Service_Type.fromResultSet(res));
        }
        return service_types;
    }



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
