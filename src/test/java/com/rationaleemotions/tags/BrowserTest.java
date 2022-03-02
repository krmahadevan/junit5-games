package com.rationaleemotions.tags;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class BrowserTest {

  @Test
  @DisplayName("Launch Website") // <-- This will be the test name in test reports
  @Tag("p1")
  public void launchBrowser() {
    System.err.println("Website launched");
  }
}
