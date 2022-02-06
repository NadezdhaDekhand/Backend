package HomeWork_6_db.model;

import HomeWork_5.*;
import HomeWork_6_db.dao.ProductsMapper;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetCategoryTest {

    static CategoryService categoryService;

    @BeforeAll
    static void beforeAll() {
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows
    @Test
    void getCategoryByIdPositiveTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(100).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));


    }

}
