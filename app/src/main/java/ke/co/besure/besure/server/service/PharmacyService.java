package ke.co.besure.besure.server.service;

import java.util.List;

import ke.co.besure.besure.model.Pharmacy;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by chriz on 2/13/2018.
 */

public interface PharmacyService {

    @GET("/API/getPharmacies")
    Call<List<Pharmacy>> get();
}
