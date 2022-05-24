package models.charts;

public class ServiceTypeChart {

    private int count;
    private String serviceName;



    public ServiceTypeChart(int count , String serviceName) {
        this.count = count;
        this.serviceName = serviceName;
    }

    public int getCount() {
        return count;
    }

    public String getServiceName() {
        return serviceName;
    }
}
