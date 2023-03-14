package ru.samsung.case2022;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StateViewModel extends AndroidViewModel {
    private final MutableLiveData<String> stateUpdateLiveData = new MutableLiveData<>();

    public StateViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getStateUpdateLiveData() {
        return stateUpdateLiveData;
    }

    public void setStateUpdateLiveData(String stateUpdateLiveData) {
        this.stateUpdateLiveData.postValue(stateUpdateLiveData);
    }
}