package course.com.viewmodelarchitectureapp.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import course.com.viewmodelarchitectureapp.model.Repo;

public class SelectedRepoViewModel extends ViewModel {

    private final MutableLiveData<Repo> selectedRepo = new MutableLiveData<>();

    public MutableLiveData<Repo> getSelectedRepo() {
        return selectedRepo;
    }

    public void setSelectedRepo(Repo repo) {
        selectedRepo.setValue(repo);
    }

}
