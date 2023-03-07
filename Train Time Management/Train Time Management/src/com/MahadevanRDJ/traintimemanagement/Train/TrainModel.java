package com.MahadevanRDJ.traintimemanagement.Train;

import java.util.List;

import com.MahadevanRDJ.traintimemanagement.DTOs.Stations;
import com.MahadevanRDJ.traintimemanagement.DTOs.Train;
import com.MahadevanRDJ.traintimemanagement.TrainRepository.TrainRepository;

public class TrainModel implements TrainModelCallBack {
    private TrainModelControllerCallBack trainController;

    public TrainModel(TrainController trainController) {
        this.trainController = trainController;
    }

    @Override
    public void getTrains(String from, String to) {
        List<Train> schedule = TrainRepository.getInstance().getTrainSchedule(from, to);
        if(schedule != null) {
            trainController.showTrainSchedule(schedule);
        } else {
            trainController.noTrainSchedule();
        }
    }

    @Override
    public void getStations(int trainNumber) {
        List<Stations> stations = TrainRepository.getInstance().getStations(trainNumber);
        trainController.displayStations(stations);
    }

    
}
