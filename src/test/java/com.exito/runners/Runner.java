package com.exito.runners;

import com.exito.utils.datosexcel.BeforeSuite;
import com.exito.utils.datosexcel.DataToFeature;
import com.exito.utils.runnerpersonalizado.RunnerPersonalizado;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import java.io.IOException;

import static com.exito.utils.constants.TipoClaseConstantes.CLASE_RUNNER;

@RunWith(RunnerPersonalizado.class)
@CucumberOptions(
        features = "src/test/resources/features/userRegister.feature",
        glue = "com.exito.stepDefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {"pretty"},
        tags = "@Successful"
)
public class Runner {
    private Runner() {
        throw new IllegalStateException(CLASE_RUNNER);
    }

    @BeforeSuite
    public static void test() throws IOException {
        DataToFeature.overrideFeatureFiles("src/test/resources/features/userRegister.feature");
    }
}
