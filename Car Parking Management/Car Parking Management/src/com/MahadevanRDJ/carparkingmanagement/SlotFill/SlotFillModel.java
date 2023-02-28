package com.MahadevanRDJ.carparkingmanagement.SlotFill;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.MahadevanRDJ.carparkingmanagement.CarParkingRepository.CarParkingRepository;
import com.MahadevanRDJ.carparkingmanagement.DTOs.ParkingSlot;

public class SlotFillModel implements SlotFillModelCallBack {
    private SlotFillModelControllerCallBack slotFillController;

    public SlotFillModel(SlotFillModelControllerCallBack slotFillController) {
        this.slotFillController = slotFillController;
    }

    @Override
    public void setDate(LocalDate userDate) {
       CarParkingRepository.getInstance().setDate(userDate);
    }

    @Override
    public void setTime(LocalTime userTime) {
        CarParkingRepository.getInstance().setTime(userTime);
    }

    @Override
    public void getSlots() {
       CarParkingRepository.getInstance().setInitialSlot();
       slotFillController.displaySlots(CarParkingRepository.getInstance().returnParkingSlots());
    }

    @Override
    public void setSlotDate(String slotDate) {
        ParkingSlot slot = CarParkingRepository.getInstance().getSlotDate(slotDate);
        if(slot != null) {
            slotFillController.chooseSlots(slot);
        } else {
            slotFillController.slotNotFound();
        }
    }

    @Override
    public void setSlotTime(int slotTime, String slotDate) {
        ParkingSlot slot = CarParkingRepository.getInstance().setTimeSlot(slotTime, slotDate);
        if(slot != null) {
            slotFillController.showSlot(slot);
        } else {
            slotFillController.slotUnAvailable();
        }
    }

    @Override
    public void totalFare() {
        int total = CarParkingRepository.getInstance().totalFare();
        slotFillController.totalFare(total);
    }

    @Override
    public void removeSlot(String slotDate, int slotTime) {
        boolean isRemoved = CarParkingRepository.getInstance().removeSlot(slotDate, slotTime);
        if(isRemoved) {
            slotFillController.removedSuccessfully();
        } else {
            slotFillController.notRemoved();
        }
    }

    @Override
    public void displayBookedSlots() {
        Map<String, List<Integer>> bookedSlots = CarParkingRepository.getInstance().getBookedSlots();
        slotFillController.displayBookedSlots(bookedSlots);

    }
    
}
interface SlotFillModelControllerCallBack{

    void displaySlots(List<ParkingSlot> list);

    void displayBookedSlots(Map<String, List<Integer>> bookedSlots);

    void notRemoved();

    void removedSuccessfully();

    void totalFare(int total);

    void slotUnAvailable();

    void showSlot(ParkingSlot slot);

    void slotNotFound();

    void chooseSlots(ParkingSlot slot);

}