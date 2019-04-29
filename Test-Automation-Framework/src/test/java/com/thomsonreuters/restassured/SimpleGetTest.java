package com.thomsonreuters.restassured;

import com.thomsonreuters.restassured.rest.model.Folder;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleGetTest {

    @Test(groups = {"Rest API GET"})
    public void testWeatherDetails() {
        // Specify the base URL to the RESTful web service

        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        RequestSpecification httpRequest = given();

        // Make a request to the server by specifying the method Type and the method URL.
        // This will return the Response from the server. Store the response in a variable.
        Response response = httpRequest.request(Method.GET, "/Hyderabad");

        // Now let us print the body of the message to see what response
        // we have recieved from the server
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
        assertThat(responseBody).as("Response is empty").isNotEmpty();
    }

    @Test(groups = {"Rest API GET"})
    public void testStatusCode() {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/Hyderabad");

        // Get the status code from the Response. In case of
        // a successfull interaction with the web service, we
        // should get a status code of 200.
        // Assert that correct status code is returned.
        assertThat(response.getStatusCode()).as("Incorrect status code returned").isEqualTo(200);
    }

    @Test(groups = {"Rest API GET"})
    public void testHeader() {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/Hyderabad");

        // Reader header of a give name. In this line we will get
        // Header named Content-Type
        String contentType = response.header("Content-Type");
        System.out.println("Content-Type value: " + contentType);

        // Reader header of a give name. In this line we will get
        // Header named Server
        String serverType = response.header("Server");
        System.out.println("Server value: " + serverType);

        // Reader header of a give name. In this line we will get
        // Header named Content-Encoding
        String acceptLanguage = response.header("Content-Encoding");
        System.out.println("Content-Encoding: " + acceptLanguage);
    }

    @Test(groups = {"Rest API GET"})
    public void testAllHeaders() {
        RestAssured.baseURI = "https://law.demo.thomsonreuters.co.uk/Foldering/v3/PLCATestTZ/folders/user/root/ancestors";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get();
        Headers allHeaders = response.headers();
        allHeaders.asList().stream().forEach(h -> {
            System.out.println("name: " + h.getName());
            System.out.println("value: " + h.getValue());
        });
    }

    @Test(groups = {"Rest API GET"})
    public void testResponseBody() {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/Hyderabad");

        ResponseBody body = response.getBody();
        System.out.println("body: " + body.asString());
    }

    @Test(groups = {"Rest API GET"})
    public void testJsonPath() {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/Hyderabad");
        JsonPath jsonPathEvaluator = response.jsonPath();
        String city = jsonPathEvaluator.get("City");
        System.out.println("City received from Response: " + city);
    }

    @Test(groups = {"Rest API GET"})
    public void testGetAncestors() {

        RestAssured.baseURI = "https://law.demo.thomsonreuters.co.uk/Foldering/v3/PLCATestA2/folders/user/root/ancestors";

        RequestSpecification httpRequest = given();

        httpRequest.header("x-cobalt-host", "foldering.int.next.demo.westlaw.com");
        //httpRequest.header("Cookie", " RememberMe=%7b%22cosiSignOn%22%3a%22true%22%7d; UserSettingsLocale=locale=en-US; plcuk-privacy-policy-version=ProductsPrivacyStatementNotifiedMay2018; plc-cookie-policy-accepted=Y; _gcl_au=1.1.1817196583.1553691057; _fbp=fb.2.1553691057625.59202083; _ga=GA1.3.647408274.1553691058; BIGipServerdemo_apache_pc1=!b3c5ojPAYgE1pe8n6EHHaLmb/yFgCDT/6+rEK+p1AOO2YyavO0OoUlQFf4Ju7qw3J0HnAto6xM1aPzM=; bhCookieSess=1; BIGipServerdemo_apache_b=!c6FCilafEswVV7An6EHHaLmb/yFgCMXpFB8ClifKoVzu/3lNkEwY6+g0QipiCuQHhVochbASLZAzLfc=; wco_pm=!TpKMz7vvoVFXBoyK+GdqapBS4JePIFhBjUsaEBYcHC6pKNZQahpjbytUdpZ2sRZByyWyvd/cTWNN; ri_pm=!btIkhPwvhRDeGvyK+GdqapBS4JePIIDnTZb4niCqBUmdyzYEFFJ89If4C2/X9VM8+hMU8ZDcJoAz; alert_pm=!0wVvkB44D8Z+bX6K+GdqapBS4JePIPL/s30rC4kvv5m2IlQkL5qtoRRFFUm5wItNlo1Di324o2RA; sea_pm=!lsezZc90hD6PUPmK+GdqapBS4JePIJ0lL+zAinf7xZrUYSWdUdycEeOlOWOQOGpn1kBzBZpw3AY=; bhCookiePerm=1; ig=demo_b_1; doc_pm=!7bNt8x5O2SRadxmK+GdqapBS4JePIGe5upXYkJRwkYdSTNeZl0q2V0OoZrVqMNw0ehDp6IcBDg==; bhResults=bhab=0&bhav=; mbox=PC#1555316495802-984517.26_31#1557309272|session#1556099671352-315497#1556101532; _gid=GA1.3.1327444939.1556099672; TS014d1757_77=089f8c44bdab2800b202ea7660a9c7f64efcd0f58708a8ec05ff9607806536fcd12a4bd1af960730c5329a7f2271fba608d7578c64823800d089486e5c25b91249976541548849ee1e2cf42e63e348778fe3fb3183ca07af350059dc76bcb76c856b22063ad8f6d3a07c4cc45b5ce15b; AuthenticationStrength=50; site=b; typa_pm=!OXo/Z/8FlVDpLZGK+GdqapBS4JePIMap6yqD3OYQEdOhGxEGNfRO9oqe9uIkvlFNgHpGTweTjCi9; pmd_pm=!u2IvAP5jYmflvm2K+GdqapBS4JePIA2KM9KLcE8eM+0XKsxHZ9Vu1Z6Bl1SeulYiuaejRnTWyRm8; Web_SessionId=s2m333h51cpic1fgt32zhg0d; Co_SessionToken=-qbRS-QcET5FcEcyf2-EgMvI8zzrk4xAN4BKP3XZsh6cp9yvTEkjsct8Wxp_l2o3E78ZFE37VaX8_kFgNv1wHq0PPRXq9rYlsEhmDwEBqAQ; Co_EST=DqF2t83ZOi+TF5RWl9ovJ2G8vGISwNIxzHhoEF3YtUYVZup8mymIZZxQzCPSKiUk5+E+6LP9kY0LW78M61B35TR3r95sbsukLE3PagbOY9u92oD8UG8uOzNdohF49vWPvS9Ejn3cbqg51rShiZNqKg==; TS014d1757=01f6b1de26aaa3c1106fe9f45fb950abde69ee8e5ce682995ed5581c1338674cee856bb97302b00ba5ee628754a05b857ec84b23ee739a78b455b7ce22096c487f36d99f23d9119ae4cf65ceb31f95e9088afa24c528becb58b81c4bcf8da4c65871e3352d; TS0128ffdd=01f6b1de2652ab031acebb73a9eb57218753a977523b2a62deddb45dd4264c6ab9fa2be2e6cd7e0c99156672187cff9e1eb775269dfb154e6415f2a70677f3a78fd8a7c69da9ba13301d28b0b6ae3817d28b444e4e54058ed46c66b51ebad0e15a84c09e60ca114f4ba8416d3791b71b5804a924f234d3cb862eb21713063ebbb9354c9bbeb14922e21714e7efd72abc312cc568a144bbff6187668e10226229effbab27769d68bcde837c236a4172f2d9b6a49117c0c89513edf82d52795ee614a38649cdde803785a699c94410bf261d67451792; SessionStorage=%7B%22ReadingModeView%22%3A%22regular%22%2C%22IsSkipOutOfPlanChecked%22%3Afalse%2C%22Created%22%3A%2204/25/2019%2010%3A05%3A54%22%2C%22Client%22%3A%22NOCLIENTID%22%2C%22ReqWindow%22%3A9%2C%22ActiveReqs%22%3A5%7D; web_pm=!ppRcB+UzyBztvYaK+GdqapBS4JePICVmFAJA6/fFektDfYRjYghSHbvrF/2ixaQ1a97j2XICCQ==; fol_pm=!TkFaDd7rM+1oziSK+GdqapBS4JePIEFYwhiluVo6oOCDFu/1K2HixbydvQO8txLeBULguhegUFQ=; RememberMe=%7b%22cosiSignOn%22%3a%22true%22%7d; UserSettingsLocale=locale=en-US; plcuk-privacy-policy-version=ProductsPrivacyStatementNotifiedMay2018; plc-cookie-policy-accepted=Y; _gcl_au=1.1.1817196583.1553691057; _fbp=fb.2.1553691057625.59202083; _ga=GA1.3.647408274.1553691058; BIGipServerdemo_apache_pc1=!b3c5ojPAYgE1pe8n6EHHaLmb/yFgCDT/6+rEK+p1AOO2YyavO0OoUlQFf4Ju7qw3J0HnAto6xM1aPzM=; bhCookieSess=1; BIGipServerdemo_apache_b=!c6FCilafEswVV7An6EHHaLmb/yFgCMXpFB8ClifKoVzu/3lNkEwY6+g0QipiCuQHhVochbASLZAzLfc=; ri_pm=!btIkhPwvhRDeGvyK+GdqapBS4JePIIDnTZb4niCqBUmdyzYEFFJ89If4C2/X9VM8+hMU8ZDcJoAz; sea_pm=!lsezZc90hD6PUPmK+GdqapBS4JePIJ0lL+zAinf7xZrUYSWdUdycEeOlOWOQOGpn1kBzBZpw3AY=; bhCookiePerm=1; doc_pm=!7bNt8x5O2SRadxmK+GdqapBS4JePIGe5upXYkJRwkYdSTNeZl0q2V0OoZrVqMNw0ehDp6IcBDg==; bhResults=bhab=0&bhav=; mbox=PC#1555316495802-984517.26_31#1557309272|session#1556099671352-315497#1556101532; typa_pm=!OXo/Z/8FlVDpLZGK+GdqapBS4JePIMap6yqD3OYQEdOhGxEGNfRO9oqe9uIkvlFNgHpGTweTjCi9; pmd_pm=!u2IvAP5jYmflvm2K+GdqapBS4JePIA2KM9KLcE8eM+0XKsxHZ9Vu1Z6Bl1SeulYiuaejRnTWyRm8; TS014d1757_77=089f8c44bdab28006d5f06f2f6e752331c132684ab998457d6d752122b2f9393de9f9ccf53446d765f913c3bc887c4af08a2d43907823800f814c392dfa40ee83b58edcdcfc409f18dc18f502928905cdeb9b9e6d1cd5dae7cd2cf1ddfdbd709f0f47c8df19a2a24fcf9c5460b86f7bb; site=b; ig=demo_b_1; web_pm=!EA0+mcTnjwjOr7iK+GdqapBS4JePICDfW/AL6JDeoN3ti5Hr+0Vj9IhrZaankqPXaK99Ig1cIA==; wco_pm=!hhqwP0RDk/b7sYyK+GdqapBS4JePIF5XRJuRQMjwWFOc4yFXeaX8P8f8AmblW9h6gEZtRzHbB2TI; fol_pm=!nR0v37NcwB5t6TmK+GdqapBS4JePIBBCknukGkebqoG2OBYgbqXwM4hsS6gp7VqYSNVr8DeIA8A=; alert_pm=!e4VS+UDOBkM6bKOK+GdqapBS4JePIEwpXd5y6bN5ikUWymZgBwDUDayAi8yx/epezX1fPMKm9+k/; TS014d1757=01f6b1de26b0eded9bad3a12032c51aba42667eaceb9c239c89a2b01e496c6d045e9cec4e3dbf91ed8218826a216872b683674bb0dd9b44ea069278b99df7e20f378e6cf2359d349f8262dd0f00445a4208ebf961a85c17caa281b9f59b2579955547111bc; AuthenticationStrength=50; Web_SessionId=2biyl3spjubutnsvrvgt5bem; Co_SessionToken=6V-SUNaaCucSrfBlFjQQqk59KkE-0QX0_JzmJm46ZRjmHrxaEKno7KEwbSEBUWadpXDjDFiAZ2PFjA-RpqiIUJ_My6xqD5L8PpSVfZViR_g; Co_EST=DtqN/ry2R5rgu4nonk7UqUbNFq/Md+x0juuHFXxD0FalnG5p+DOfSfz5ntLAWoDNeE7/Omfb4gXQoYTv4WWKSr1ZT7hx2N8J2AR4LrWLnuxAeCvEP05fwKe+/V6gNGyIaEvgVfFFXMJh5yu1pI5lJA==; TS0128ffdd=01f6b1de26760d7a178a1670790e1c025d7c654f383b2a62deddb45dd4264c6ab9fa2be2e646d4349eb48ea8161d22a08def487fb73c5cee5876a904e3a0cbd7d0aec035560aa8c5381818ca9fe3048205b3e98f85c7964f7c5abdc47eb5003524aeed831ec8767616596e56e4a00ccf836322bb6929cd743407128c265f2e0222161ad0395eba36d1de61fde58b04f2560da506d8f68c447010b70c98e6deb912dd713d6c31de06bec9834daa31f993fb3e7639baab00e332eb8aebe27c37d1288039d64450e1ac01df49d9911da40eddc64f520e; SessionStorage=%7B%22ReadingModeView%22%3A%22regular%22%2C%22IsSkipOutOfPlanChecked%22%3Afalse%2C%22Created%22%3A%2204/26/2019%2011%3A37%3A08%22%2C%22Client%22%3A%22NOCLIENTID%22%2C%22ReqWindow%22%3A5%2C%22ActiveReqs%22%3A5%7D");
        //Response response = httpRequest.get(RestAssured.baseURI);

        //Folder folder = response.as(Folder.class);

       // System.out.println(folder.getCategoryId());

       /* JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> categoryIds = jsonPathEvaluator.get("categoryId");
        System.out.println("Category id received from Response: " + categoryIds.toString());*/

    }
}
