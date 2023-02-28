package com.MahadevanRDJ.carparkingmanagement.CarParkingRepository;

import java.util.HashMap;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.MahadevanRDJ.carparkingmanagement.DTOs.Admin;
import com.MahadevanRDJ.carparkingmanagement.DTOs.Car;
import com.MahadevanRDJ.carparkingmanagement.DTOs.Customer;
import com.MahadevanRDJ.carparkingmanagement.DTOs.ParkingSlot;
import com.MahadevanRDJ.carparkingmanagement.DTOs.User;

public class CarParkingRepository {
    private static CarParkingRepository carParkingInstance;

    private List<Admin> admin = new ArrayList<Admin>();
    private List<User> users = new ArrayList<User>();
    private List<Customer> customers = new ArrayList<Customer>();
    private Customer customer;
    private List<ParkingSlot> parkingSlots = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();
    private List<List<Integer>> perDaySlot = new ArrayList<>();
    private Map<String, List<Integer>> userSlot = new HashMap<String, List<Integer>>();
    private Car car;
    private LocalDate date;
    private LocalTime time;

    private CarParkingRepository() {
    }

    public static CarParkingRepository getInstance() {
        if (carParkingInstance == null) {
            carParkingInstance = new CarParkingRepository();
        }
        return carParkingInstance;
    }

    private void defaultAdmins() {
        admin.add(new Admin("Deva", "DevaRDJ3"));
        admin.add(new Admin("Zoho", "Tenaksi3"));
    }

    public Admin getAdmin(String adminName, String adminPassword) {
        defaultAdmins();
        for (Admin admin : admin) {
            if (adminName.equalsIgnoreCase(admin.getAdminName()) && adminPassword.equals(admin.getAdminPassword())) {
                return admin;
            }
        }
        return null;
    }

    public void addUser(String usersFirstname, String usersLastname, String usersName, String password) {
        users.add(new User(usersFirstname, usersLastname, usersName, password));
    }

    public User getUser(String usersName, String password) {
        for (User user : users) {
            if (usersName.equalsIgnoreCase(user.getUserName()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public boolean checkUserName(String usersName) {
        for (User user : users) {
            if (usersName.equals(user.getUserName())) {
                return true;
            }
        }
        return false;
    }

    public void resetPassword(String usersName, String password) {
        for (User user : users) {
            if (usersName.equals(user.getUserName())) {
                user.setPassword(password);
            }
        }
    }

    public void setDate(LocalDate userDate) {
        this.date = userDate;
    }

    public void setSlotName() {
        LocalDate tempDate = this.date;
        for (int i = 0; i < 7; i++) {
            String name = (tempDate.getMonth().toString().substring(0, 3) + "-" + tempDate.getDayOfMonth());
            parkingSlots.get(i).setSlotName(name);
            try {
                tempDate = tempDate.of(tempDate.getYear(), tempDate.getMonth(), tempDate.getDayOfMonth() + 1);
            }
            catch(Exception e) {
                tempDate = tempDate.of(tempDate.getYear(), tempDate.getMonthValue() + 1, 1);
            }
        }
    }

    public void setInitialSlot() {
        int j = time.getHour();
        for (int i = 0; i < 7; i++) {
            parkingSlots.add(new ParkingSlot(" ", new ArrayList<>()));
            perDaySlot.add(new ArrayList<>());
            List<Integer> parkingSlot = new ArrayList<Integer>();
            for (int k = 0; k < 24; k++) {
                parkingSlot.add(0);
            }
            parkingSlots.get(i).setSlots(parkingSlot);
            perDaySlot.add(i, parkingSlot);
        }

        if (date.getDayOfMonth() == LocalDate.now().getDayOfMonth()) {
            for (int i = 0; i < j; i++)
                parkingSlots.get(0).getSlots().add(i, -1);
        }

        setSlotName();
    }

    
    public List<ParkingSlot> returnParkingSlots() {
        return parkingSlots;
    }

    public void setTime(LocalTime userTime) {
        this.time = userTime;
    }

    public ParkingSlot getSlotDate(String slotDate) {
        for (ParkingSlot parkingSlot : parkingSlots) {
            if (slotDate.equalsIgnoreCase(parkingSlot.getSlotName())) {
                return parkingSlot;
            }
        }
        return null;
    }

    public ParkingSlot setTimeSlot(int slotTime, String slotDate) {
        int date = Integer.parseInt(slotDate.substring(4)) - this.date.getDayOfMonth();
        for (ParkingSlot parkingSlot : parkingSlots) {
            if (parkingSlot.getSlotName().equals(slotDate)) {
                if (parkingSlot.getSlots().get(slotTime) != -1 && parkingSlot.getSlots().get(slotTime) != 1 && slotTime < 24) {
                    perDaySlot.get(date).add(slotTime, 1);
                    ParkingSlot.setNumberOfSlots((ParkingSlot.getNumberOfSlots() + 1));
                    parkingSlot.getSlots().set(slotTime, 1);
                    updateUserSlot(slotDate.toUpperCase(), perDaySlot.get(date));
                    return parkingSlot;
                }
            }
        }
        return null;
    }

    public void updateUserSlot(String slotDate, List<Integer> slot) {
        userSlot.put(slotDate, slot);
    }

    public boolean removeSlot(String slotDate, int slotTime) {
        int date = Integer.parseInt(slotDate.substring(4)) - this.date.getDayOfMonth();
        if (userSlot.containsKey(slotDate.toUpperCase())) {
            for (int i = 0; i < 24; i++) {
                if (i == slotTime && parkingSlots.get(date).getSlots().get(i) == 1) {
                    parkingSlots.get(date).getSlots().remove(i);
                    parkingSlots.get(date).getSlots().add(i, 0);
                    perDaySlot.get(date).remove(i);
                    perDaySlot.get(date).add(i, 0);
                    ParkingSlot.setNumberOfSlots(ParkingSlot.getNumberOfSlots() - 1);
                    updateUserSlot(slotDate.toUpperCase(), perDaySlot.get(date));
                    return true;
                }
            }
        }
        return false;
    }

    public int totalFare() {
        return  ParkingSlot.getNumberOfSlots() * parkingSlots.get(0).getFare();
    }

    public void addCustomerDetails(String customerName, String gender, long contact, String city) {
        this.customer = new Customer(customerName, gender, contact, city);
        customers.add(customer);
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void addCar(String carName, String carNumber) {
        car = new Car(carName, carNumber);
        cars.add(car);
    }

    public Car getCar() {
        return this.car;
    }

    public Map<String, List<Integer>> getBookedSlots() {
        return userSlot;
    }
}
