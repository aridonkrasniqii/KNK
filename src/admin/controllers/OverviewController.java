package admin.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import models.charts.EventChart;
import models.charts.RoomChart;
import models.charts.ServiceTypeChart;
import models.charts.StaffChart;
import repositories.EventsRepository;
import repositories.RoomRepository;
import repositories.ServicesTypeRepository;
import repositories.StaffRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {


    @FXML
    private PieChart serviceChart;
    @FXML
    private PieChart staffChart;
    @FXML
    private PieChart roomsChart;
    @FXML
    private PieChart eventsChart;


    private ObservableList<PieChart.Data> staffList;
    private ObservableList<PieChart.Data> servicesList;
    private ObservableList<PieChart.Data> roomsList;
    private ObservableList<PieChart.Data> eventsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            roomsList = FXCollections.observableArrayList(loadRooms());
            staffList= FXCollections.observableArrayList(loadStaff());
            servicesList = FXCollections.observableArrayList(loadServices());
            eventsList = FXCollections.observableArrayList(loadEvents());
            roomsChart.setData(roomsList);
            eventsChart.setData(eventsList);
            staffChart.setData(staffList);
            serviceChart.setData(servicesList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private ArrayList<PieChart.Data> loadRooms() throws Exception {
        ArrayList<RoomChart> rooms = RoomRepository.findType();
        ArrayList<PieChart.Data> pieChartDate = new ArrayList<>();
        rooms.forEach(r -> {
            pieChartDate.add(new PieChart.Data(r.getRoomType(), r.getCount()));
        });
        return pieChartDate;
    }

    private ArrayList<PieChart.Data> loadEvents() throws Exception {

        ArrayList<EventChart> events = EventsRepository.findOrganizer();
        ArrayList<PieChart.Data> pieChartEvents = new ArrayList<>();

        events.forEach(e -> {
            pieChartEvents.add(new PieChart.Data(e.getOrganizer(), e.getCount()));
        });

        return pieChartEvents;
    }


    private ArrayList<PieChart.Data> loadStaff() throws Exception {
        ArrayList<StaffChart> staffs = StaffRepository.findPositions();
        ArrayList<PieChart.Data> pieChartStaff = new ArrayList<>();

        staffs.forEach(s -> {
            pieChartStaff.add(new PieChart.Data(s.getPosition(), s.getCount()));
        });

        return pieChartStaff;
    }

    private ArrayList<PieChart.Data> loadServices() throws Exception {

        ArrayList<ServiceTypeChart> services = ServicesTypeRepository.findServicesName();
        ArrayList<PieChart.Data> pieChartService = new ArrayList<>();


        services.forEach(s -> {
           pieChartService.add(new PieChart.Data(s.getServiceName(), s.getCount()));
        });

        return pieChartService;
    }


}




















