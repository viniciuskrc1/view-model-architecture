package course.com.viewmodelarchitectureapp.home

import course.com.viewmodelarchitectureapp.model.Repo

interface RepoSelectedListener {

    fun onRepoSelected(repo: Repo?)
}