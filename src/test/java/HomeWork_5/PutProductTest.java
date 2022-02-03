package HomeWork_5;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PutProductTest {
    static ProductService productService;
    Product product;
    Faker faker = new Faker();
    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
    }

    @SneakyThrows
    @Test
    void putProductTest() {
        Response<Product> response = productService.createProduct(product)
                .execute();
        id = response.body().getId();
        System.out.println(product.getTitle() + " " + id);
        product = new Product()
                .withId(id)
                .withTitle("BOOOM")
                .withCategoryTitle("Food")
                .withPrice(12000);
        Response<Product> response1 = productService.putProduct(product).execute();

        assertThat(response1.isSuccessful(), CoreMatchers.is(true));
        assertThat(response1.body().getTitle(), equalTo("BOOOM"));
        assertThat(response1.body().getId(), equalTo(id));
        System.out.println(product.getTitle() + " " + id );
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}