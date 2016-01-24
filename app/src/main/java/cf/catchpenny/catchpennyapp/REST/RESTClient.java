package cf.catchpenny.catchpennyapp.REST;

import cf.catchpenny.catchpennyapp.Models.JWT;
import cf.catchpenny.catchpennyapp.Models.User;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface RESTClient {

    @FormUrlEncoded
    @POST("auth/login")
    Call<JWT> getToken(@Field("email") String email, @Field("password") String password);

    @GET("user")
    Call<User> getUserInformation();

    /*@GET("d/{did}/c/{cid}")
    Call<Details> getDetails(
            @Path("did") String domain_id,
            @Path("cid") String channel_id,
            @Query("page") Integer page
    );*/
}
