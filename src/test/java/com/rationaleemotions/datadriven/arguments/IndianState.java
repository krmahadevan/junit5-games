package com.rationaleemotions.datadriven.arguments;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class IndianState {

  private final String name;
  private final String capital;

  public IndianState(String name, String capital) {
    this.name = name;
    this.capital = capital;
  }

  @Override
  public String toString() {
    return String.format("{ State: %s, capital: %s }", name, capital);
  }

  public static class Aggregator implements ArgumentsAggregator {

    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
        throws ArgumentsAggregationException {
      return new IndianState(accessor.getString(0), accessor.getString(1));
    }
  }
}
