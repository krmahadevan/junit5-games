package com.rationaleemotions.extensions;

import com.rationaleemotions.extensions.ExecutionConditionDemoTest.Decider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

@ExtendWith(Decider.class)
public class ExecutionConditionDemoTest {

  @DisplayName("jvm-arg-driven-test")
  @Test
  public void testMethod() {
  }

  public static class Decider implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
      String arg = "should.run";
      boolean flag = Boolean.getBoolean(arg);
      if (flag) {
        return ConditionEvaluationResult.enabled("You CAN run");
      }
      return ConditionEvaluationResult.disabled(
          "You CANNOT run because we are missing the JVM argument " + arg);
    }
  }

}
