package HomeWork_4;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AccountTests{

    static Properties prop = new Properties();
    static ResponseSpecification responseSpecification ;
    static RequestSpecification requestSpecification ;
    static String token= "Bearer 062499bef38508393c9da814607a264d6e38286e";

    @BeforeAll
    static void setUp() throws IOException {

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        FileInputStream fis;
        fis = new FileInputStream("src/test/resources/my.properties");
        prop.load(fis);
        requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.ANY)
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();
        RestAssured.responseSpecification = responseSpecification;
    }

    @Test
    void getAccountInfoTest() {
    ImgurDto imgurDto = given()
                .spec(requestSpecification)
                .when()
                .get(prop.get("geturl") + "nadeshka7979")
                //.prettyPeek()
                .then()
                .spec(responseSpecification).extract().body()
                .as(ImgurDto.class);
        System.out.println(imgurDto.data.getId());
    }

    @Test
    void getAccountImagesTest() {
   given().log().method()
                .spec(requestSpecification)
                .when()
                .get(prop.get("geturl") + "me/images")
                //.prettyPeek()
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getCommentsTest() {
        given().log().method()
                .spec(requestSpecification)
                .when()
                .get(prop.get("geturl") + "nadeshka7979/comments")
                //.prettyPeek()
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getImageIDsTest() {
        given()
                .spec(requestSpecification)
                .when()
                .get(prop.get("geturl") + "nadeshka7979/images/ids")
                //.prettyPeek()
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getAccountFavoritesTest() {
     ImgurDto2 imgurDto2 = given().log().method()
                .spec(requestSpecification)
                .when()
                .get(prop.get("geturl") + "nadeshka7979/favorites")
               // .prettyPeek()
                .then()
                .spec(responseSpecification).extract().body()
                .as(ImgurDto2.class);
        System.out.println(imgurDto2.data.get(5).id);
    }

    @Test
    void getAccountGalleryFavoritesTest() {
        given().log().method()
                .spec(requestSpecification)
                .when()
                .get(prop.get("geturl") + "nadeshka7979/gallery_favorites")
                //.prettyPeek()
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getAlbum1Test() {
        given()
                .spec(requestSpecification)
                .when()
                .get(prop.get("geturl2") + "mCs6NaG")
                //.prettyPeek()
                .then()
                .spec(responseSpecification);
    }

    @Test
    void postFavoriteAlbumTest() {
        given().log().method()
                .spec(requestSpecification)
                .when()
                .post(prop.get("geturl2") + "mCs6NaG/favorite")
                //.prettyPeek()
                .then()
                .spec(responseSpecification);
    }

    @Test
    void postAddImageToAlbumTest() {
        given().log().method()
                .spec(requestSpecification)
                .when()
                .post(prop.get("geturl2") + "mCs6NaG/add?ids[]=KIp5fnf&ids[]=qbLUyGB")
                //.prettyPeek()
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getImageCountTest() {
        String data = given().log().method()
                .spec(requestSpecification)
                .when()
                .get(prop.get("geturl") + "nadeshka7979/images/count")
                .then()
                .spec(responseSpecification).extract().response().jsonPath().getString("data");
        assertThat(data, equalTo("6"));
        System.out.println(data);
    }
}

