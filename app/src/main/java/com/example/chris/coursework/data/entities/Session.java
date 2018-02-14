package com.example.chris.coursework.data.entities;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Chris on 04/02/2018.
 */

public class Session {


    private int sessionId;
    // Dot Matrix
    private int dm_timeTaken = 0;
    private int dm_falsePos = 0;
    private int dm_trueNeg = 0;

    // Square Matrices Direction
    private int smd_timeTaken = 0;
    private int smd_correctCars = 0;
    private int smd_correctLorries = 0;

    // Square Matrices Compass
    private int smc_timeTaken = 0;
    private int smc_redCars = 0;
    private int smc_blueCars = 0;

    // Road sign recognition
    private int rsr_timeTaken = 0;
    private int rsr_correctSigns = 0;

    // Trail making test
    private long tmt_timeTakenA = 0;
    private long tmt_timeTakenB = 0;

    private Date creationDate = null;
    private Date lastAttemptDate = null;



    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getDm_timeTaken() {
        return dm_timeTaken;
    }

    public void setDm_timeTaken(int dm_timeTaken) {
        this.dm_timeTaken = dm_timeTaken;
    }

    public int getDm_falsePos() {
        return dm_falsePos;
    }

    public void setDm_falsePos(int dm_falsePos) {
        this.dm_falsePos = dm_falsePos;
    }

    public int getDm_trueNeg() {
        return dm_trueNeg;
    }

    public void setDm_trueNeg(int dm_trueNeg) {
        this.dm_trueNeg = dm_trueNeg;
    }

    public int getSmd_timeTaken() {
        return smd_timeTaken;
    }

    public void setSmd_timeTaken(int smd_timeTaken) {
        this.smd_timeTaken = smd_timeTaken;
    }

    public int getSmd_correctCars() {
        return smd_correctCars;
    }

    public void setSmd_correctCars(int smd_correctCars) {
        this.smd_correctCars = smd_correctCars;
    }

    public int getSmd_correctLorries() {
        return smd_correctLorries;
    }

    public void setSmd_correctLorries(int smd_correctLorries) {
        this.smd_correctLorries = smd_correctLorries;
    }

    public int getSmc_timeTaken() {
        return smc_timeTaken;
    }

    public void setSmc_timeTaken(int smc_timeTaken) {
        this.smc_timeTaken = smc_timeTaken;
    }

    public int getSmc_redCars() {
        return smc_redCars;
    }

    public void setSmc_redCars(int smc_redCars) {
        this.smc_redCars = smc_redCars;
    }

    public int getSmc_blueCars() {
        return smc_blueCars;
    }

    public void setSmc_blueCars(int smc_blueCars) {
        this.smc_blueCars = smc_blueCars;
    }

    public int getRsr_timeTaken() {
        return rsr_timeTaken;
    }

    public void setRsr_timeTaken(int rsr_timeTaken) {
        this.rsr_timeTaken = rsr_timeTaken;
    }

    public int getRsr_correctSigns() {
        return rsr_correctSigns;
    }

    public void setRsr_correctSigns(int rsr_correctSigns) {
        this.rsr_correctSigns = rsr_correctSigns;
    }

    public long getTmt_timeTakenA() {
        return tmt_timeTakenA;
    }

    public void setTmt_timeTakenA(long tmt_timeTakenA) {
        this.tmt_timeTakenA = tmt_timeTakenA;
    }

    public long getTmt_timeTakenB() {
        return tmt_timeTakenB;
    }

    public void setTmt_timeTakenB(long tmt_timeTakenB) {
        this.tmt_timeTakenB = tmt_timeTakenB;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastAttemptDate() {
        return lastAttemptDate;
    }

    public void setLastAttemptDate(Date lastAttemptDate) {
        this.lastAttemptDate = lastAttemptDate;
    }

    public int getSessionId() {
        return sessionId;
    }

    @Override
    public String toString() {
        return "Session Id: " + sessionId + "" + "\n" +
                "Dot Cancellation Time; " + dm_timeTaken + "" + "\n" +
                "Dot Cancellation False Positive: " + dm_falsePos + "" + "\n" +
                "Dot Cancellation Errors: " + dm_trueNeg + "" + "\n" +
                "Square Matrices Direction Time Taken: " + smd_timeTaken + "" + "\n" +
                "Square Matrices Direction Cars: " + smd_correctCars + "" + "\n" +
                "Square Matrices Direction Lorries: " + smd_correctLorries + "" + "\n" +
                "Square Matrices Compass Time Taken: " + smc_timeTaken + "" + "\n" +
                "Square Matrices Compass RedCars: " + smc_redCars + "" + "\n" +
                "Square Matrices Compass BlueCars: " + smc_blueCars + "" + "\n" +
                "Road Sign Recognition Time Taken: " + rsr_timeTaken + "" + "\n" +
                "Road Sign Recognition Score: " + rsr_correctSigns + "" + "\n" +
                "Trail Making Test A Time Taken" + tmt_timeTakenA + "" + "\n" +
                "Trail Making Test B Time Taken" + tmt_timeTakenB + "" + "\n" +
                "Session Created: " + creationDate.toString() + "" + "\n" +
                "Last Attempt: " + lastAttemptDate.toString() + "" + "\n";
    }
}
