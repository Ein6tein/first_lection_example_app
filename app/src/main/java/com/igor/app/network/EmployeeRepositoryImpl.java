package com.igor.app.network;

import android.text.TextUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.igor.app.api.EmployeeApi;
import com.igor.app.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;

public class EmployeeRepositoryImpl extends BaseRepository<EmployeeApi> implements EmployeeRepository {

    @Inject public EmployeeRepositoryImpl(Class<EmployeeApi> clazz, OkHttpClient unsafeOkHttpClient, OkHttpClient safeOkHttpClient) {
        super(clazz, unsafeOkHttpClient, safeOkHttpClient);
    }

    @Override public Observable<List<Employee>> getEmployees() {
        return create()
                .getCustomers()
                .map(json -> {
                    ArrayList<Employee> employees = new ArrayList<>();

                    JsonObject jsonObject = json.getAsJsonObject();
                    JsonArray array = jsonObject.get("data").getAsJsonArray();

                    for (int i = 0; i < array.size(); i++) {
                        JsonObject object = array.get(i).getAsJsonObject();
                        Employee employee = new Employee();
                        employee.setId(object.get("id").getAsInt());
                        employee.setName(object.get("employee_name").getAsString());
                        employee.setSalary(object.get("employee_salary").getAsInt());
                        employee.setAge(object.get("employee_age").getAsInt());
                        employee.setImage(object.get("profile_image").getAsString());
                        if (TextUtils.isEmpty(employee.getImage())) {
                            employee.setImage("https://img.freepik.com/free-photo/portrait-white-man-isolated_53876-40306.jpg");
                        }

                        employees.add(employee);
                    }

                    return employees;
                })
                .compose(applySchedulers());
    }
}
