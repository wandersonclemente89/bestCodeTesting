package br.com.geopostelight.steps;

import br.com.geopostelight.utils.BaseTest;
import br.com.geopostelight.utils.Functions;
import io.cucumber.datatable.DataTable;
import io.restassured.http.ContentType;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Helper extends BaseTest {

    @Step
    public void autenticacao(){

        setRequestSpecification(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiYTM1ZGUzOWMzMTgxODUyNDQyOGE5NDgyYjI5YTBlNmZjZTY0N2U4MDE3NzI3MTY5NWFmOGE0NDZkZDNkYzFlMTQ1OTBiYTFhNjBjNWZhMTkiLCJpYXQiOjE2MDQwOTE4MDMsIm5iZiI6MTYwNDA5MTgwMywiZXhwIjoxNjM1NjI3ODAzLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.G4QqFrViejtbDhsbbIpxW2wm22BpM5jlQnuYU7JciBS7IQ2EcTUGZsvDhFaA3As-mnAmMKpS3P-wFSTnye0Db5PlnwKnZUoGdt9Wht97dtgKQu7TX8pNNB2AUTsL66VSqKRD3q9UeKKh4kCVeOTqDsDg8YN1x_yUrFo-pIciXBTjMPLVT0tLpYvAOzIpp60QGqf0zjQ2exnY_zJoAa13msYPwVFc8hm9HgmsSxIflE0Jw3acsEnUOP4Yli85RtWA0wmOHHZ1m0Q1Nb-JMFLMOmBf-zHEK9vochGgpFQlvmv80b2bbT_FIkfU2QR9vHWEgv3HLZh9q-4RH5Ry4P0D8_MPeHrbcjXhLNeaR5TQo2JQgzgUv7a0W6sGLX3jHuWD5QX--AgIA5M_q68vS4oFuClf7_DYRDZO_7MXiCLX9xmA0F0RMO2pPhfQBCQI_HhR-Tjq5BQV0rfj5_YgEoglyNt1PzZNsU25mQ8AG5x6n6DKUo1TQ9KckbbDIvh0g0i147kcxe1GnJ3XFEzxW1_gNEf-EP4MDbfBPzhzB7IpxJ5jCWXg6ZXYiU72VZc2T9nAiJo2dZMshW7WZLYgkPSRR-y7Ghogg4IcBt3alpx1evP2uOowzyzdWlIpFyfBVC4yFvL71-Ho8ZNw9U_xdg_zOUFWC5gyRWEpNG4ehNoXUWs")
        );
    }

    @Step
    public void configurarInformacoesPesquisa(String nomeFantasia){
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", "1");
        params.put("per_page", "30");
        params.put("direction", "asc");
        params.put("column", "nome_fantasia");
        params.put("nome_fantasia", nomeFantasia);

        setRequestSpecification(getRequestSpecification().queryParams(params));
    }


    @Step
    public void validacao(String acao){
        setValidatableResponse(getResponse().then().statusCode(200).log().body());

        switch (acao){
            case "criados":
                assertThat(
                        getValidatableResponse().extract().body().jsonPath().<String>get("message")
                        , is(equalTo("O registro foi salvo com sucesso!"))
                );
                assertThat(
                        String.valueOf(getValidatableResponse().extract().body().jsonPath().<String>get("success")),
                        is(equalTo("true"))
                );
                break;
            case "retornados":
                assertThat(
                        getValidatableResponse().extract().body().jsonPath().<String>get("empresas.data[0].nome_fantasia")
                        , is(equalTo("GEOPOSTE_FANTASIA"))
                );
                assertThat(
                        getValidatableResponse().extract().body().jsonPath().<String>get("empresas.data[0].endereco")
                        , is(equalTo("Rua do Freela, 41"))
                );
                assertThat(
                        getValidatableResponse().extract().body().jsonPath().<String>get("params.direction")
                        , is(equalTo("asc"))
                );
                break;
            case "atualizados":
                assertThat(
                        getValidatableResponse().extract().body().jsonPath().<String>get("message")
                        , is(equalTo("O registro foi atualizado com sucesso!"))
                );
                assertThat(
                        String.valueOf(getValidatableResponse().extract().body().jsonPath().<String>get("success")),
                        is(equalTo("true"))
                );
                break;
            case "apagados":
                assertThat(
                        getValidatableResponse().extract().body().jsonPath().<String>get("message")
                        , is(equalTo("O registro foi apagado com sucesso!"))
                );
                assertThat(
                        String.valueOf(getValidatableResponse().extract().body().jsonPath().<String>get("success")),
                        is(equalTo("true"))
                );
                break;
        }
    }

    @Step
    public void configurarInformacoesDaEmpresa(DataTable dataTable) throws IOException {
        List<List<String>> linhas = dataTable.asLists(String.class);
        Functions functions = new Functions();

        JSONObject payload = functions.getJson("src/test/resources/requests/adicionarEmpresa.json");

        for (List<String> colunas: linhas) {
            payload.put(colunas.get(0),colunas.get(1));
        }

        setRequestSpecification(getRequestSpecification().body(payload.toString()).log().body());
    }

    @Step
    public void gerenciarAPI(String operacao){

        switch (operacao){
            case "cadastro":
                setResponse(getRequestSpecification().when().post("/empresas"));
                break;
            case "atualização":
                setResponse(getRequestSpecification().when().put("/empresas/{id}"));
               break;
            case "pesquisa":
                setResponse(getRequestSpecification().when().get("/empresas"));
                break;
            case "exclusão":
                setResponse(getRequestSpecification().when().delete("/empresas/{id}"));
                break;
        }
    }

    @Step
    public void atualizarEmpresa(String nomeFantasia, DataTable dataTable) throws IOException {
       getEmpresaId(nomeFantasia);
       configurarInformacoesDaEmpresa(dataTable);
    }

    @Step
    public void getEmpresaId(String nomeFantasia){
        autenticacao();
        configurarInformacoesPesquisa(nomeFantasia);
        gerenciarAPI("pesquisa");
        String id = String.valueOf(getResponse().then().extract().body().jsonPath().<String>get("empresas.data[0].id"));

        setRequestSpecification(getRequestSpecification().pathParam("id", id));
    }
}
