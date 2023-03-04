package com.MahadevanRDJ.traintimemanagement.Train;

import java.util.List;

import com.MahadevanRDJ.traintimemanagement.DTOs.Stations;
import com.MahadevanRDJ.traintimemanagement.DTOs.Train;

public interface TrainViewCallBack {

    void showTrainSchedule(List<Train> schedule);

    void noTrainSchedule();

    void displayStations(List<Stations> stations);
    
}
