package HomeWork_6_db.model;

import HomeWork_5.Product;
import HomeWork_5.ProductService;
import HomeWork_5.RetrofitUtils;
import HomeWork_6_db.dao.ProductsMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class GetProductTest {

    static ProductService productService;
    String resource = "mybatis-config.xml";
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

        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        ProductsMapper productsMapper = session.getMapper(ProductsMapper.class);
        ProductsExample example = new ProductsExample();
        List<Products> list = productsMapper.selectByExample(example);
        System.out.println(list);
    }

}