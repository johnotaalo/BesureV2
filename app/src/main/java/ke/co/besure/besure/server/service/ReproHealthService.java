package ke.co.besure.besure.server.service;

import java.util.List;

import ke.co.besure.besure.model.ReproHealthResource;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ReproHealthService {
    @GET("/API/getReproductiveHealth")
    Call<List<ReproHealthResource>> get();
}
