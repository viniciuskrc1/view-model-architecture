package course.com.viewmodelarchitectureapp.home

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import course.com.viewmodelarchitectureapp.R
import course.com.viewmodelarchitectureapp.model.Repo
import kotlinx.android.synthetic.main.view_repo_list_item.view.*

class RepoListAdapter(viewModel: ListViewModel?,
                      lifecycleOwner: LifecycleOwner,
                      repoSelectedListener: RepoSelectedListener?) : RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {

    val data = ArrayList<Repo>()
    private var repoSelectedListener : RepoSelectedListener ?= null

    init {
        this.repoSelectedListener = repoSelectedListener
        viewModel?.getRepos()?.observe(lifecycleOwner, Observer {
            data.clear()
            if (it != null) {
                data.addAll(it)
                notifyDataSetChanged()
            }
        })
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_repo_list_item, parent, false)
        return RepoViewHolder(view, repoSelectedListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return data[position].id!!
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class RepoViewHolder(itemView: View, repoSelectedListener: RepoSelectedListener?) : RecyclerView.ViewHolder(itemView) {
        val tvRepoName : TextView = itemView.tv_repo_name
        val tvRepoDescription : TextView = itemView.tv_repo_description
        val tvForks : TextView = itemView.tv_forks
        val tvStars : TextView = itemView.tv_stars
        private var repo : Repo ?= null

        init {
            itemView.setOnClickListener {
                if (repo != null)
                    repoSelectedListener?.onRepoSelected(repo)
            }
        }

        fun bind (repo: Repo) {
            this.repo = repo
            tvRepoName.text = repo.name
            tvRepoDescription.text = repo.description
            tvForks.text = repo.forks.toString()
            tvStars.text = repo.stargazers.toString()
        }
    }

}
