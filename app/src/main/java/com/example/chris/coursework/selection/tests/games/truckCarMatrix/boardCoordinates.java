package com.example.chris.coursework.selection.tests.games.truckCarMatrix;

/**
 * Created by Fred on 09/02/2018.
 */

public class boardCoordinates {
    private int viewID;
    private int xValue;
    private int yValue;
    private String firstDirection;
    private String secondDirection;

    public void setViewID(int ID){
        this.viewID = ID;
    }
    public void setX(int x){
        this.xValue = x;
    }
    public void setY(int y){
        this.yValue = y;
    }
    public void setFirstDirection(String x){
        this.firstDirection = x;
    }
    public void setSecondDirection(String x){
        this.secondDirection = x;
    }
    public int getViewID(){
        return this.viewID;
    }
    public int getXValue(){
        return this.xValue;
    }
    public int getYValue(){
        return this.yValue;
    }
    public String getFirstDirection(){
        return this.firstDirection;
    }
    public String getSecondDirection(){
        return this.secondDirection;
    }
}
