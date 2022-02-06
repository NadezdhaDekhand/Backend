package HomeWork_6_db;

import HomeWork_6_db.dao.CategoriesMapper;
import HomeWork_6_db.model.Categories;
import HomeWork_6_db.model.CategoriesExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Callable;


public class MyBatis {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        CategoriesMapper categoriesMapper = session.getMapper(CategoriesMapper.class);
        CategoriesExample example = new CategoriesExample();

        example.createCriteria().andIdEqualTo(100);
       List<Categories> list = categoriesMapper.selectByExample(example);

        Categories categories = new Categories();
        categories.setId(100);
        categories.setTitle("BOOm");
        categoriesMapper.updateByExample(categories,example);
        System.out.println(list);
    }
}
