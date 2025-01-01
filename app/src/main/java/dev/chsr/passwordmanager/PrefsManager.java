package dev.chsr.passwordmanager;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PrefsManager {

    private static final String PREF_NAME = "password_manager_prefs";
    private static final String PASSWORD_LIST_KEY = "password_list";

    public static void savePasswordList(Context context, List<PasswordItem> passwordList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(passwordList);

        editor.putString(PASSWORD_LIST_KEY, json);
        editor.apply();
    }

    public static List<PasswordItem> getPasswordList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        String json = sharedPreferences.getString(PASSWORD_LIST_KEY, null);

        if (json == null) {
            return new ArrayList<>();
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<PasswordItem>>(){}.getType();
        return gson.fromJson(json, type);
    }
}