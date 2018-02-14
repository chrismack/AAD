package com.example.chris.coursework;

import com.example.chris.coursework.data.entities.Attending;
import com.example.chris.coursework.data.entities.Patient;
import com.example.chris.coursework.data.entities.Session;
import com.example.chris.coursework.data.entities.Therapist;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Chris on 13/02/2018.
 */

public class MainModelTest {

    private MainModel model;
    private Therapist therapist;
    private Patient patient;
    private Session session;
    private Attending attending;

    @Before
    public void setup() {
        model = new MainModel(null);

        therapist = new Therapist();
        therapist.setEmail("foo@example.com");
        therapist.setPassword("c729eaf54df19d13da8a6b99c6c977e5e804f60f398f36f5efae3031d15b6a23");
        therapist.setSalt("1");
        therapist.setFirstName("Chris");
        therapist.setLastName("Mack");

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

        session = new Session();
        session.setTmt_timeTakenA(100);
        session.setTmt_timeTakenB(150);

        attending = new Attending();
        attending.setPatient(patient);
        attending.setTherapist(therapist);
        attending.setSession(session);
    }

    @After
    public void tearDown() {
        model = null;
    }

    @Test
    public void setGetTherapist() throws Exception {
        Therapist expected = therapist;

        model.setTherapist(therapist);
        Therapist result = model.getTherapist();

        assertEquals(expected.getEmail(), result.getEmail());
        assertEquals(expected.getPassword(), result.getPassword());
        assertEquals(expected.getSalt(), result.getSalt());
        assertEquals(expected.getFirstName(), result.getFirstName());
        assertEquals(expected.getLastName(), result.getLastName());
    }

    @Test
    public void setGetPatient() throws Exception {
        Patient expected = patient;

        model.setCurrentPatient(patient);
        Patient result = model.getCurrentPatient();

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
    public void setGetSession() throws Exception {
        Session expected = session;

        model.setSession(session);
        Session result = model.getCurrentSession();

        assertEquals(expected.getTmt_timeTakenA(), result.getTmt_timeTakenA());
        assertEquals(expected.getTmt_timeTakenB(), result.getTmt_timeTakenB());
    }

    @Test
    public void setGetAttending() throws Exception {
        Attending expected = attending;

        model.setAttending(attending);
        Attending result = model.getAttending();

        assertEquals(expected, result);
    }

}
