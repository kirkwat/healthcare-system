package healthcaresystem.model;

public class LabTest {
    private int testID;
    private int patientID;
    private String testType;
    private String date;
    private String result;

    /**
     * @param testID
     * @param patientID
     * @param testType
     * @param date
     * @param result
     */
    public LabTest(int testID, int patientID, String testType, String date, String result) {
        this.testID = testID;
        this.patientID = patientID;
        this.testType = testType;
        this.date = date;
        this.result = result;
    }

    /**
     * @return the testID
     */
    public int getTestID() {
        return testID;
    }

    /**
     * @return the patientID
     */
    public int getPatientID() {
        return patientID;
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
