package com.rationaleemotions.parameters;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class SuperHero {

  private final String name;

  public SuperHero(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static class Resolver implements ParameterResolver {

    private static final List<SuperHero> heroes = Arrays.asList(
        new SuperHero("Spider-man"),
        new SuperHero("Super-man"),
        new SuperHero("He-man"),
        new SuperHero("Ant-man"),
        new SuperHero("Iron-man")
    );

    private static final Random random = new Random();

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
        ExtensionContext extensionContext) throws ParameterResolutionException {
      return parameterContext.getParameter().getType().equals(SuperHero.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
        ExtensionContext extensionContext) throws ParameterResolutionException {
      if (supportsParameter(parameterContext, extensionContext)) {
        int position = random.nextInt(heroes.size());
        return heroes.get(position);
      }
      return null;
    }
  }
}
