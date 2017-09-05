package com.example.varadavamsi.keyboardlock;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class MainActivity extends Activity {

 ConnectionClass connectionClass;
 EditText edtuserid,edtpass;
 Button btnlogin;
 ProgressBar pbbar;


 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_main);

 connectionClass = new ConnectionClass();
 edtuserid = (EditText) findViewById(R.id.edtuserid);
 edtpass = (EditText) findViewById(R.id.edtpass);
 btnlogin = (Button) findViewById(R.id.btnlogin);
 pbbar = (ProgressBar) findViewById(R.id.pbbar);
 pbbar.setVisibility(View.GONE);

 btnlogin.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 DoLogin doLogin = new DoLogin();
 doLogin.execute("");

 }
 });

 }

 public class DoLogin extends AsyncTask<String,String,String>
 {
 String z = "";
 Boolean isSuccess = false;


 String userid = edtuserid.getText().toString();
 String password = edtpass.getText().toString();


 @Override
 protected void onPreExecute() {
 pbbar.setVisibility(View.VISIBLE);
 }

 @Override
 protected void onPostExecute(String r) {
 pbbar.setVisibility(View.GONE);
 Toast.makeText(MainActivity.this,r,Toast.LENGTH_SHORT).show();

 if(isSuccess) {
 Intent i = new Intent(MainActivity.this, AddProducts.class);
 startActivity(i);
 finish();
 }

 }

 @Override
 protected String doInBackground(String... params) {
 if(userid.trim().equals("")|| password.trim().equals(""))
 z = "Please enter User Id and Password";
 else
 {
 try {
 Connection con = connectionClass.CONN();
 System.out.println("connection class");
 System.out.println(con);
 if (con == null) {
 z = "Error in connection with SQL server";
 System.out.println("inside the null value");
 }
 else {
 System.out.println("inside the connection established class");
 String query = "select * from devicestatus where where deviceID = 'BDC6-LX-6296D0D'";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 System.out.println("Query executed");

 if(rs.next())
 {

 z = "Login successfull";

 isSuccess=true;
 }
 else
 {
 z = "Invalid Credentials";
 isSuccess = false;
 }

 }
 }
 catch (Exception ex)
 {
 isSuccess = false;
 z = "Exceptions";
 }
 }
 return z;
 }
 }
}

