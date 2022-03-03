package com.rationaleemotions.extensions;

import com.rationaleemotions.extensions.LifecycleMethodExecutionExceptionHandlerDemoTest.IGobbleExceptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.LifecycleMethodExecutionExceptionHandler;

// Even though the configuration methods are throwing exceptions, there will be no failures
// because we are having listeners which are gobbling up all the exceptions
@ExtendWith(IGobbleExceptions.class)
public class LifecycleMethodExecutionExceptionHandlerDemoTest {

  @BeforeAll
  static void beforeAll() {
    throw new IllegalArgumentException("bad argument");
  }

  @BeforeEach
  public void beforeEach() {
    throw new IllegalStateException("bad state");
  }

  @DisplayName("my-friendly-test")
  @Test
  public void testMethod() {
  }

  public static class IGobbleExceptions implements LifecycleMethodExecutionExceptionHandler {

    @Override
    public void handleBeforeAllMethodExecutionException(ExtensionContext context,
        Throwable throwable) {
      System.err.println("Exception that was thrown was " + throwable.getClass().getName());
    }

    @Override
    public void handleBeforeEachMethodExecutionException(ExtensionContext context,
        Throwable throwable) {
      System.err.println("Exception that was thrown was " + throwable.getClass().getName());
    }
  }
}
