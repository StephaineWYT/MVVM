package wen.mvvm.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import wen.mvvm.repo.UserRepo;
import wen.mvvm.vo.Resource;
import wen.mvvm.vo.User;

public class MainViewModel extends ViewModel {

    private final UserRepo userRepo = UserRepo.getInstance();
    private final MutableLiveData<String> userNameLiveData = new MutableLiveData<>();
    private final LiveData<Resource<User>> userEntityLiveData;

    public MainViewModel() {
        userEntityLiveData = Transformations.switchMap(userNameLiveData, input -> {
            if (input == null) {
                return new MutableLiveData<>();
            } else {
                return userRepo.getUser(input);
            }
        });
    }

    LiveData<Resource<User>> getUser() {
        return userEntityLiveData;
    }

    void setUserName(String userName) {
        userNameLiveData.postValue(userName);
    }
}
