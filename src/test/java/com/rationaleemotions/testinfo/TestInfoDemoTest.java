package com.rationaleemotions.testinfo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class TestInfoDemoTest {

  @Test
  @DisplayName("See-This-Is-Your-Display-Name")
  public void testMethod(TestInfo testInfo) {
    testInfo.getTestMethod().ifPresent(m -> System.err.println("Test Name " + m.getName()));
    System.err.println("Display name : " + testInfo.getDisplayName());
  }
}
