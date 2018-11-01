package ke.co.besure.besure.server.service;

import java.util.List;

import ke.co.besure.besure.model.Facility;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FacilityService {
    @GET("/API/getFacilitiesByCounty/{county}")
    Call<List<Facility>> get(@Path("county") String county);
}
