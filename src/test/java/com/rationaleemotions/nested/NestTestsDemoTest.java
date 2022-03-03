package com.rationaleemotions.nested;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

//Example borrowed from https://www.arhohuttunen.com/junit-5-nested-tests/
public class NestTestsDemoTest {
  @Nested
  @DisplayName("Creating a product")
  class PostTest {
    @Nested
    @DisplayName("when fields are missing")
    class WhenFieldsAreMissingTest {
      @Test
      @DisplayName("return HTTP status Bad Request")
      void returnHttpStatusBadRequest() { }

      @Test
      @DisplayName("do not create a product")
      void doNotCreateProduct() { }
    }

    @Nested
    @DisplayName("when fields are valid")
    class WhenFieldsAreValidTest {
      @Test
      @DisplayName("return HTTP status Created")
      void returnHttpStatusCreated() { }
    }
  }

  @Nested
  @DisplayName("Finding a product")
  class GetByIdTest {
    @Nested
    @DisplayName("when product is not found")
    class WhenProductIsNotFoundTest {
      @Test
      @DisplayName("return HTTP status Not Found")
      void returnHttpStatusNotFound() { }
    }

    @Nested
    @DisplayName("when product is found")
    class WhenProductIsFoundTest {
      @Test
      @DisplayName("return HTTP status OK")
      void returnHttpStatusOk() { }

      @Test
      @DisplayName("return found product as JSON")
      void returnFoundProductAsJson() { }
    }
  }

  @Nested
  @DisplayName("Deleting a product")
  class DeleteTest {
    @Nested
    @DisplayName("when product is not found")
    class WhenProductIsNotFoundTest {
      @Test
      @DisplayName("return HTTP status Not Found")
      void returnHttpStatusNotFound() { }
    }

    @Nested
    @DisplayName("when product is found")
    class WhenProductIsFoundTest {
      @Test
      @DisplayName("return HTTP status No Content")
      void returnHttpStatusNoContent() { }
    }
  }
}
