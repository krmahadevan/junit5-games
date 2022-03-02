package com.rationaleemotions.dynamic;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class TestFactoryWithIterableDemoTest {

  @TestFactory
  public Iterable<DynamicTest> testMethod() {
    Iterator<DynamicTest> iterator = Stream.of(1, 2, 0, 4)
        .map(TestFactoryWithIterableDemoTest::newTestFor)
        .collect(Collectors.toList()).iterator();
    return () -> iterator;
  }

  private static DynamicTest newTestFor(int eachNumber) {
    return DynamicTest.dynamicTest(
        "[Iterable]Is " + eachNumber + " even",
        () -> assertThat(eachNumber).isEven()
    );
  }

}
