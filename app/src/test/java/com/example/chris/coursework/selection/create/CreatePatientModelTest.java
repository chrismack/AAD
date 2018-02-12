package com.example.chris.coursework.selection.create;

import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.common.Utils;
import com.example.chris.coursework.data.entities.Patient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.doReturn;

/**
 * Created by Chris on 12/02/2018.
 */

public class CreatePatientModelTest {

    private CreatePatientModel model;
    private Patient patient;

    @Mock CreatePatientPresenter presenter;
    @Mock MainModel mockMainModel;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        doReturn(null).when(mockMainModel).getDAO();

        model = new CreatePatientModel(presenter);

        patient = new Patient();
        patient.setId(1);
        patient.setFirstName("Chris");
        patient.setLastName("Mack");
        patient.setDob("12-11-1995");
        patient.setNhsNumber("123456789");
        patient.setAddress1("1 test street");
        patient.setAddress2("test town");
        patient.setCity("Testington");
        patient.setPostcode("TWTWTW");
    }

    @After
    public void tearDown() {
        model = null;
    }

    @Test
    public void validPatient() throws Exception {
        String expected = "";
        String result = model.checkPatientErrors(patient);

        assertEquals(expected, result);
    }

    @Test
    public void firstNameNotPresent() throws Exception {
        String expected = "First name is required\n";
        patient.setFirstName("");

        String result = model.checkPatientErrors(patient);

        patient.setFirstName("Chris");

        assertEquals(expected, result);
    }

    @Test
    public void lastNameNotPresent() throws Exception {
        String expected = "Last name is required\n";
        patient.setLastName("");

        String result = model.checkPatientErrors(patient);

        patient.setLastName("Mack");

        assertEquals(expected, result);
    }

    @Test
    public void dobNotPresent() throws Exception {
        String expected = "Date of Birth is required\n";
        patient.setDob("");

        String result = model.checkPatientErrors(patient);

        patient.setDob("12-11-1995");

        assertEquals(expected, result);
    }

    @Test
    public void nhsNumberNotPresent() throws Exception {
        String expected = "";
        patient.setNhsNumber("");

        String result = model.checkPatientErrors(patient);

        patient.setNhsNumber("123456789");

        assertEquals(expected, result);

    }

    @Test
    public void address1NotPresent() throws Exception {
        String expected = "";
        patient.setAddress1("");

        String result = model.checkPatientErrors(patient);

        patient.setAddress1("1 test street");

        assertEquals(expected, result);
    }

    @Test
    public void address2NotPresent() throws Exception {
        String expected = "";
        patient.setAddress2("");

        String result = model.checkPatientErrors(patient);

        patient.setAddress2("test town");

        assertEquals(expected, result);
    }

    @Test
    public void noAddressPresent() throws Exception {
        String expected = "At least one address line is required is required\n";
        patient.setAddress1("");
        patient.setAddress2("");

        String result = model.checkPatientErrors(patient);

        patient.setAddress1("1 test street");
        patient.setAddress2("test town");

        assertEquals(expected, result);
    }

    @Test
    public void cityNotPresent() throws Exception {
        String expected = "";
        patient.setCity("");

        String result = model.checkPatientErrors(patient);

        patient.setCity("testington");

        assertEquals(expected, result);
    }

    @Test
    public void postcodeNotPresent() throws Exception {
        String expected = "Postcode is required\n";
        patient.setPostcode("");

        String result = model.checkPatientErrors(patient);

        patient.setPostcode("TWTWTW");

        assertEquals(expected, result);
    }

    @Test
    public void patientIsNull() throws Exception {
        String expected = "Patient must be entered!";

        String result = model.checkPatientErrors(null);

        assertEquals(expected, result);
    }

    @Test
    public void patientIsEmpty() throws Exception {
        String expected = "First name is required\n" +
                            "Last name is required\n" +
                            "Date of Birth is required\n" +
                            "At least one address line is required is required\n" +
                            "Postcode is required\n";

        String result = model.checkPatientErrors(new Patient());

        assertEquals(expected, result);
    }

    @Test
    public void patientNamesEmpty() throws Exception {
        String expected = "First name is required\n" +
                            "Last name is required\n";

        patient.setFirstName("");
        patient.setLastName("");

        String result = model.checkPatientErrors(patient);

        patient.setFirstName("Chris");
        patient.setLastName("Mack");

        assertEquals(expected, result);
    }

    @Test
    public void firstNameSpaceOnly() throws Exception {
        String expected = "First name is required\n";

        patient.setFirstName(" ");

        String result = model.checkPatientErrors(patient);

        patient.setFirstName("Chris");

        assertEquals(expected, result);
    }

    @Test
    public void buildValidPatient() throws Exception {
        Patient expected = patient;
        Patient result = model.buildPatient("Chris", "Mack", Utils.createCalendarFrom("12-11-1995"), "123456789", "1 test street", "test town", "Testington", "TWTWTW");

        assertEquals(expected.getFirstName(), result.getFirstName());
        assertEquals(expected.getLastName(), result.getLastName());
        assertEquals(expected.getDob(), result.getDob());
        assertEquals(expected.getAddress1(), result.getAddress1());
        assertEquals(expected.getAddress2(), result.getAddress2());
        assertEquals(expected.getNhsNumber(), result.getNhsNumber());
        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getPostcode(), result.getPostcode());
    }

    @Test
    public void buildEmptyPatient() throws Exception {
        Patient expected = new Patient();
        Patient result = model.buildPatient("", "", null, "", "", "", "", "");

        assertEquals(expected.getFirstName(), result.getFirstName());
        assertEquals(expected.getLastName(), result.getLastName());
        assertEquals(expected.getDob(), result.getDob());
        assertEquals(expected.getAddress1(), result.getAddress1());
        assertEquals(expected.getAddress2(), result.getAddress2());
        assertEquals(expected.getNhsNumber(), result.getNhsNumber());
        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getPostcode(), result.getPostcode());
    }
}
