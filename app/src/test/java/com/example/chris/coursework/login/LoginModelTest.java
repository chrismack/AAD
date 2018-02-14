package com.example.chris.coursework.login;

import android.content.Context;

import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.data.DAO;
import com.example.chris.coursework.data.entities.Therapist;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Chris on 12/02/2018.
 */


public class LoginModelTest {

    @Mock DAO mockDao;
    @Mock LoginPresenter presenter;
    @Mock MainModel mockMainModel;
    @Mock LoginView mockLoginView;

    Therapist therapist;
    LoginModel loginModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        loginModel = new LoginModel(presenter);

        therapist = new Therapist();
        therapist.setEmail("foo@example.com");
        therapist.setPassword("c729eaf54df19d13da8a6b99c6c977e5e804f60f398f36f5efae3031d15b6a23");
        therapist.setSalt("1");
        therapist.setFirstName("Chris");
        therapist.setLastName("Mack");
    }

    @After
    public void tearDown() {
        therapist = null;
    }

    @Test
    public void correctLogin() throws Exception {
        String email = "foo@example.com";
        String password = "hello";

        Therapist expected = therapist;

        Therapist result = Whitebox.invokeMethod(loginModel, "comparePasswords", therapist, new String(password));

        assertEquals(expected, result);
    }

    @Test
    public void incorrectPassword() throws Exception {
        String email = "foo@example.com";
        String password = "dave";

        Therapist expected = null;

        Therapist result = Whitebox.invokeMethod(loginModel, "comparePasswords", therapist, new String(password));

        assertEquals(expected, result);
    }

    @Test
    public void incorrectSalt() throws Exception {
        String email = "foo@example.com";
        String password = "hello";
        String newSalt = "2";
        therapist.setSalt(newSalt);

        Therapist expected = null;

        Therapist result = Whitebox.invokeMethod(loginModel, "comparePasswords", therapist, new String(password));

        therapist.setSalt("1");

        assertEquals(expected, result);

    }

    @Test
    public void incorrectPasswordAndSalt() throws Exception {
        String email = "foo@example.com";
        String password = "dave";
        String newSalt = "2";
        therapist.setSalt(newSalt);

        Therapist expected = null;

        Therapist result = Whitebox.invokeMethod(loginModel, "comparePasswords", therapist, new String(password));

        therapist.setSalt("1");

        assertEquals(expected, result);
    }

}
