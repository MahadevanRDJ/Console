package com.MahadevanRDJ.carparkingmanagement.SlotFill;

import java.time.LocalDate;
import java.time.LocalTime;

public interface SlotFillControllerCallBack {

    void checkDate(LocalDate userDate);

    void setTime(LocalTime userTime);

    void getSlots();

    void setSlotDate(String slotID);

    void setSlotTime(int slotTime, String string);

    void totalFare();

    void removeSlot(String slotDate, int slotTime);

    void displayBookedSlots();
    
}
