package stepdefinitions;

import com.utils.AuthUtils;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static com.common.Constants.AUTHORIZATION_HEADER_TYPE;
import static com.common.Constants.HMAC_ENDPOINT;
import static com.common.Constants.IMBURSE_SANDBOX_BASE_URL;
import static com.common.Constants.PRIVATE_AUTH_KEY;
import static com.common.Constants.PUBLIC_AUTH_KEY;
import static io.restassured.RestAssured.given;

public class Hooks {

    public static String bearerToken = "";
    private int currentTokenExpiryTime = 0;
    AuthUtils authUtils = new AuthUtils();

    @Before
    public void getToken() {
        long currentTime = authUtils.getTimestamp();
        if (bearerToken.equals("") || currentTime > currentTokenExpiryTime) {
            RestAssured.baseURI = IMBURSE_SANDBOX_BASE_URL;
            String hmac = authUtils.generateHmac(PUBLIC_AUTH_KEY, PRIVATE_AUTH_KEY);
            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .header(AUTHORIZATION_HEADER_TYPE, "hmac " + hmac).
                            when()
                            .post(HMAC_ENDPOINT).
                            then()
                            .statusCode(HttpStatus.SC_CREATED).
                            extract().response();

            bearerToken = "bearer " + response.jsonPath().get("accessToken");
            currentTokenExpiryTime = response.jsonPath().get("expires");
        }
    }
}
