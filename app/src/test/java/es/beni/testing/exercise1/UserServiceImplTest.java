package es.beni.testing.exercise1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock UserDAO userDAO;

    @Mock SecurityService securityService;

    private UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl(userDAO, securityService);
    }

    @Test
    public void shouldSaveUserInDatabase() throws Exception {
        User user = Mockito.mock(User.class);

        when(user.getPassword()).thenReturn("12345");

        when(securityService.md5(user.getPassword())).thenReturn("ABCDE");

        doNothing().when(user).setPassword("ABCDE");

        doNothing().when(userDAO).updateUser(user);

        userService.assignPassword(user);

        verify(user).getPassword();
        verify(securityService).md5(user.getPassword());
        verify(user).setPassword("ABCDE");
        verify(userDAO).updateUser(user);
    }
}