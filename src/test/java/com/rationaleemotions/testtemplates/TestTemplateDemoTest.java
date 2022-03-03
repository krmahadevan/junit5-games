package com.rationaleemotions.testtemplates;

import com.rationaleemotions.testtemplates.BrowserTest.Flavor;
import com.rationaleemotions.testtemplates.DriverFactory.BrowserDriver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

public class TestTemplateDemoTest {

  @DisplayName("Admin-Portal(Firefox)")
  @TestTemplate
  @BrowserTest(flavor = Flavor.FIREFOX)
  @ExtendWith(JUnit5Helpers.BrowserFlavorProvider.class)
  public void testAdminPortal(BrowserDriver driver) {
    System.err.println(driver.driverName());
  }

  @DisplayName("Main-Portal(IE)")
  @TestTemplate
  @BrowserTest(flavor = Flavor.IE)
  @ExtendWith(JUnit5Helpers.BrowserFlavorProvider.class)
  public void testMainPortal(BrowserDriver driver) {
    System.err.println("Browser flavor = " + driver.driverName());
  }

  @DisplayName("Main-Portal(Safari)")
  @TestTemplate
  @BrowserTest(flavor = Flavor.SAFARI)
  @ExtendWith(JUnit5Helpers.BrowserFlavorProvider.class)
  public void testCustomerSupportPortal(BrowserDriver driver) {
    System.err.println("Browser flavor = " + driver.driverName());
  }
}
