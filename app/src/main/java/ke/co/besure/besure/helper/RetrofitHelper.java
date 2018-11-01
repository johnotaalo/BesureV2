package ke.co.besure.besure.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import ke.co.besure.besure.config.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chriz on 9/12/2017.
 */

public class RetrofitHelper {

    private static RetrofitHelper mInstance;

    public static RetrofitHelper getInstance() {
        if (mInstance == null){
            mInstance = new RetrofitHelper();
        }
        return mInstance;
    }

    public Retrofit createHelper(){
        GsonBuilder gbuilder = new GsonBuilder();
        gbuilder.registerTypeAdapter(Boolean.class, new BooleanTypeAdapter());
        Gson gson = gbuilder.create();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson));

        return builder.build();
    }
}
