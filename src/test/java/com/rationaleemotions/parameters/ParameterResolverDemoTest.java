package com.rationaleemotions.parameters;

import com.rationaleemotions.parameters.SuperHero.Resolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(Resolver.class)
public class ParameterResolverDemoTest {

  @Test
  @DisplayName("Super-Hero-Evaluator")
  public void testMethod(SuperHero hero) {
    System.err.println("Our favourite super-hero is " + hero.getName());
  }
}
