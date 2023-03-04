package com.MahadevanRDJ.traintimemanagement.Train;

import java.util.List;

import com.MahadevanRDJ.traintimemanagement.DTOs.Stations;
import com.MahadevanRDJ.traintimemanagement.DTOs.Train;

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
    public void showTrainSchedule(List<Train> schedule) {
       trainView.showTrainSchedule(schedule);
    }
    @Override
    public void getStations(int trainNumber) {
        trainModel.getStations(trainNumber);
    }
    @Override
    public void displayStations(List<Stations> stations) {
        trainView.displayStations(stations);
    }
    
}
