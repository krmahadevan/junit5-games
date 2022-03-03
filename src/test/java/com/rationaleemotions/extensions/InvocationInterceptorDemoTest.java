package com.rationaleemotions.extensions;

import com.rationaleemotions.extensions.InvocationInterceptorDemoTest.EavesdroppingListener;
import java.lang.reflect.Method;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.DynamicTestInvocationContext;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@ExtendWith(EavesdroppingListener.class)
public class InvocationInterceptorDemoTest {

  @BeforeEach
  public void beforeEach(TestInfo info) {
    info.getTestMethod().ifPresent(t -> System.err.println("beforeEach for @Test " + t.getName()));
  }

  @AfterEach
  public void afterEach(TestInfo info) {
    info.getTestMethod().ifPresent(t -> System.err.println("afterEach for @Test " + t.getName()));
  }

  @BeforeAll
  static void beforeAll() {
    System.err.println("beforeAll");
  }

  @AfterAll
  static void afterAll() {
    System.err.println("afterAll");
  }

  @DisplayName("Kung-Fu Panda")
  @Test
  public void myFancyTestMethod() {
    System.err.println("myFancyTestMethod");
  }

  @TestFactory
  public Stream<DynamicTest> iProduceDynamicTests() {
    return Stream.of(DynamicTest.dynamicTest("i-am-a-dynamic-test",
        new Executable() {
          @Override
          public void execute() {
          System.err.println("[DynamicTest] I am what a dynamic test does");
          }

          @Override
          public String toString() {
            return "[DynamicTest] I am what a dynamic test will do";
          }
        }
    ));
  }

  @RepeatedTest(value = 2)
  public void iRunMultipleTimes() {
  }

  @ParameterizedTest
  @CsvSource({"John-Rambo", "Terminator"})
  public void iAmADataDrivenTest(String name) {
    System.err.println("[ParameterizedTest] You are " + name);
  }

  public static class EavesdroppingListener implements InvocationInterceptor {

    @Override
    public void interceptBeforeAllMethod(Invocation<Void> invocation,
        ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext)
        throws Throwable {
      System.err.println(
          "[Interceptor] @BeforeAll method " + invocationContext.getExecutable().getName());
      invocation.proceed();
    }

    @Override
    public void interceptAfterAllMethod(Invocation<Void> invocation,
        ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext)
        throws Throwable {
      System.err.println(
          "[Interceptor] @AfterAll method " + invocationContext.getExecutable().getName());
      invocation.proceed();
    }

    @Override
    public void interceptBeforeEachMethod(Invocation<Void> invocation,
        ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext)
        throws Throwable {
      System.err.println(
          "[Interceptor] @BeforeEach method " + invocationContext.getExecutable().getName());
      invocation.proceed();
    }

    @Override
    public void interceptAfterEachMethod(Invocation<Void> invocation,
        ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext)
        throws Throwable {
      System.err.println(
          "[Interceptor] @AfterEach method " + invocationContext.getExecutable().getName());
      invocation.proceed();
    }

    @Override
    public void interceptTestMethod(Invocation<Void> invocation,
        ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext)
        throws Throwable {
      System.err.println(
          "[Interceptor] @Test method " + invocationContext.getExecutable().getName());
      invocation.proceed();
    }

    @Override
    public <T> T interceptTestFactoryMethod(Invocation<T> invocation,
        ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext)
        throws Throwable {
      String text = "@" + TestFactory.class.getSimpleName();
      System.err.println(
          "[Interceptor] " + text + " method " + invocationContext.getExecutable().getName());
      return invocation.proceed();
    }

    @Override
    public void interceptTestTemplateMethod(Invocation<Void> invocation,
        ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext)
        throws Throwable {
      String text = "";
      Method m = invocationContext.getExecutable();
      if (m.getAnnotation(RepeatedTest.class) != null) {
        text = "@" + RepeatedTest.class.getSimpleName();
      } else if (m.getAnnotation(ParameterizedTest.class) != null) {
        text = "@" + ParameterizedTest.class.getSimpleName();
      }
      System.err.println(
          "[Interceptor] " + text + " method " + invocationContext.getExecutable().getName());
      invocation.proceed();
    }

    @Override
    public void interceptDynamicTest(Invocation<Void> invocation,
        DynamicTestInvocationContext invocationContext, ExtensionContext extensionContext)
        throws Throwable {
      System.err.println(
          "[Interceptor] Dynamic test method " + invocationContext.getExecutable());
      invocation.proceed();
    }
  }
}
