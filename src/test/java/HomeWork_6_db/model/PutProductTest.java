package HomeWork_6_db.model;

import HomeWork_5.Product;
import HomeWork_5.ProductService;
import HomeWork_5.RetrofitUtils;
import HomeWork_6_db.dao.ProductsMapper;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PutProductTest {
    String resource = "mybatis-config.xml";
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
                .withTitle("BOOOMs")
                .withCategoryTitle("Food")
                .withPrice(12000);
        Response<Product> response1 = productService.putProduct(product).execute();

        assertThat(response1.isSuccessful(), CoreMatchers.is(true));
        assertThat(response1.body().getTitle(), equalTo("BOOOMs"));
        assertThat(response1.body().getId(), equalTo(id));
        System.out.println(product.getTitle() + " " + id );

        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        ProductsMapper productsMapper = session.getMapper(ProductsMapper.class);
        ProductsExample example = new ProductsExample();
        example.createCriteria().andTitleEqualTo("BOOOMs");
        List<Products> list = productsMapper.selectByExample(example);
        System.out.println(list);
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}