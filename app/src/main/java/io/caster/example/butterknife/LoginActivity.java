package io.caster.example.butterknife;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

  private static final String KEY_USERNAME = "username";

  @BindView(R.id.container) View container;
  @BindView(R.id.username) EditText username;
  @BindView(R.id.password) EditText password;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);

    if (savedInstanceState == null) {
      username.setText(getStoredUsername());
    }
  }

  @OnClick(R.id.login)
  void onLoginClicked(Button button) {
    button.setEnabled(false);
    button.setText(R.string.label_logging_in);

    login(button, username.getText(), password.getText());
  }

  @OnCheckedChanged(R.id.save_username)
  void onSaveUsernameCheckChanged(boolean checked) {
    storeUsername(checked ? username.getText() : "");
  }

  //- Internal methods

  @Override protected void onResume() {
    super.onResume();
    username.setText(getStoredUsername());
    password.setText("");
  }

  private void login(@Nullable final Button loginButton, CharSequence username, CharSequence password) {
    new AsyncTask<Void, Void, Void>() {
      @Override protected Void doInBackground(Void... params) {

        // Simulate the delay of validating credentials
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        return null;
      }

      @Override protected void onPostExecute(Void aVoid) {
        // restore state
        if (loginButton != null) {
          loginButton.setEnabled(true);
          loginButton.setText(R.string.label_login);
        }

        Snackbar.make(container, "Login complete", Snackbar.LENGTH_SHORT).show();
        //Intent intent = new Intent(LoginActivity.this, ItemListActivity.class);
        //startActivity(intent);
      }
    }.execute();
  }

  private boolean validatePassword(@NonNull String text) {
    return text.trim().length() > 3;
  }

  private void storeUsername(@Nullable CharSequence username) {
    getPreferences().edit()
        .putString(KEY_USERNAME, username.toString())
        .apply();
  }

  private String getStoredUsername() {
    return getPreferences().getString(KEY_USERNAME, "");
  }

  private SharedPreferences getPreferences() {
    return getSharedPreferences(LoginActivity.class.getSimpleName(), Context.MODE_PRIVATE);
  }
}
