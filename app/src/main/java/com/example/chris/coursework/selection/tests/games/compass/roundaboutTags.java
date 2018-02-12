package com.example.chris.coursework.selection.tests.games.compass;

/**
 * Created by Fred on 10/02/2018.
 */

public class roundaboutTags {
    private int tagID;
    private String firstDirection;
    private String secondDirection;

    public void setTagID(int id)
    {
        this.tagID = id;
    }
    public void setFirstDirection(String direction)
    {
        this.firstDirection = direction;
    }
    public void setSecondDirection(String direction)
    {
        this.secondDirection = direction;
    }
    public int getTagID()
    {
        return this.tagID;
    }
    public String getFirstDirection()
    {
        return this.firstDirection;
    }
    public String getSecondDirection()
    {
        return this.secondDirection;
    }
}
