package com.MahadevanRDJ.traintimemanagement.Train;

import com.MahadevanRDJ.traintimemanagement.DTOs.TrainSchedule;
import com.MahadevanRDJ.traintimemanagement.TrainRepository.TrainRepository;

public class TrainModel implements TrainModelCallBack {
    private TrainModelControllerCallBack trainController;

    public TrainModel(TrainController trainController) {
        this.trainController = trainController;
    }

    @Override
    public void getTrains(String from, String to) {
        TrainSchedule schedule = TrainRepository.getInstance().getTrainSchedule(from, to);
        if(schedule != null) {
            trainController.showTrainSchedule(schedule);
        } else {
            trainController.noTrainSchedule();
        }
    }

    
}
