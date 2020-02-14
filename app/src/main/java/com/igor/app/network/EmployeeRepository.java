package com.igor.app.network;

import com.igor.app.model.Employee;

import java.util.List;

import io.reactivex.Observable;

public interface EmployeeRepository {

    Observable<List<Employee>> getEmployees();
}
