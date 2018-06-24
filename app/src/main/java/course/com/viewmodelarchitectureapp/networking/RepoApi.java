package course.com.viewmodelarchitectureapp.networking;

import com.squareup.moshi.KotlinJsonAdapterFactory;
import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RepoApi {

    private static final String  BASE_URL = "http://api.github.com/";
    private static RepoService repoService;
    private static Retrofit retrofit;

    public static RepoService getInstance() {
        if (repoService != null)
            return repoService;
        if (retrofit == null)
            initializeRetrofit();
        repoService = retrofit.create(RepoService.class);
        return repoService;
    }

    private static void initializeRetrofit() {
        Moshi moshi = new Moshi.Builder().add(new KotlinJsonAdapterFactory()).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();
    }

}