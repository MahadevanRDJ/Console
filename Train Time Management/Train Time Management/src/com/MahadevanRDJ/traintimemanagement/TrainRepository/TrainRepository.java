package com.MahadevanRDJ.traintimemanagement.TrainRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.MahadevanRDJ.traintimemanagement.DTOs.Admin;
import com.MahadevanRDJ.traintimemanagement.DTOs.TrainSchedule;
import com.MahadevanRDJ.traintimemanagement.DTOs.User;

public class TrainRepository {
    private static TrainRepository trainInstance;

    private Admin admin;
    private User user;
    private List<TrainSchedule> allSchedule = new ArrayList<>();
    private List<Integer> trains;
    private Map<String, String> stations = new LinkedHashMap<String, String>();
    private static TrainSchedule currentTrainSchedule = new TrainSchedule();

    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection connection;

    private TrainRepository() {
    }

    public void createConnection() {
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
            trainInstance.addStationName();
            return trainInstance;
        }
        return trainInstance;
    }

    public Admin adminLogin(String adminName, String password) {
        query = "Select * from admin Where name='" + adminName + "' and password='" + password + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                admin = new Admin(adminName, password);
                return admin;
            }
            statement.close();
        } catch (SQLException e) {
        }
        return null;
    }

    public void addUser(String firstName, String lastName, String username, String password) {
        // query = "INSERT INTO user VALUES (" + firstmail + ", " + lastmail + ", " +
        // usermail + ", " + password + ")";
        query = "INSERT INTO user(Firstname, Lastname, Username, Password) VALUES (?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User userLogin(String userName, String password) {
        query = "Select * from user Where username='" + userName + "' and password='" + password + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                user = new User(userName, password);
                return user;
            }
            statement.close();
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean checkUser(String userName) {
        query = "select * from user where username = '" + userName + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
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
            if (resultSet.next()) {
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

    private void addStationName() {
        query = "Select * from stations";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                stations.put(resultSet.getString(1), resultSet.getString(2));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
        }
    }
    
    public List<TrainSchedule> getTrainSchedule(String from, String to) {
        query = "Select * from stations";
        from = from.toUpperCase();
        to = to.toUpperCase();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if (resultSet.getString(2).equals(from)) {
                    currentTrainSchedule.setSourceCode(resultSet.getString(1));
                    currentTrainSchedule.setSourcePlace(from);
                }
                if (resultSet.getString(2).equals(to)) {
                    currentTrainSchedule.setDestinationCode(resultSet.getString(1));
                    currentTrainSchedule.setDestinationPlace(to);
                }
            }

            statement.close();
            currentTrainSchedule();
            return allSchedule;
        } catch (SQLException e) {
        }
        return null;
    }
    
    private void currentTrainSchedule() {
        String sourceStationCode = currentTrainSchedule.getSourceCode();
        String destinationStationCode = currentTrainSchedule.getDestinationCode();
        String sourceTime = "";
        String destinaionTime = "";
        trains = new ArrayList<>();
        query = "Select * from schedule where stationcode = '" + sourceStationCode
                + "' and trainno in (Select DISTINCT trainno from schedule)";
        
        if (currentTrainSchedule.getSourcePlace() != null && currentTrainSchedule.getDestinationPlace() != null) {
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    trains.add(resultSet.getInt(2));
                }

                for (int trainNo : trains) {

                    String sourceTime_QUERY = "Select * from schedule where trainno = " + trainNo
                            + " and stationcode = '" + sourceStationCode + "'";
                    resultSet = statement.executeQuery(sourceTime_QUERY);

                    int toTime = 0, fromTime = 0;
                    while (resultSet.next()) {
                        sourceTime = resultSet.getString(5);
                        fromTime = getTime(sourceTime);
                    }

                    if(fromTime == -9999) continue;
                    
                    String destinationTime_QUERY = "Select * from schedule where trainno = " + trainNo
                            + " and stationcode = '" + destinationStationCode + "'";
                    resultSet = statement.executeQuery(destinationTime_QUERY);

                    while (resultSet.next()) {
                        destinaionTime = resultSet.getString(4);
                        toTime = getTime(destinaionTime);
                    }

                    if (toTime < fromTime)
                        continue;
                    
                    setDepartedTime(sourceTime);
                    setReachedTime(destinaionTime);
                    addStations(trainNo, fromTime, toTime);
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
            }
        }
    }
    private void addStations(int trainNo, int fromTime, int toTime) {

        try {
            resultSet = statement.executeQuery("SELECT * From schedule where trainno = " + trainNo);
            List<String> betweenStations = new ArrayList<String>();
            TrainSchedule tSchedule = (TrainSchedule) currentTrainSchedule.clone();

            while (resultSet.next()) {

                int t_fromTime = getTime(resultSet.getString(5));
                while ((t_fromTime < fromTime)) {
                    t_fromTime = getTime(resultSet.getString(5));
                    resultSet.next();
                }

                betweenStations.add(stations.get(resultSet.getString(3)));
                while (resultSet.next()) {
                    if ((fromTime <= toTime) ) {
                        betweenStations.add(stations.get(resultSet.getString(3)));
                        fromTime = getTime(resultSet.getString(5));
                    }
                }
                tSchedule.setBetweenStations(betweenStations);
                tSchedule.setTrainID(trainNo);
                allSchedule.add(tSchedule);
            }
        } catch (NumberFormatException e) {
        } catch (SQLException e) {
        } catch (CloneNotSupportedException e) {

        }
        
    }

    private int getTime(String time) {
        String[] times; 
        times = time.split(":");
        time = "";
        for (String string : times) {
            time += string;
        }
        return Integer.parseInt(time);
    }

    private void setReachedTime(String destinaionTime) {
        int hour = Integer.parseInt(destinaionTime.substring(0, 2)) % 24;
        String min = destinaionTime.substring(3);
        currentTrainSchedule.setDestinationTime(hour + ":" + min);
    }

    private void setDepartedTime(String sourceTime) {
        int hour = Integer.parseInt(sourceTime.substring(0, 2)) % 24;
        String min = sourceTime.substring(3);
        currentTrainSchedule.setSourceTime(hour + ":" + min);
    }

}