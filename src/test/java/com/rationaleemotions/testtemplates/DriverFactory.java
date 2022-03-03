package com.rationaleemotions.testtemplates;

import com.rationaleemotions.testtemplates.BrowserTest.Flavor;

public class DriverFactory {

  @FunctionalInterface
  interface BrowserDriver {
    String driverName();
  }

  public static BrowserDriver createBrowserDriver(Flavor flavor) {
    switch (flavor) {
      case FIREFOX:
        return () -> "firefox";
      case IE:
        return () -> "internet explorer";
      case CHROME:
        return () -> "chrome";
      default:
        return () -> "safari";
    }
  }
}
