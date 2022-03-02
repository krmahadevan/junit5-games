package com.rationaleemotions.execorder;

import com.rationaleemotions.execorder.ExecutionOrderDemoTest.PriorityOrder;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Comparator;
import java.util.Optional;
import org.junit.jupiter.api.MethodDescriptor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrdererContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(value = PriorityOrder.class)
public class ExecutionOrderDemoTest {

  @Test
  @Priority(1000)
  public void a() {
    System.err.println("a()");
  }

  @Test
  @Priority(100)
  public void b() {
    System.err.println("b()");
  }

  @Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
  @Retention(RetentionPolicy.RUNTIME)
  @interface Priority {

    int value() default 0;
  }

  public static class PriorityOrder implements MethodOrderer {

    @Override
    public void orderMethods(MethodOrdererContext context) {
      context.getMethodDescriptors()
          .sort((Comparator<MethodDescriptor>) (o1, o2) -> {
            int m1 = getPriorityOrDefaultTo(o1);
            int m2 = getPriorityOrDefaultTo(o2);
            return Integer.compare(m1, m2);
          });

      context.getMethodDescriptors()
          .forEach(
              e -> System.err.println(">>>> " + e.getMethod().getName() + " priority " +
                  getPriorityOrDefaultTo(e))
          );
    }
  }

  private static int getPriorityOrDefaultTo(MethodDescriptor m1) {
    Optional<Priority> result = m1.findAnnotation(Priority.class);
    return result.map(Priority::value).orElse(0);
  }

}
