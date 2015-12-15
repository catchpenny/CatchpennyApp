package cf.catchpenny.catchpennyapp.REST;

import cf.catchpenny.catchpennyapp.Models.JWT;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * Created by mbtam on 12/10/2015.
 */
public interface RESTClient {

    @FormUrlEncoded
    @POST("auth/login")
    Call<JWT> getToken(@Field("email") String email, @Field("password") String password);

    @GET("/user")
    Call<String> getResponse(@Header("Authorization") String authorization);
}
