package wen.mvvm.repo;

import androidx.annotation.NonNull;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wen.mvvm.api.ApiResponse;
import wen.mvvm.api.ApiService;
import wen.mvvm.vo.Resource;
import wen.mvvm.vo.User;

public class UserRepo {

    private static UserRepo userRepo = new UserRepo();

    public static UserRepo getInstance() {
        return userRepo;
    }

    public LiveData<Resource<User>> getUser(String userId) {
        MutableLiveData<Resource<User>> userEntityLiveData = new MutableLiveData<>();
        userEntityLiveData.postValue(Resource.loading(null));
        ApiService.INSTANCE.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                ApiResponse apiResponse = new ApiResponse(response);
                if (apiResponse.isSuccessful()) {
                    userEntityLiveData.postValue(Resource.success(response.body()));
                } else {
                    userEntityLiveData.postValue(Resource.error(apiResponse.errorMessage, null));
                }
            }
            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                userEntityLiveData.postValue(Resource.error(t.getMessage(), null));
            }
        });
        return userEntityLiveData;
    }
}
