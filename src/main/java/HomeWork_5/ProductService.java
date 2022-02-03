package HomeWork_5;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;


public interface ProductService {

    @GET("products/{id}")
    Call<Product> getProductId(@Path("id") int id);

    @POST("products")
   Call<Product> createProduct(@Body Product createProductRequest);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);

    @PUT("products")
    Call<Product> putProduct(@Body Product createProductRequest);

    @GET("products")
    Call<Product> getProductNoId();

}