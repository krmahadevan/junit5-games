package com.rationaleemotions.extensions;

import com.rationaleemotions.extensions.CallbackDemoTest.Commentator;
import java.lang.reflect.Method;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

@ExtendWith(Commentator.class)
public class CallbackDemoTest {

  @BeforeAll
  public static void beforeAll() {
  }

  @AfterAll
  public static void afterAll() {
  }

  @BeforeEach
  public void beforeEach() {
  }

  @AfterEach
  public void afterEach() {
  }

  @Test
  public void testMethod() {
  }

  public static class Commentator implements BeforeAllCallback, AfterAllCallback,
      BeforeEachCallback, AfterEachCallback, BeforeTestExecutionCallback,
      AfterTestExecutionCallback {

    @Override
    public void afterAll(ExtensionContext context) {
      context.getElement().ifPresent(t -> {
        if (t instanceof Class) {
          System.err.println(
              "[Commentator] afterAll() called for Class: " + ((Class<?>) t).getSimpleName());
        }
      });
    }

    @Override
    public void afterEach(ExtensionContext context) {
      context.getElement().ifPresent(t -> {
        if (t instanceof Method) {
          System.err.println(
              "[Commentator] afterEach() called for method " + ((Method) t).getName());
        }
      });
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
      context.getTestMethod()
          .ifPresent(t -> System.err.println(
              "[Commentator] afterTestExecution() called for test " + t.getName()));
    }

    @Override
    public void beforeAll(ExtensionContext context) {
      context.getElement().ifPresent(t -> {
        if (t instanceof Class) {
          System.err.println(
              "[Commentator] beforeAll() called for Class: " + ((Class<?>) t).getSimpleName());
        }
      });
    }

    @Override
    public void beforeEach(ExtensionContext context) {
      context.getElement().ifPresent(t -> {
        if (t instanceof Method) {
          System.err.println(
              "[Commentator] beforeEach() called for method " + ((Method) t).getName());
        }
      });
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
      context.getTestMethod()
          .ifPresent(t -> System.err.println(
              "[Commentator] beforeTestExecution() called for test " + t.getName()));
    }
  }

}
