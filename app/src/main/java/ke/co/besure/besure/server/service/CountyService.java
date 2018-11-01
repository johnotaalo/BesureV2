package ke.co.besure.besure.server.service;

import java.util.List;

import ke.co.besure.besure.model.County;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by chriz on 2/11/2018.
 */

public interface CountyService {
    @GET("/API/getCounties")
    Call<List<County>> get();
}
