package com.MahadevanRDJ.carparkingmanagement.SlotFill;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.MahadevanRDJ.carparkingmanagement.Customer.CustomerView;
import com.MahadevanRDJ.carparkingmanagement.Customer.CustomerViewCallBack;
import com.MahadevanRDJ.carparkingmanagement.DTOs.ParkingSlot;
import com.MahadevanRDJ.carparkingmanagement.util.Validate;

public class SlotFillView implements SlotFillViewCallBack {
    private SlotFillControllerCallBack slotFillController;
    private boolean isDisplayed = false;
    private boolean isTime = false;

    public SlotFillView() {
        this.slotFillController = new SlotFillController(this);
    }
    public static void main(String[] args) {
        SlotFillView sv = new SlotFillView();
        sv.init();
    }
    @Override
    public void init() {
        int choice;
        do {
            System.out.println("------------------------------------------------");
            System.out.println("1. Select Date And Time");
            System.out.println("2. Display Slots");
            System.out.println("3. Add Slot");
            System.out.println("4. Remove Slot");
            System.out.println("5. Display Booked slots");
            System.out.println("6. Total fare");
            System.out.println("7. Customer Details");
            System.out.println("8. EXIT");
            System.out.println("Choice: ");
            choice = getInteger();
            switch (choice) {
                case 1:
                    fromDate();
                    break;
                case 2:
                    if(isTime) displaySlots();
                    break;
                case 3:
                    if(isDisplayed) slotBooking();
                    break;
                case 4:
                    if(isDisplayed) removeSlot();
                    break;
                case 5:
                    if(isDisplayed) displayBookedSlots();
                    break;
                case 6:
                    if(isDisplayed) totalFare();
                    break;
                case 7:
                    if(isDisplayed) goToCustomer();
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 8);
    }

    private void displayBookedSlots() {
        slotFillController.displayBookedSlots();
    }

    private void goToCustomer() {
        CustomerViewCallBack cView = new CustomerView();
        cView.init();
    }

    private void fromDate() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Date :");
        int date = getInteger();
        System.out.println("Month :");
        int month = getInteger();
        System.out.println("Year :");
        int year = getInteger();

        try {
            LocalDate userDate = LocalDate.of(year, month, date);
            slotFillController.checkDate(userDate);
        } catch (Exception e) {
            System.out.println("Invalid date / Not a leap year.");
            fromDate();
        }
        
        isTime = true;
    }

    private void fromTime() {
        LocalTime userTime = LocalTime.now();
        slotFillController.setTime(userTime);
    }

    private void displaySlots() {
        slotFillController.getSlots();
        isDisplayed = true;
    }

    private void slotBooking() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("------------Slot Booking-----------");
        System.out.println("Slot Date: -> Format : [month-date]");
        String slotDate = getString();
        slotFillController.setSlotDate(slotDate);
    }

    public void removeSlot() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Remove Slot : -> Format : [month-date]");
        System.out.println("Slot Date: ");
        String slotDate = getString();
        System.out.println("Slot Time : ");
        int slotTime = getInteger();
        slotFillController.removeSlot(slotDate, slotTime);
    }

    public void totalFare() {
        slotFillController.totalFare();

    }
    private int getInteger() {
        return Validate.getInt();
    }

    private String getString() {
        return Validate.getString();
    }

    @Override
    public void validDate(LocalDate date) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Your Date is valid.");
        fromTime();
    }

    @Override
    public void inValidDate() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Dear Customer, you're trying to book the slot before the current date.");
        fromDate();
    }


    @Override
    public void displaySlots(List<ParkingSlot> slots) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("-------------------------Slots Sheets---------------------------");
        System.out.println("\n\n");
        System.out.print("Slots \t");
        for (int i = 0; i < slots.size(); i++) {
            System.out.print(slots.get(i).getSlotName() + "\t");
        }
        System.out.println();
        for (int i = 0; i < 24; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < 7; j++) {
                System.out.print(slots.get(j).getSlots().get(i) + "\t");
            }
            System.out.println();
        }

    }

    @Override
    public void chooseSlots(ParkingSlot slot) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Slots: ");
        int slotTime = getInteger();
        slotFillController.setSlotTime(slotTime, slot.getSlotName());
    }

    @Override
    public void slotNotFound() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Sorry, you've chosen a slot before current date.");
    }

    @Override
    public void showSlot(ParkingSlot slot) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Slot booking is successful.");
    }

    @Override
    public void slotNotAvailable() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Dear Customer, the slot you're trying to book is currently unavailable.");
    }

    @Override
    public void totalFare(int total) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Total Fare: " + total);

    }

    @Override
    public void notRemoved() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Slot you've chosen to remove is unaccessible.");
    }

    @Override
    public void removedSuccessfully() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Slot removed successfully.");

    }

    @Override
    public void displayBookedSlots(Map <String, List<Integer>> bookedSlots) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("-------------------------User Booked Slots-----------------------");
        
        for (Map.Entry<String, List<Integer>> entry : bookedSlots.entrySet()) {
            System.out.println(entry.getKey());
            System.out.print("Slots Time :");
            for (int i = 0; i < entry.getValue().size(); i++) {
                if (entry.getValue().get(i) == 1) 
                    System.out.print(i + "  ");
            }
            System.out.println();
        }
    }
}
