package com.rationaleemotions.dynamic;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInfo;

public class TestFactoryUsingDynamicContainersDemoTest {

  //This will be invoked ONLY before each of the "@TestFactory" invocations
  @BeforeEach
  public void beforeEach(TestInfo info) {
    System.err.println("Running before Each for " + info.getDisplayName());
  }

  @DisplayName("Number-Evaluator-Iterable")
  @TestFactory
  public Iterable<DynamicContainer> usingIterable() {
    Iterator<DynamicContainer> iterator = Arrays.asList(
        DynamicContainer.dynamicContainer("Even-Numbers", evenNumberTests()),
        DynamicContainer.dynamicContainer("Odd-Numbers", oddNumberTests())
    ).iterator();
    return () -> iterator;
  }


  @DisplayName("Number-Evaluator-Iterators")
  @TestFactory
  public Iterator<DynamicContainer> usingIterators() {
    return Arrays.asList(
        DynamicContainer.dynamicContainer("Even-Numbers", evenNumberTests()),
        DynamicContainer.dynamicContainer("Odd-Numbers", oddNumberTests())
    ).iterator();
  }

  @DisplayName("Number-Evaluator-Streams")
  @TestFactory
  public Stream<DynamicContainer> usingStreams() {
    return Stream.of(
        DynamicContainer.dynamicContainer("Even-Numbers", evenNumberTests()),
        DynamicContainer.dynamicContainer("Odd-Numbers", oddNumberTests())
    );
  }

  @DisplayName("Number-Evaluator-Lists")
  @TestFactory
  public List<DynamicContainer> usingLists() {
    return Arrays.asList(
        DynamicContainer.dynamicContainer("Even-Numbers", evenNumberTests()),
        DynamicContainer.dynamicContainer("Odd-Numbers", oddNumberTests())
    );
  }

  private static Stream<DynamicTest> oddNumberTests() {
    return IntStream.rangeClosed(1, 5)
        .mapToObj(TestFactoryUsingDynamicContainersDemoTest::newOddTest);

  }

  private static Stream<DynamicTest> evenNumberTests() {
    return IntStream.rangeClosed(1, 5)
        .mapToObj(TestFactoryUsingDynamicContainersDemoTest::newEvenTest);
  }

  private static DynamicTest newOddTest(int eachNumber) {
    return DynamicTest.dynamicTest(
        "[Stream Powered] Is " + eachNumber + " odd",
        () -> assertThat(eachNumber).isOdd()
    );
  }

  private static DynamicTest newEvenTest(int eachNumber) {
    return DynamicTest.dynamicTest(
        "[Stream Stream] Is " + eachNumber + " even",
        () -> assertThat(eachNumber).isEven()
    );
  }
}
