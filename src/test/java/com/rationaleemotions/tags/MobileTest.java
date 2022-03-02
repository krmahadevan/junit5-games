package com.rationaleemotions.tags;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class MobileTest {

  @Test
  @DisplayName("Launch App") // <-- This will be the test name in test reports
  @Tag("p1")
  public void launchApp() {
    System.err.println("Mobile App Launched");
  }
}
