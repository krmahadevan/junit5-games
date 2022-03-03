package com.rationaleemotions.extensions;

import com.rationaleemotions.extensions.TestInstanceFactoryDemoTest.IProduceInstances;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstanceFactory;
import org.junit.jupiter.api.extension.TestInstanceFactoryContext;
import org.junit.jupiter.api.extension.TestInstantiationException;

@ExtendWith(IProduceInstances.class)
public class TestInstanceFactoryDemoTest {

  private final String instanceName;

  public TestInstanceFactoryDemoTest(String instanceName) {
    this.instanceName = instanceName;
  }

  @DisplayName("I-am-produced-by-factories")
  @Test
  public void testMethod() {
    System.err.println("My Instance name is " + this.instanceName);
  }

  public static class IProduceInstances implements TestInstanceFactory {

    @Override
    public Object createTestInstance(TestInstanceFactoryContext factoryContext,
        ExtensionContext extensionContext) throws TestInstantiationException {
      return new TestInstanceFactoryDemoTest("Dragon-Warrior");
    }
  }

}
