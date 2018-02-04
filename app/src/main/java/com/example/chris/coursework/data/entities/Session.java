package com.example.chris.coursework.data.entities;

/**
 * Created by Chris on 04/02/2018.
 */

public class Session {
    private int sessionId;
    private int testId;
    private int dm_timeTaken;
    private int dm_falsePos;
    private int dm_trueNeg;
    private int smd_timeTaken;
    private int smd_correctCars;
    private int smd_correctLorries;
    private int smc_timeTaken;
    private int smc_redCars;
    private int smc_blueCars;
    private int rsr_timeTaken;
    private int rsr_correctSigns;

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
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

    public int getTmt_timeTaken() {
        return tmt_timeTaken;
    }

    public void setTmt_timeTaken(int tmt_timeTaken) {
        this.tmt_timeTaken = tmt_timeTaken;
    }

    private int tmt_timeTaken;
}
