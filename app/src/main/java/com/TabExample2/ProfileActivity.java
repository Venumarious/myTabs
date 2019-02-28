package com.TabExample2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.eduadministrative.mytabs.R;

public class ProfileActivity extends AppCompatActivity {

    // All xml labels
    TextView txtName;
    TextView txtEmail;
    TextView txtMobile;
    TextView txtAddress;

    // Progress Dialog
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        txtMobile = (TextView) findViewById(R.id.mobile);
        txtAddress = (TextView) findViewById(R.id.address);

        // Loading Profile in Background Thread
        new LoadProfile().execute();

    }

    class LoadProfile extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ProfileActivity.this);
            pDialog.setMessage("Loading profile ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting Profile JSON
         */
        protected String doInBackground(String... args) {
            // Building Parameters

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    // Storing each json item in variable
                    try {
                        String id = "1";
                        String name = "Arhaan Khan";
                        String email = "arhaankhan16@gmail.com";
                        String mobile = "35795184620";
                        String address = "You wont be able to find me";

                        // displaying all data in textview
                        txtName.setText(name);
                        txtEmail.setText("Email: " + email);
                        txtMobile.setText("Mobile: " + mobile);
                        txtAddress.setText("Add: " + address);

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            });

        }
    }
}
