package com.ecse429.autoproj8.partC.categories;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ecse429.autoproj8.partA.categories.categories_.Categories__GET;
import com.ecse429.autoproj8.partA.categories.categories_.Categories__POST;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.runner.*;

import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PerformanceTestCategories {

    private static String hello = "not another hello world";

    @Test
    public void runCateogiresBenchmarks() throws Exception {
        Options options = new OptionsBuilder().include(this.getClass().getName() + ".*").mode(Mode.AverageTime)
                .warmupTime(TimeValue.seconds(1)).warmupIterations(2).threads(1).measurementIterations(6).forks(1)
                .shouldFailOnError(true).shouldDoGC(true).build();

        new Runner(options).run();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void performanceTestCategoriesPOST() throws IOException, InterruptedException {
                // Dummy request category
                Category requestValidCategory = new Category(1, "Valid Category 1", "Valid Category description", null, null);
        
                // POST valid category
                String[] exclude = {"id", "categories"};
                Category responseCategory = Categories__POST.createCategory(requestValidCategory, exclude);
    }
    

}
