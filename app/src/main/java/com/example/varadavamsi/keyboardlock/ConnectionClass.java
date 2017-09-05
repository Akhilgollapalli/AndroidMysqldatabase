package com.example.varadavamsi.keyboardlock;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import java.sql.Connection;
import android.util.Log;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by varada.vamsi on 9/4/2017.
 */

public class ConnectionClass {
    String ip = "rajdbinstance.cxrdzmiohdqz.us-west-2.rds.amazonaws.com";
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    String db = "trialdatabase";
    String un = "raj";
    String password = "Password";
    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {

            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);
            System.out.println("connection succesfull");
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
            System.out.println(se);
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }
}
