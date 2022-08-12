package sg.edu.rp.c346.id21019423.carparkavailability;

public class Car {

    private String carpark_number;
    private String update_datetime;


    public Car(String carpark_number, String update_datetime) {
        this.carpark_number = carpark_number;
        this.update_datetime = update_datetime;
    }

    public String getCarpark_number() {
        return carpark_number;
    }

    public void setCarpark_number(String carpark_number) {
        this.carpark_number = carpark_number;
    }

    public String getUpdate_datetime() {
        return update_datetime;
    }

    public void setUpdate_datetime(String update_datetime) {
        this.update_datetime = update_datetime;
    }


    @Override
    public String toString() {
        return "CARPARK AVAILABILITY" + '\n' +
                "Carpark number: " + carpark_number + '\n' +
                "Update Datetime: " + update_datetime + '\n';
    }
}
