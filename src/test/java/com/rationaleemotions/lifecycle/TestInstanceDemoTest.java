package com.rationaleemotions.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(value = Lifecycle.PER_CLASS) // <-- This causes an object to be shared by all
// test methods of the same class.

//@TestInstance(value = Lifecycle.PER_METHOD) // <-- This is by default and causes new object to be
// instantiated for every test method
public class TestInstanceDemoTest {

  @Test
  @DisplayName("first-test-method")
  public void testMethod() {
    System.err.println("testMethod() : Current Object is " + hashCode());
  }

  @Test
  @DisplayName("second-test-method")
  public void anotherTestMethod() {
    System.err.println("anotherTestMethod() : Current Object is " + hashCode());
  }

}
