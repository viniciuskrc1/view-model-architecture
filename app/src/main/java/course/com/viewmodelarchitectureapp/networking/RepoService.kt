package course.com.viewmodelarchitectureapp.networking

import course.com.viewmodelarchitectureapp.model.Repo
import retrofit2.Call
import retrofit2.http.GET

interface RepoService {

    @GET("orgs/Google/repos")
    fun getRepositories() : Call<List<Repo>>
}