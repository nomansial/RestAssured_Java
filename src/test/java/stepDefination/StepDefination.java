package stepDefination;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import resources.common_methods;

public class StepDefination{
	String baseURL;
	String API_KEY;
	String Retrieve_ID_Response;
	String convert_Currency_Response;
	String name;
	String id;
	int id_count, convert_count;
	JsonPath js_id, convert_id, info_API;
	String converted_amount;
	String info_API_Response;

	@Given("API Key is provided in header")
	public void headerValues() throws IOException {
		baseURL = common_methods.getGlobalValue("baseURL");
		API_KEY = common_methods.getGlobalValue("API_Key");;
	}

	@When("retrieve the ID of currency {string}")
	public void executeAPI(String currency_symbols) throws FileNotFoundException {
		RestAssured.baseURI = baseURL;
		Retrieve_ID_Response = given().queryParam("symbol", currency_symbols).header("Accept", "application/json")
				.header("X-CMC_PRO_API_KEY", "7690a11a-cf25-4edb-850a-d534bd0104a3").when()
				.get("/v1/cryptocurrency/map").then().statusCode(200).extract().response().asString();

		js_id = new JsonPath(Retrieve_ID_Response);
		id_count = js_id.getInt("data.size()");

		name = js_id.getString("data.name");
		id = js_id.getString("data.id");
	}

	@And("ID is retieved")
	public void Convert() {
		System.out.println("Currency Names are: "+name);
		System.out.println("ID's for these currencies are: "+id);
	}

	@Then("convert to {string}")
	public void retrieveID(String convert_to) {		
		RestAssured.baseURI= baseURL;
		String remove_brackets = id.replaceAll("[\\[\\]\\s]", "");
		List<String> list_currency = Arrays.asList(remove_brackets.split(","));
		
		for (int i = 0; i < list_currency.size(); i++) {
		
		String convert_Currency_Response = given().queryParam("id", list_currency.get(i)).queryParam("amount", "10").queryParam("convert", convert_to).header("Accept","application/json").header("X-CMC_PRO_API_KEY","7690a11a-cf25-4edb-850a-d534bd0104a3").when().get("/v2/tools/price-conversion")
				.then().statusCode(200).extract().response().asString();
		
		convert_id = new JsonPath(convert_Currency_Response);
		String converted_amount_symbol = "data.quote."+convert_to+".price";
		converted_amount = convert_id.getString(converted_amount_symbol);
		
		System.out.println("Converted amount for currency "+list_currency.get(i)+" to "+convert_to+" is "+converted_amount);
		
		}
		
	}
	
	@When("API executed for assertions on Ethereum currency")
	public void info_API() {		
		RestAssured.baseURI= baseURL;
		
		info_API_Response = given().queryParam("id", "1027").header("Accept","application/json").header("X-CMC_PRO_API_KEY","7690a11a-cf25-4edb-850a-d534bd0104a3").when().get("/v1/cryptocurrency/info")
				.then().statusCode(200).extract().response().asString();
		
	}
	
	@Then("Assertions are verified")
	public void info_API_assertions() {
		info_API = new JsonPath(info_API_Response);
		
		assertEquals(info_API.getString("data.1027.logo"), "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png");
		assertEquals(info_API.getString("data.1027.urls.technical_doc"), "[https://github.com/ethereum/wiki/wiki/White-Paper]");
		assertEquals(info_API.getString("data.1027.symbol"), "ETH");
		assertEquals(info_API.getString("data.1027.date_added"), "2015-08-07T00:00:00.000Z");
	}
}
