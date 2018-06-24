package course.com.viewmodelarchitectureapp.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import course.com.viewmodelarchitectureapp.R
import course.com.viewmodelarchitectureapp.home.SelectedRepoViewModel
import kotlinx.android.synthetic.main.screen_details.*

class DetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        displayContent()
    }

    private fun displayContent() {
        val selectedRepoViewModel = ViewModelProviders.of(activity!!).get(SelectedRepoViewModel::class.java)
        selectedRepoViewModel.selectedRepo.observe(this, Observer {
            tv_repo_name.text = it?.name
            tv_repo_description.text = it?.description
            tv_forks.text = it?.forks?.toString()
            tv_stars.text = it?.stargazers?.toString()
        })
    }

}