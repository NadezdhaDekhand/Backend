package HomeWork_6_db.model;

import HomeWork_5.Product;
import HomeWork_5.ProductService;
import HomeWork_5.RetrofitUtils;
import HomeWork_6_db.dao.ProductsMapper;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public class DeleteProduct {
    String resource = "mybatis-config.xml";
    static ProductService productService;
    Product product;
    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle("FOOOOOD1")
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
    }
    @SneakyThrows
    @Test
    void deleteProductTest(){
        Response<Product> response = productService.createProduct(product)
                .execute();
        id = response.body().getId();
        Response<ResponseBody> response1 = productService.deleteProduct(id).execute();
        assertThat(response1.isSuccessful(), CoreMatchers.is(true));

        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        ProductsMapper productsMapper = session.getMapper(ProductsMapper.class);
        ProductsExample example = new ProductsExample();
        example.createCriteria().andTitleEqualTo("FOOOOOD1");
        List<Products> list = productsMapper.selectByExample(example);
        System.out.println(list);
    }

}
