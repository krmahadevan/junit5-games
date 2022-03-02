package com.rationaleemotions.datadriven.arguments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class DataDrivenByArgumentsTest {

  @DisplayName("Best-Super-hero-test")
  @ParameterizedTest
  @ArgumentsSource(value = SuperHero.Provider.class)
  public void testSuperHeroes(SuperHero superHero) {
    System.err.println("Super-Hero : " + superHero.getName());
  }

  @DisplayName("Best-Player-test")
  @CsvSource({"Sachin Tendulkar", "Ravi Shastri", "Sunil Gavaskar", "Kapil Dev"})
  @ParameterizedTest
  public void testPlayers(@AggregateWith(Player.Aggregator.class) Player player) {
    System.err.println("Player : " + player.getName());
  }

  @DisplayName("States tester")
  @CsvFileSource(resources = "/states.csv")
  @ParameterizedTest
  public void showStates(@AggregateWith(IndianState.Aggregator.class) IndianState state) {
    System.err.println(state.toString());
  }

  @DisplayName("Show dates")
  @CsvSource({"01/Jan/2009", "01/Jan/2010", "01/Jan/2011"})
  @ParameterizedTest
  public void showDates(@ConvertWith(MyConverter.class)Date date) {
    System.err.println("Date : " + new SimpleDateFormat("dd-MMM-yyyy").format(date));
  }

  public static class MyConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context)
        throws ArgumentConversionException {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
      try {
        return sdf.parse(source.toString());
      } catch (ParseException e) {
        return null;
      }
    }
  }


}
