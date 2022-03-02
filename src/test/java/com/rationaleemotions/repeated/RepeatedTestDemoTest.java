package com.rationaleemotions.repeated;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS) // All repetitions of the test will share the same object.
//@TestInstance(Lifecycle.PER_METHOD) // Every repetition will get its own object and so counter will always
//be "1"
public class RepeatedTestDemoTest {

  private final AtomicInteger counter = new AtomicInteger(0);

  @DisplayName("repeater-test")
  @RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
  public void testMethod() {
    System.err.println("Iteration #" + counter.incrementAndGet() + " on object " + hashCode());
  }
}
