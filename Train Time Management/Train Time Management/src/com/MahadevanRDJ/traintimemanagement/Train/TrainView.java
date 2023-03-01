package com.MahadevanRDJ.traintimemanagement.Train;

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
        System.out.println("To :");
        String to = getString();


        trainController.getTrains(from, to);
    }
    private String getString() {
        return Validate.getString();
    }
    @Override
    public void showTrainSchedule(TrainSchedule schedule) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("**** SCHEDULED TRAIN ****");
        System.out.println("Train ID: " + schedule.getTrainID());
        System.out.println("From: " + schedule.getSourcePlace());
        System.out.println("To: " + schedule.getDestinationPlace());
        System.out.println("Departed Time: " + schedule.getSourceTime());
        System.out.println("Reached Time : " + schedule.getDestinationTime());
        System.out.print("Via :" );
        for (int i = 0; i < schedule.getBetweenStations().size() - 1; i++) {
            System.out.print(schedule.getBetweenStations().get(i) + "--> ");
        }
        System.out.print(schedule.getBetweenStations().get(schedule.getBetweenStations().size() -1));
        
    }
    @Override
    public void noTrainSchedule() {
       System.out.println("-------------------------------------------------------------------");
       System.out.println("No train hase been scheduled yet.");
    }
}
