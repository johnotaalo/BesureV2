package ke.co.besure.besure.server.service;

import java.util.List;

import ke.co.besure.besure.model.HIVFact;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HIVFactsService {
    @GET("/API/getHIVFacts")
    Call<List<HIVFact>> get();
}
