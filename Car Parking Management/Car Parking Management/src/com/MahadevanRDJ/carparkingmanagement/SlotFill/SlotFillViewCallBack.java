package com.MahadevanRDJ.carparkingmanagement.SlotFill;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.MahadevanRDJ.carparkingmanagement.DTOs.ParkingSlot;

public interface SlotFillViewCallBack {

    void validDate(LocalDate userDate);

    void inValidDate();

    void displaySlots(List<ParkingSlot> parkingSlots);

    void chooseSlots(ParkingSlot slot);

    void slotNotFound();

    void slotNotAvailable();

    void showSlot(ParkingSlot slot);

    void totalFare(int total);

    void notRemoved();

    void removedSuccessfully();

    void displayBookedSlots(Map<String, List<Integer>> bookedSlots);

    void init();
    
}
