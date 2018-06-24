package course.com.viewmodelarchitectureapp.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import course.com.viewmodelarchitectureapp.model.Repo
import course.com.viewmodelarchitectureapp.networking.RepoApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel : ViewModel() {

    private val repos = MutableLiveData<List<Repo>>()
    private val repoLoadError = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()
    private var repoCall : Call<List<Repo>> ?= null

    init {
        fetchRepos()
    }

    fun getRepos(): LiveData<List<Repo>> {
        return repos
    }

    fun getRepoLoadError(): LiveData<Boolean> {
        return repoLoadError
    }

    fun getLoading(): LiveData<Boolean> {
        return loading
    }

    private fun fetchRepos() {
        loading.value = true
        repoCall = RepoApi.getInstance().getRepositories()
        repoCall?.enqueue(object: Callback<List<Repo>?> {
            override fun onResponse(call: Call<List<Repo>?>?, response: Response<List<Repo>?>?) {
                repoLoadError.value = false
                repos.value = response?.body()
                loading.value = false
                repoCall = null
            }

            override fun onFailure(call: Call<List<Repo>?>?, t: Throwable?) {
                Log.e(javaClass.simpleName, "Error loading repos", t)
                repoLoadError.value = true
                loading.value = false
                repoCall = null
            }
        })
    }

    override fun onCleared() {
        if (repoCall != null) {
            repoCall?.cancel()
            repoCall = null
        }
    }

}