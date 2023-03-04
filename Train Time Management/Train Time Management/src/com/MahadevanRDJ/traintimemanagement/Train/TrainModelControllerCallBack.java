package com.MahadevanRDJ.traintimemanagement.Train;

import java.util.List;

import com.MahadevanRDJ.traintimemanagement.DTOs.TrainSchedule;

public interface TrainModelControllerCallBack {

    void noTrainSchedule();

    void showTrainSchedule(List<TrainSchedule> schedule);

}
