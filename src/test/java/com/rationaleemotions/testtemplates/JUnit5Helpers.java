package com.rationaleemotions.testtemplates;

import com.rationaleemotions.testtemplates.BrowserTest.OS;
import com.rationaleemotions.testtemplates.DriverFactory.BrowserDriver;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

public class JUnit5Helpers {

  public static class BrowserAwareTestTemplateInvocationContext implements
      TestTemplateInvocationContext {

    private final String flavor;

    public BrowserAwareTestTemplateInvocationContext(String flavor) {
      this.flavor = flavor;
    }

    @Override
    public String getDisplayName(int invocationIndex) {
      return flavor;
    }

    @Override
    public List<Extension> getAdditionalExtensions() {
      return Arrays.asList(
          new PlatformCondition(),
          new BrowserDriverResolver()
      );
    }
  }

  public static class BrowserDriverResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
        ExtensionContext extensionContext) throws ParameterResolutionException {
      return parameterContext.getParameter().getType().equals(BrowserDriver.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
        ExtensionContext extensionContext) throws ParameterResolutionException {
      AtomicReference<BrowserDriver> driver = new AtomicReference<>();
      extensionContext.getTestMethod().ifPresent(m -> {
        BrowserTest bt = m.getAnnotation(BrowserTest.class);
        if (bt == null) {
          driver.set(null);
        } else {
          BrowserDriver bd = DriverFactory.createBrowserDriver(bt.flavor());
          driver.set(bd);
        }
      });
      return driver.get();
    }
  }

  public static class PlatformCondition implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
      Optional<Method> tm = context.getTestMethod();
      OS os = OS.determineCurrentOs();
      BrowserTest browserTest = null;
      if (tm.isPresent()) {
        browserTest = tm.get().getAnnotation(BrowserTest.class);
        if (browserTest != null) {
          OS provided = browserTest.flavor().getPlatform();
          if (provided.isCurrentOs()) {
            return ConditionEvaluationResult.enabled("Enabled for the platform " + os.name());
          }
        }

      }
      String browser = "";
      if (browserTest != null) {
        browser = browserTest.flavor().name();
      }
      return ConditionEvaluationResult.disabled("Current Platform "
          + os.name() + " is NOT supported for browser " + browser);
    }

  }

  public static class BrowserFlavorProvider implements TestTemplateInvocationContextProvider {

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
      AtomicBoolean flag = new AtomicBoolean(false);
      context.getTestMethod()
          .ifPresent(m -> flag.set(m.getAnnotation(BrowserTest.class) != null));
      return flag.get();
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      Optional<Method> method = context.getTestMethod();
      if (method.isPresent()) {
        StringBuilder flavor = new StringBuilder();
        BrowserTest bt = method.get().getAnnotation(BrowserTest.class);
        if (bt != null) {
          flavor.append(bt.flavor().name());
        }

        return Stream.of(new BrowserAwareTestTemplateInvocationContext(flavor.toString()));
      }
      return null;
    }
  }
}
