package com.rationaleemotions.extensions;

import static org.assertj.core.api.Assertions.assertThat;

import com.rationaleemotions.extensions.TestInstancePostProcessorDemoTest.ClassLevelObjectProducedAnnouncer;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;


@TestInstance(value = Lifecycle.PER_CLASS)
@ExtendWith(ClassLevelObjectProducedAnnouncer.class)
// If @ExtendWith is added on top of the method, then this
//extension for some reason is not able to detect the lifecycle.
public class TestInstancePostProcessorDemoTest implements CounterAware {

  private AtomicInteger counter;

  @Test
  public void myTestMethod() {
    assertThat(counter.get()).isEqualTo(1);
  }

  @Override
  public void setCounter(AtomicInteger counter) {
    this.counter = counter;
  }

  public static class ClassLevelObjectProducedAnnouncer implements TestInstancePostProcessor {

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
      context.getTestInstanceLifecycle()
          .ifPresent(a -> System.err.println("Life-cycle is " + a.name()));

      //Set a counter if its of type "CounterAware"
      if (testInstance instanceof CounterAware) {
        AtomicInteger counter = new AtomicInteger(1);
        ((CounterAware) testInstance).setCounter(counter);
      }
    }
  }

}
