package com.rationaleemotions.datadriven.values;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DataDrivenByValuesTest {

  @DisplayName("Even-Numbers-Validator")
  @ParameterizedTest(name = ParameterizedTest.DEFAULT_DISPLAY_NAME)
  @ValueSource(ints = {1, 2, 3, 4, 5})
  public void evenNumberTest(int i) {
    assertThat(i).isEven();
  }
}
