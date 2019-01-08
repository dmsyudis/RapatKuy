package id.duakoma.com.meetsquare;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import libs.mjn.prettydialog.PrettyDialog;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    Button buttonLogin;
    JSONArray arrayData;
    AutoCompleteTextView mEmailView;
    EditText mPasswordView;
    TextView text_skip;
    id.duakoma.com.meetsquare.http.Process process = new id.duakoma.com.meetsquare.http.Process();
    private ProgressDialog mProgress;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);
        text_skip = (TextView)findViewById(R.id.text_skip);
        mEmailView = (AutoCompleteTextView)findViewById(R.id.email) ;
        mPasswordView = (EditText)findViewById(R.id.password);
        buttonLogin = (Button)findViewById(R.id.email_sign_in_button);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmailView.setError(null);
                mPasswordView.setError(null);
                View focusView = null;
                boolean cancel = false;
                //mProgress.show();
                /*
                Runnable progressRunnable = new Runnable() {

                    @Override
                    public void run() {
                        mProgress.cancel();
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 2000);
                */

                try {
                    String sUser = mEmailView.getText().toString();
                    String sPass = mPasswordView.getText().toString();
                    String prosesLogin = process.login(sUser,sPass);
                    //System.out.println("proseslogin: "+prosesLogin);
                    //Toast.makeText(LoginActivity.this, prosesLogin, Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject = new JSONObject(prosesLogin);
                    String masuk = jsonObject.getString("error");
                    //System.out.println("proseslogin masuk: "+masuk);
                    if (TextUtils.isEmpty(sUser) && TextUtils.isEmpty(sPass)) {
                        mEmailView.setError(getString(R.string.error_field_required));
                        mPasswordView.setError(getString(R.string.error_field_required));
                        focusView = mEmailView;
                        focusView = mPasswordView;
                        cancel = true;
                    }else if(TextUtils.isEmpty(sUser)){
                        mEmailView.setError(getString(R.string.error_field_required));
                        focusView = mEmailView;
                        cancel = true;
                    }else if(TextUtils.isEmpty(sPass)){
                        mPasswordView.setError(getString(R.string.error_field_required));
                        focusView = mPasswordView;
                        cancel = true;
                    }

                    else if(masuk.equalsIgnoreCase("false")){
                        new PrettyDialog(LoginActivity.this)
                                .setTitle("Login Berhasil !")
                                .setIcon(R.drawable.pdlg_icon_success)
                                .setIconTint(R.color.colorFix)
                                .setMessage("Loading . . .")
                                .show();
                        Intent intent = new Intent(LoginActivity.this,RoomActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        new PrettyDialog(LoginActivity.this)
                                .setTitle("Login Gagal !")
                                .setIcon(R.drawable.pdlg_icon_close)
                                .setIconTint(R.color.pdlg_color_red)
                                .setMessage("Username atau Password Salah")
                                .show();
                        //mProgress.dismiss();
                        //Toast.makeText(LoginActivity.this,"Username atau Password Salah", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        text_skip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RoomActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
