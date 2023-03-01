package com.MahadevanRDJ.traintimemanagement.TrainRepository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.MahadevanRDJ.traintimemanagement.DTOs.Admin;
import com.MahadevanRDJ.traintimemanagement.DTOs.TrainSchedule;
import com.MahadevanRDJ.traintimemanagement.DTOs.User;

public class TrainRepository {
    private static TrainRepository trainInstance;
    private Admin admin;
    private User user;
    private TrainSchedule trainSchedule = new TrainSchedule(null, 0, null, null, null, null, null, null);
    private List<String> betweenStations = new ArrayList<String>();
    private List<Integer> trainNumber = new ArrayList<Integer>();

    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection connection;
    private TrainRepository() {
    }

    public  void createConnection()  {
        String url = "jdbc:mysql://localhost:3306/train";
        String usermail = "root";
        String password = "ArunEswari3#";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, usermail, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static TrainRepository getInstance() {
        if (trainInstance == null) {
            trainInstance = new TrainRepository();
            trainInstance.createConnection();
            return trainInstance;
        }
        return trainInstance;
    }

    public Admin adminLogin(String adminName, String password)  {
        query = "Select * from admin Where name='" + adminName + "' and password='" + password + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                admin = new Admin(adminName , password);
                return admin;
            }
            statement.close();
        } catch (SQLException e) {  
        }        
        return null;
    }
    public void addUser(String firstName, String lastName, String username, String password) {
        // query = "INSERT INTO user VALUES (" + firstmail + ", " + lastmail + ", " + usermail + ", " + password + ")";
        query = "INSERT INTO user VALUES (?, ?, ?, ?, ?)";
        int id = new Random().nextInt(0, Integer.MAX_VALUE);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User userLogin(String userName, String password)  {
        query = "Select * from user Where username='" + userName + "' and password='" + password + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if(resultSet.next()) {
                user = new User(userName, password);
                return user;
            }
            statement.close();
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean checkUser(String userName)  {
       query = "select * from user where username = '" + userName + "'";

       try {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        
        while(resultSet.next()) {
            return true;
        }
       } catch (Exception e) {
       }
       return false;
    }
    
    public void resetPassword(String userName, String password) {
        String querySelect = "SELECT * FROM  user where username = '" + userName + "' ";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(querySelect);
            if(resultSet.next()) {
                query = "Update user SET password = ' " + password + "' where username = '" + userName + "'";
                preparedStatement = connection.prepareStatement(query);
            
                preparedStatement.executeUpdate();

                preparedStatement.close();
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   

    public TrainSchedule getTrainSchedule(String from, String to) {
        query = "Select * from stations";
        from = from.toUpperCase();
        to= to.toUpperCase();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                if(resultSet.getString(2).equalsIgnoreCase(from)) {
                    trainSchedule.setSourceCode(resultSet.getString(1));
                    trainSchedule.setSourcePlace(from);
                } 
                if(resultSet.getString(2).equalsIgnoreCase(to)) {
                    trainSchedule.setDestinationCode(resultSet.getString(1));
                    trainSchedule.setDestinationPlace(to);
                }
            }

            statement.close();
            return trainSchedule(trainSchedule);
        } catch (SQLException e) {
        
        }
        return null;
    }

    private TrainSchedule trainSchedule(TrainSchedule trainSchedule) {
        String sourceStationCode = trainSchedule.getSourceCode();
        String destinationStationCode = trainSchedule.getDestinationCode();
        String sourceTime = "";
        String destinaionTime = "";
        query = "Select * from schedule where stationcode = '" + sourceStationCode + "' and trainno in (Select DISTINCT trainno from schedule)";
        if(trainSchedule.getSourcePlace() != null && trainSchedule.getDestinationPlace() != null)  {
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                while(resultSet.next()) {
                    trainNumber.add(resultSet.getInt(2));
                }
                for (int trainNo : trainNumber) {

                    String sourceTime_QUERY = "Select * from schedule where trainno = " + trainNo + " and stationcode = '" + sourceStationCode + "'";
                    resultSet = statement.executeQuery(sourceTime_QUERY);
                    
                    int toTime = 0, fromTime = 0;
                    while(resultSet.next()) {
                        sourceTime = ((resultSet.getString(5)));
                        fromTime = Integer.parseInt(sourceTime.substring(0, 2)) % 24;
                    }


                    String destinationTime_QUERY = "Select * from schedule where trainno = " +  trainNo + " and stationcode = '" + destinationStationCode + "'";
                    resultSet = statement.executeQuery(destinationTime_QUERY);
                    
                    while(resultSet.next()) {
                        destinaionTime = (resultSet.getString(4));
                        toTime = Integer.parseInt((destinaionTime.substring(0, 2))) % 24;
                    }
                    
                   

                    if(toTime < fromTime) continue;
                    
                    setDepartedTime(sourceTime);
                    setReachedTime(destinaionTime);
                    resultSet = statement.executeQuery("SELECT * From schedule where trainno = " + trainNo);
                    while(resultSet.next()) {
                        
                        sourceTime = (resultSet.getString(5));
                        int t_fromTime = Integer.parseInt((sourceTime.substring(0, 2))) % 24;
                        while(!(t_fromTime == fromTime)) {
                            sourceTime = (resultSet.getString(5));
                            t_fromTime = Integer.parseInt((sourceTime.substring(0, 2))) % 24;
                            resultSet.next(); 
                        }
                        
                        betweenStations.add(resultSet.getString(3));
                        while(resultSet.next()) {
                            if(!(fromTime == toTime)) {
                                betweenStations.add(resultSet.getString(3));
                                sourceTime = (resultSet.getString(5));
                                fromTime = Integer.parseInt((sourceTime.substring(0, 2))) % 24;
                            }
                        }
                    }
                    trainSchedule.setTrainID(trainNo);
                }
                resultSet.close();
                statement.close();
                trainSchedule.setBetweenStations(betweenStations);
                return trainSchedule;
            } catch (SQLException e) {
            }
        }
        return null;
    }

    private void setReachedTime(String destinaionTime) {
        int hour = Integer.parseInt(destinaionTime.substring(0, 2)) % 24;
        String min = destinaionTime.substring(3);
        trainSchedule.setDestinationTime(hour + ":" + min);
    }

    private void setDepartedTime(String sourceTime) {
        int hour = Integer.parseInt(sourceTime.substring(0, 2)) % 24;
        String min = sourceTime.substring(3);
        trainSchedule.setSourceTime(hour + ":" + min);
    }
}