package com.rationaleemotions.datadriven.methods;

import java.util.stream.IntStream;

public class DataProviderHouse {

  public static IntStream streamBackedNumberProvider() {
    return IntStream.rangeClosed(1, 5);
  }
}
