package com.rationaleemotions.datadriven.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class SuperHero {

  private final String name;

  public SuperHero(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    //This will help in a pretty printed message in test reports since the test reports
    //are invoking the toString() of the POJO being passed to the parameterised test.
    return String.format("SuperHero{%s}", name);
  }

  public static class Provider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
      return Stream.of("He-man", "Spider-man", "Iron-man", "Super-man")
          .map(SuperHero::new)
          .map(Arguments::of);
    }
  }
}
