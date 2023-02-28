package com.MahadevanRDJ.carparkingmanagement.SlotFill;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.MahadevanRDJ.carparkingmanagement.DTOs.ParkingSlot;

public class SlotFillController implements SlotFillControllerCallBack, SlotFillModelControllerCallBack {
    private SlotFillModelCallBack slotFillModel;
    private SlotFillViewCallBack slotFillView;
    private LocalDate currentDate = LocalDate.now();

    public SlotFillController(SlotFillView slotFillView) {
        this.slotFillView = slotFillView;
        this.slotFillModel = new SlotFillModel(this);
    }
    @Override
    public void checkDate(LocalDate userDate) {
        int difference = userDate.compareTo(currentDate);
        if(difference >= 0) {
            slotFillModel.setDate(userDate);
            slotFillView.validDate(userDate);
        } else {
            slotFillView.inValidDate();
        }
    }
    @Override
    public void setTime(LocalTime userTime) {
        slotFillModel.setTime(userTime);
    }
    @Override
    public void getSlots() {
       slotFillModel.getSlots();
    }
    @Override
    public void displaySlots(List<ParkingSlot> parkingSlots) {
       slotFillView.displaySlots(parkingSlots);
    }
    @Override
    public void setSlotDate(String slotDate) {
        slotFillModel.setSlotDate(slotDate);
    }
    @Override
    public void slotNotFound() {
        slotFillView.slotNotFound();
    }
    @Override
    public void chooseSlots(ParkingSlot slot) {
        slotFillView.chooseSlots(slot);
    }
    @Override
    public void setSlotTime(int slotTime, String slotDate) {
        slotFillModel.setSlotTime(slotTime, slotDate);
    }
    @Override
    public void slotUnAvailable() {
        slotFillView.slotNotAvailable();
    }
    @Override
    public void showSlot(ParkingSlot slot) {
        slotFillView.showSlot(slot);
    }
    @Override
    public void totalFare() {
        slotFillModel.totalFare();
    }
    @Override
    public void totalFare(int total) {
        slotFillView.totalFare(total);
    }
    @Override
    public void removeSlot(String slotDate, int slotTime) {
        slotFillModel.removeSlot(slotDate, slotTime);
    }
    @Override
    public void notRemoved() {
        slotFillView.notRemoved();
    }
    @Override
    public void removedSuccessfully() {
        slotFillView.removedSuccessfully();
    }
    @Override
    public void displayBookedSlots() {
        slotFillModel.displayBookedSlots();
    }
    @Override
    public void displayBookedSlots(Map<String, List<Integer>> bookedSlots) {
        slotFillView.displayBookedSlots(bookedSlots);
    }
    
}
