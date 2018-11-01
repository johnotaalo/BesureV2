package ke.co.besure.besure.server.service;

import java.util.List;

import ke.co.besure.besure.model.FAQ;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FAQService {
    @GET("/API/getFAQs")
    Call<List<FAQ>> get();
}
