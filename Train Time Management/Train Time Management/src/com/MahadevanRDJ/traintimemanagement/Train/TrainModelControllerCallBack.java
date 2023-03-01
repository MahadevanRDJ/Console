package com.MahadevanRDJ.traintimemanagement.Train;

import com.MahadevanRDJ.traintimemanagement.DTOs.TrainSchedule;

public interface TrainModelControllerCallBack {

    void noTrainSchedule();

    void showTrainSchedule(TrainSchedule schedule);

}
