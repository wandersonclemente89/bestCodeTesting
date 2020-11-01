package br.com.geopostelight.empresa;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"br.com.geopostelight.empresa.steps", "br.com.geopostelight.empresa.hooks"}
)
public class EmpresaRunner {
}
