package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.Apiresources;
import resources.Utils;
import resources.testdata;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class stepvalidation extends Utils {
    RequestSpecification resq;
    ResponseSpecification res;
    Response response ;
    testdata p=new testdata();
   static String place_id;
    @Given("add place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        // Write code here that turns the phrase above into concrete actions


        resq= given().spec(requestspecifications()).body(p.addplace(name,language,address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resources, String methods) {
        // Write code here that turns the phrase above into concrete actions
        Apiresources apiresources=Apiresources.valueOf(resources);
        res=new ResponseSpecBuilder().expectStatusCode(200).build();
        if (methods.equals("post")) {
            response = resq.when().post(apiresources.getResources());
        } else if (methods.equals("get")) {
            response = resq.when().get(apiresources.getResources());
        }
    }
    @Then("the api call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(response.getStatusCode(),int1.intValue());

    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedresponse) {
        // Write code here that turns the phrase above into concrete actions

        Assert.assertEquals(getresponsekey(response,key),expectedresponse);
    }
    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedname, String method) throws IOException {
        // Write code here that turns the phrase above into concrete actions

         place_id=getresponsekey(response,"place_id");
          resq=given().spec(requestspecifications()).queryParam("place_id",place_id);
            user_calls_with_http_request(method,"get");
             String actualname=getresponsekey(response,"name");
            Assert.assertEquals(actualname,expectedname);
    }
    @Given("delete place payload")
    public void delete_place_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        resq=given().spec(requestspecifications()).body(p.deleteplace(place_id));
        }

}
