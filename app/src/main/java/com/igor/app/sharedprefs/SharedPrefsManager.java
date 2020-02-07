package com.igor.app.sharedprefs;

import javax.inject.Inject;

public class SharedPrefsManager {

    @Inject public SharedPrefsManager() {}

    public String accessSharedPrefs() {
        return "We accessed SharedPrefs via Dagger";
    }
}
