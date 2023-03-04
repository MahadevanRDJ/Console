package com.MahadevanRDJ.traintimemanagement.Train;


public interface TrainControllerCallBack {

    void getTrains(String from, String to);

    void getStations(int trainNumber);
    
}
