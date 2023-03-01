package com.MahadevanRDJ.traintimemanagement.Train;

import com.MahadevanRDJ.traintimemanagement.DTOs.TrainSchedule;

public interface TrainViewCallBack {

    void showTrainSchedule(TrainSchedule schedule);

    void noTrainSchedule();
    
}
