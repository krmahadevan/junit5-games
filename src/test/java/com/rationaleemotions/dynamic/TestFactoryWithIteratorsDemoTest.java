package com.rationaleemotions.dynamic;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class TestFactoryWithIteratorsDemoTest {

  @TestFactory
  public Iterator<DynamicTest> testMethod() {
    return Stream.of(1, 2, 0, 4).map(TestFactoryWithIteratorsDemoTest::newTestFor)
        .collect(Collectors.toList()).iterator();
  }

  private static DynamicTest newTestFor(int eachNumber) {
    return DynamicTest.dynamicTest(
        "[Iterator]Is " + eachNumber + " even",
        () -> assertThat(eachNumber).isEven()
    );
  }

}
