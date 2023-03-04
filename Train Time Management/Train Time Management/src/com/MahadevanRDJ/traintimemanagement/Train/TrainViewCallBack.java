package com.MahadevanRDJ.traintimemanagement.Train;

import java.util.List;

import com.MahadevanRDJ.traintimemanagement.DTOs.TrainSchedule;

public interface TrainViewCallBack {

    void showTrainSchedule(List<TrainSchedule> schedule);

    void noTrainSchedule();
    
}
