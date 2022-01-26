package HomeWork_3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AccountTests {
    static Map<String, String> headers = new HashMap<>();
    static Properties prop = new Properties();

    @BeforeAll
    static void setUp() throws IOException {
        headers.put("Authorization", "Bearer 062499bef38508393c9da814607a264d6e38286e");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        FileInputStream fis;
        fis = new FileInputStream("src/test/resources/my.properties");
        prop.load(fis);
    }

    @Test
    void getAccountInfoTest() {
        String url = given().log().method()
                .headers(headers)
                .when()
                .get(prop.get("geturl") + "nadeshka7979")
                .prettyPeek()
                .then()
                .statusCode(200).extract().response().jsonPath().getString("data.id");
        assertThat(url, equalTo("159182201"));
    }

    @Test
    void getAccountImagesTest() {
        given().log().method()
                .headers(headers)
                .when()
                .get(prop.get("geturl") + "me/images")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getCommentsTest() {
        given().log().method()
                .headers(headers)
                .when()
                .get(prop.get("geturl") + "nadeshka7979/comments")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getImageIDsTest() {
        String sur = given()
                .headers(headers)
                .when()
                .get(prop.get("geturl") + "nadeshka7979/images/ids")
                .prettyPeek()
                .then()
                .statusCode(200).extract().response().jsonPath().getString("success");
        assertThat(sur, equalTo("true"));
    }

    @Test
    void getAccountFavoritesTest() {
        given().log().method()
                .headers(headers)
                .when()
                .get(prop.get("geturl") + "nadeshka7979/favorites")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getAccountGalleryFavoritesTest() {
        given().log().method()
                .headers(headers)
                .when()
                .get(prop.get("geturl") + "nadeshka7979/gallery_favorites")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getAlbum1Test() {
        String str = given()
                .headers(headers)
                .when()
                .get(prop.get("geturl2") + "mCs6NaG")
                //.prettyPeek()
                .then()
                .statusCode(200).extract().response().jsonPath().getString("data.images.id");
        System.out.println(str);
    }

    @Test
    void postFavoriteAlbumTest() {
        given().log().method()
                .headers(headers)
                .when()
                .post(prop.get("geturl2") + "mCs6NaG/favorite")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void postAddImageToAlbumTest() {
        given().log().method()
                .headers(headers)
                .when()
                .post(prop.get("geturl2") + "mCs6NaG/add?ids[]=KIp5fnf&ids[]=qbLUyGB")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getImageCountTest() {
        String data = given()
                .headers(headers)
                .when()
                .get(prop.get("geturl") + "nadeshka7979/images/count")
                .then()
                .statusCode(200).extract().response().jsonPath().getString("data");
        assertThat(data, equalTo("6"));
    }
}

