package application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserDriverTest {
  UserDriverApplication userDriver;
  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  @BeforeEach
  void setUp() throws Exception {
    userDriver = new UserDriverApplication();
  }

  @AfterEach
  void tearDown() throws Exception {
    userDriver = null;
  }

  /**
   * Tests to be sure the InvalidUsername exception is thrown as expected in login()
   */
  @Test
  void test001_InvalidUsername_login() {
    try {
      userDriver.login("eheying");
      fail("InvalidUsername not thrown in test001.");
    } catch (InvalidUsername e) {
      // do nothing, this is expected
    } catch (Exception e) {
      fail("Unexpected exception in test001.");
    }
  }
  
  /**
   * Tests to be sure the login works as expected with a valid username
   */
  @Test
  void test002_valid_login() {
    try {
      if(!userDriver.login("ben@wisc.edu")) // should return !true
        fail("Should be able to log in a vaild user (test002).");
    } catch (InvalidUsername e) {
      fail("InvalidUsername thrown in test002.");
    } catch (Exception e) {
      fail("Unexpected exception in test002.");
    }
  }
  
  /**
   * Tests to be sure the login fails if another user is already logged in
   */
  @Test
  void test003_valid_login_but_already_logged_in() {
    try {
      if(userDriver.login("ben@wisc.edu")) {
        if(userDriver.login("ccpatel2@wisc.edu"))
          fail("Should not be able to log in 2 users at the same time (test003");
      }
    } catch (InvalidUsername e) {
      fail("InvalidUsername thrown in test003.");
    } catch (Exception e) {
      fail("Unexpected exception in test003.");
    }
  }

  /**
   * Tests to be sure the UserExists exception is thrown as expected in register()
   */
  @Test
  void test004_UserExists_register() {
    try {
      userDriver.register("ben@wisc.edu");
      fail("UserExists not thrown in test004.");
    } catch (UserExists e) {
      // do nothing, this is expected
    } catch (Exception e) {
      fail("Unexpected exception in test004.");
    }
  }

  /**
   * Tests to be sure the user is logged out properly by checking if a new user can log in
   */
  @Test
  void test005_valid_logout() {
    try {
      userDriver.login("ben@wisc.edu");
      userDriver.logout();
      if(!userDriver.login("ccpatel2@wisc.edu")) // should return !true
        fail("Unable to log in new user after one was logged out in test005.");
    } catch(InvalidUsername e) {
      fail("InvalidUsername thrown inproperly at test005.");
    }catch (Exception e) {
      fail("Unexpected exception in test005.");
    }
  }

  /**
   * Tests to be sure a new user can be added
   */
  @Test
  void test006_add_new_user() {
    try {
      userDriver.login("ben@wisc.edu");
      userDriver.logout();
      if(!userDriver.login("ccpatel2@wisc.edu")) // should return !true
        fail("Unable to log in new user after one was logged out in test005.");
    } catch(InvalidUsername e) {
      fail("InvalidUsername thrown inproperly at test005.");
    }catch (Exception e) {
      fail("Unexpected exception in test005.");
    }
  }
}
