package course.com.viewmodelarchitectureapp.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import course.com.viewmodelarchitectureapp.R
import course.com.viewmodelarchitectureapp.details.DetailsFragment
import course.com.viewmodelarchitectureapp.model.Repo
import kotlinx.android.synthetic.main.screen_list.*

class ListFragment : Fragment(), RepoSelectedListener {

    private var viewModel: ListViewModel ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recycler_view.adapter = RepoListAdapter(viewModel, this, this)
        recycler_view.layoutManager = LinearLayoutManager(context)
        observerViewModel()
    }

    override fun onRepoSelected(repo: Repo?) {
        val selectedRepoViewModel = ViewModelProviders.of(activity!!).get(SelectedRepoViewModel::class.java)
        selectedRepoViewModel.setSelectedRepo(repo)
        activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.screen_container, DetailsFragment())
                ?.addToBackStack(null)
                ?.commit()
    }

    private fun observerViewModel() {
        viewModel?.getRepos()?.observe(this, Observer {
            if (it != null)
                recycler_view.visibility = View.VISIBLE
        })
        viewModel?.getRepoLoadError()?.observe(this, Observer {
            if (it != null && it) {
                tv_error.visibility = View.VISIBLE
                recycler_view.visibility = View.GONE
                tv_error.text = getString(R.string.api_error_repos)
            } else {
                tv_error.visibility = View.GONE
                tv_error.text = null
            }
        })
        viewModel?.getLoading()?.observe(this, Observer {
            if (it != null && it) {
                loading_view.visibility = View.VISIBLE
                tv_error.visibility = View.GONE
                recycler_view.visibility = View.GONE
            } else {
                loading_view.visibility = View.GONE
            }
        })
    }

}