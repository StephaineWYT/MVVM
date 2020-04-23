package wen.mvvm.ui;

public class MainEventHandler {

    private MainActivity mainActivity;

    MainEventHandler(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void onTextSubmit(String text) {
        mainActivity.onSearchUser(text);
    }
}
