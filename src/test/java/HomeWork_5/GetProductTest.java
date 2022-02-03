package HomeWork_5;

import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;

public class GetProductTest {

    static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @SneakyThrows
    @Test
    void getProduct () {
        Response<Product> response = productService.getProductNoId()
                .execute();
        assertThat(response.isSuccessful(),CoreMatchers.is(false));
        System.out.println(response);
    }

}