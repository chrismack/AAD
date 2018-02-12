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
        setFirstDirection1(x);
        this.xValue = x;
    }
    public void setY(int y){
        setSecondDirection1(y);
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

    private void setFirstDirection1(int x){
        if (x == 310){
            this.setFirstDirection("E");
        }
        else if (x == 620){
            this.setFirstDirection("N");
        }
        else if (x == 930){
            this.setFirstDirection("W");
        }
        else if (x == 1240){
            this.setFirstDirection("S");
        }
    }
    private void setSecondDirection1(int y){
        if (y == 486){
            this.setSecondDirection("N");
        }
        else if (y == 796){
            this.setSecondDirection("S");
        }
        else if (y == 1106){
            this.setSecondDirection("E");
        }
        else if (y == 1416){
            this.setSecondDirection("W");
        }
    }
}
