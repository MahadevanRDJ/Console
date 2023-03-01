package com.MahadevanRDJ.traintimemanagement.Train;

import com.MahadevanRDJ.traintimemanagement.DTOs.TrainSchedule;

public class TrainController implements TrainControllerCallBack, TrainModelControllerCallBack {

    private TrainViewCallBack trainView;
    private TrainModelCallBack trainModel;
    public TrainController(TrainViewCallBack trainView) {
        this.trainModel = new TrainModel(this);
        this.trainView = trainView;
    }
    @Override
    public void getTrains(String from, String to) {
        trainModel.getTrains(from, to);
    }
    @Override
    public void noTrainSchedule() {
        trainView.noTrainSchedule();
    }
    @Override
    public void showTrainSchedule(TrainSchedule schedule) {
       trainView.showTrainSchedule(schedule);
    }
    
}
