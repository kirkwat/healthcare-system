package com.healthcaresystem.model;

public class LabTest {
    private int testId;
    private int patientId;
    private String testType;
    private String date;
    private String result;

    /**
     * @param testId
     * @param patientId
     * @param testType
     * @param date
     * @param result
     */
    public LabTest(int testId, int patientId, String testType, String date, String result) {
        this.testId = testId;
        this.patientId = patientId;
        this.testType = testType;
        this.date = date;
        this.result = result;
    }

    /**
     * @return the testId
     */
    public int getTestId() {
        return testId;
    }

    /**
     * @return the patientId
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * @return the testType
     */
    public String getTestType() {
        return testType;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }
}
