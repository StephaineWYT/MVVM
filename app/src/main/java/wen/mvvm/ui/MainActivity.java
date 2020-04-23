package wen.mvvm.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import wen.mvvm.R;
import wen.mvvm.databinding.ActivityMainBinding;
import wen.mvvm.vo.Resource;
import wen.mvvm.vo.User;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainBinding.setEventHandler(new MainEventHandler(this));

        LiveData<Resource<User>> user = mainViewModel.getUser();
        user.observe(this, userResource -> {
            mainBinding.setLoadStatus(userResource == null ? null : userResource.status);
            mainBinding.setUser(userResource == null ? null : userResource.data);
            mainBinding.setResource(userResource);
        });
    }

    void onSearchUser(String text) {
        mainViewModel.setUserName(text);
    }
}
