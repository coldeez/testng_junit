package ru.javatest;



import com.tngtech.java.junit.dataprovider.DataProvider;
import org.junit.runners.model.FrameworkMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by kbal on 17.10.2017.
 */
public class DataProviders {


  @DataProvider
  public static Object[] fileNames() {
    List<String> data = new ArrayList<String>();
    for (int i = 0; i < 3; i++) {
      data.add(generateRandomName());
    }
    return (Object[]) data.toArray(new Object[]{});
  }

  private static String generateRandomName() {
    return "тест" + new Random().nextInt();
  }



  public static Object[] fileNamesFromFile(String value) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(
            DataProviders.class.getResourceAsStream(value)));

    List<String> fileNames = new ArrayList<>();
    String line = in.readLine();
    while (line != null) {
      fileNames.add(line);
      line = in.readLine();
    }
    in.close();
    return (Object[]) fileNames.toArray(new Object[]{});
  }


  @DataProvider
  public static Object[] dataSourceLoader(FrameworkMethod testMethod) throws IOException {
    DataSource ds = testMethod.getAnnotation(DataSource.class);
    if (ds == null) {
      throw new Error("Test method has no @DataSource annotation: " + testMethod.getName());
    }
    return fileNamesFromFile(ds.value());
  }

}
