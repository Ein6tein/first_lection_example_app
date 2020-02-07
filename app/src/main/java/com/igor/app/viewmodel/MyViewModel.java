package com.igor.app.viewmodel;

import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private static final String[] DATA = {
            "Option 1",
            "Icon 1",
            "Option 2",
            "Icon 2",
            "Option 3",
            "Icon 3",
            "Option 4",
            "Icon 4",
            "Option 5",
            "Icon 5",
            "Option 6",
            "Icon 6",
    };

    public String[] getData() {
        return DATA;
    }
}
