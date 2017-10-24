package ru.javatest;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by kbal on 19.10.2017.
 */


@Suite.SuiteClasses({
  CreateFileTest.class
})
@RunWith(Categories.class)
@Categories.IncludeCategory({MyCategories.NegativeTests.class, MyCategories.PositiveTests.class})
public class PositiveAndNegativeSuite {
}
