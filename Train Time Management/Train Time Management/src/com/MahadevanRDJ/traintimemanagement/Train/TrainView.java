package com.MahadevanRDJ.traintimemanagement.Train;

import java.util.List;
import java.util.Scanner;

import com.MahadevanRDJ.traintimemanagement.DTOs.TrainSchedule;
import com.MahadevanRDJ.traintimemanagement.utils.Validate;

public class TrainView implements TrainViewCallBack {
    private TrainControllerCallBack trainController;
    private Scanner scanner = new Scanner(System.in);

    public TrainView() {
        this.trainController = new TrainController(this);
    }
    public static void main(String[] args) {
        TrainView trainView = new TrainView();
        trainView.init();
    }
    public void init() {
        int choice = 0;
        
        do{
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. Search train");
            System.out.println("2. EXIT");
            while(!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Choice: ");
            } 
            choice = scanner.nextInt();
            switch(choice) {
                case 1: searchTrain();break;
                case 2: System.exit(0); break;  
            }
        } while(choice != 2);

    }

    private void searchTrain() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("From :");
        String from = getString();
        if(!Validate.check(from)) searchTrain();
        System.out.println("To :");
        String to = getString();
        if(!Validate.check(to)) searchTrain();
        if(!from.equalsIgnoreCase(to)) {
            trainController.getTrains(from, to);
        }
        else {
            System.out.println("Both sourece and destination can't be same.");
            searchTrain();
        }
    }
    private String getString() {
        return Validate.getString();
    }
    @Override
    public void showTrainSchedule(List<TrainSchedule> allSchedule) {
        for (TrainSchedule trainSchedule : allSchedule) {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("**** SCHEDULED TRAIN ****");
            System.out.println("Train ID: " + trainSchedule.getTrainID());
            System.out.println("From: " + trainSchedule.getSourcePlace());
            System.out.println("To: " + trainSchedule.getDestinationPlace());
            System.out.println("Departing Time: " + trainSchedule.getSourceTime());
            System.out.println("Reaching Time : " + trainSchedule.getDestinationTime());
            System.out.print("Via :" );
            for (int i = 0; i < trainSchedule.getBetweenStations().size() - 1; i++) {
                System.out.print(trainSchedule.getBetweenStations().get(i) + "--> ");
            }
            System.out.print(trainSchedule.getBetweenStations().get(trainSchedule.getBetweenStations().size() -1));
            System.out.println();
        }
    }
    @Override
    public void noTrainSchedule() {
       System.out.println("-------------------------------------------------------------------");
       System.out.println("No train hase been scheduled yet.");
    }
}
