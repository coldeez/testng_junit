package ru.javatest;



import org.assertj.core.api.SoftAssertions;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.hamcrest.CoreMatchers.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateFileTest implements  MyCategories {

    String fileName = "test.txt";
    String fileNameCyrillic = "тест.txt";
    static Path testDirectory;
    File file;



    @Test
    @Category(PositiveTests.class)
    public void successfulFileCreation() {
        file = new File(testDirectory + "/" + fileName);
        try {
            Assert.assertThat("File is not exist and created", file.createNewFile(), is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Category(PositiveTests.class)
    public void successfulFileCreationCyrillic() {
        file = new File(testDirectory + "/" + fileNameCyrillic);
        try {
            Assert.assertThat("File is not exist and created", file.createNewFile(), is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Category(NegativeTests.class)
    public void namedFileAlreadyExists() {
        file = new File(testDirectory + "/" + fileName);
        try {
            file.createNewFile();
            Assert.assertThat("File is not exist and created",file.createNewFile(),is(false));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    @Category(NegativeTests.class)
    public void emptyFileNameIOEx() {
        SoftAssertions s = new SoftAssertions();
        boolean ioexceptionDetected = false;
        file = new File(testDirectory + "/" + "*");
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
            ioexceptionDetected = true;
        }
        s.assertThat(ioexceptionDetected).as("Wrong file name I/O error occurred").isEqualTo(true);
        s.assertThat(!file.exists()).as("File is not created").isEqualTo(true);
        s.assertAll();
    }

    @BeforeClass
    public static void createTempDirectory() {
        try {
            testDirectory = Files.createTempDirectory("createFileTest");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void deleteTempDirectory() {
        try {
            Files.delete(testDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void deleteFileInTempDirectory() {
        if (file.exists()) {
            file.delete();
        }
    }


}