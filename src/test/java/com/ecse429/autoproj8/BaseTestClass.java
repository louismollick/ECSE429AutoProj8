package com.ecse429.autoproj8;

import java.io.IOException;

import com.ecse429.autoproj8.shutdown.Shutdown;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTestClass {
  @BeforeClass
  public static void setUp() throws IOException, InterruptedException {
      // Start server
      Runtime rt = Runtime.getRuntime();
      rt.exec("java -jar runTodoManagerRestAPI-1.5.5.jar");
      Thread.sleep(500);
  }

  @AfterClass
  public static void shutdown() throws IOException, InterruptedException {
      // Shutdown server
      Shutdown.shutdown();
      Thread.sleep(500);
  }
}
