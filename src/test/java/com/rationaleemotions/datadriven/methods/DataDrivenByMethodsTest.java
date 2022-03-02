package com.rationaleemotions.datadriven.methods;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class DataDrivenByMethodsTest {

  @DisplayName("Odd-Numbers-Using-Local-DataProvider")
  @ParameterizedTest
  @MethodSource(value = {"streamBackedNumberProvider"})
  public void oddNumberTestWithLocalDataProvider(int i) {
    assertThat(i).isOdd();
  }

  @DisplayName("Odd-Numbers-Using-Remote-DataProvider")
  @ParameterizedTest
  @MethodSource(value = {"com.rationaleemotions.datadriven.methods.DataProviderHouse#streamBackedNumberProvider"})
  public void oddNumberTestWithRemoteDataProvider(int i) {
    assertThat(i).isOdd();
  }

  public static IntStream streamBackedNumberProvider() {
    return IntStream.rangeClosed(1, 5);
  }

}
