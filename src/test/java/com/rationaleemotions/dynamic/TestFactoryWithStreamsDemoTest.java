package com.rationaleemotions.dynamic;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class TestFactoryWithStreamsDemoTest {

  @TestFactory
  Stream<DynamicTest> testMethod() {
    return Stream.of(1, 2, 0, 4).map(TestFactoryWithStreamsDemoTest::newTestFor);
  }

  private static DynamicTest newTestFor(int eachNumber) {
    return DynamicTest.dynamicTest(
        "[Stream]Is " + eachNumber + " even",
        () -> assertThat(eachNumber).isEven()
    );
  }

}
