package com.ecse429.autoproj8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

public class RandomSuiteRunner extends Suite {

  public RandomSuiteRunner(Class<?> klass, RunnerBuilder builder) throws InitializationError {
    super(builder, klass, getAnnotatedClasses(klass));
  }

  public RandomSuiteRunner(RunnerBuilder builder, Class<?>[] classes) throws InitializationError {
    super(builder, randomize(classes));
  }

  protected RandomSuiteRunner(Class<?> klass, List<Runner> runners) throws InitializationError {
    super(klass, randomize(runners));
  }

  private static Class<?>[] getAnnotatedClasses(Class<?> klass) throws InitializationError {
    SuiteClasses annotation = klass.getAnnotation(SuiteClasses.class);
    if (annotation == null) {
      throw new InitializationError(String.format("class '%s' must have a SuiteClasses annotation", klass.getName()));
    }
    return randomize(annotation.value());
  }

  private static List<Runner> randomize(List<Runner> runners) {
    Collections.shuffle(runners);
    return runners;
  }

  private static Class<?>[] randomize(Class<?>[] classes) {
    List<Class<?>> shuffled = Arrays.asList(classes);
    Collections.shuffle(shuffled);
    return shuffled.toArray(new Class<?>[shuffled.size()]);
  }
}
