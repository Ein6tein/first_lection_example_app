package com.igor.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.igor.app.model.Employee;
import com.igor.app.network.EmployeeRepository;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MyViewModel extends ViewModel {

    private final EmployeeRepository mEmployeeRepository;

    private final MutableLiveData<List<Employee>> mEmployees = new MutableLiveData<>();

    private CompositeDisposable mCompositeDisposable;

    public MyViewModel(EmployeeRepository employeeRepository) {
        mEmployeeRepository = employeeRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public LiveData<List<Employee>> employees() {
        return mEmployees;
    }

    public void getEmployees() {
        mCompositeDisposable.add(mEmployeeRepository.getEmployees().subscribe(mEmployees::postValue));
    }
}
