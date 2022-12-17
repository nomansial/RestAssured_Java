package BDD.CallSign_Assignment;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class TestFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://pro-api.coinmarketcap.com";
		//String Response = given().queryParam("limit", 10).header("Accept","application/json").header("X-CMC_PRO_API_KEY","7690a11a-cf25-4edb-850a-d534bd0104a3").when().get("/v1/cryptocurrency/map")
		//.then().statusCode(200).extract().response().asString();
		
		//System.out.println(Response);
		
		//String convert_Currency_Response = given().log().all().queryParam("id", "1").queryParam("amount", "10").queryParam("convert", "BOLI").header("Accept","application/json").header("X-CMC_PRO_API_KEY","7690a11a-cf25-4edb-850a-d534bd0104a3").when().get("/v2/tools/price-conversion")
				//.then().statusCode(200).extract().response().asString();
		
		//System.out.println(convert_Currency_Response);
//		
//		String info_API_Response = given().queryParam("id", "1027").header("Accept","application/json").header("X-CMC_PRO_API_KEY","7690a11a-cf25-4edb-850a-d534bd0104a3").when().get("/v1/cryptocurrency/info")
//				.then().statusCode(200).extract().response().asString();
//		
//		System.out.println(info_API_Response);
		
		System.out.println("Working Directory = " + System.getProperty("user.dir")+"\\src\\test\\java\\resources\\global.properties");
		
	}

}
