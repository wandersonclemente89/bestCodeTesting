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

    private Functions functions = new Functions();

    @Step
    public void autenticacao(){

        try {
            JSONObject payload = functions.getJson("src/test/resources/requests/gerarToken.json");
            String token = given().contentType(ContentType.JSON).body(payload.toString())
                    .when().post("/auth/login")
                    .then().log().body()
                    .extract().body().jsonPath().get("access_token");

            setRequestSpecification(given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer "+ token)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Step
    public void configurarInformacoesPesquisa(String nomeFantasia){
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", "1");
        params.put("per_page", "30");
        params.put("direction", "desc");
        params.put("column", "created_at");
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
                        , is(equalTo("desc"))
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
