package com.rationaleemotions.datadriven.arguments;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class Player {

  private final String name;

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return String.format("Player{%s}", name);
  }

  public static class Aggregator implements ArgumentsAggregator {

    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
        throws ArgumentsAggregationException {
      return new Player(accessor.getString(0));
    }
  }
}
