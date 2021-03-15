/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText user;
    EditText pass;
    ImageView imageView;
    RelativeLayout background;

    public void showUserList() {
        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.background || view.getId() == R.id.imageView) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void logIn(View v) {
        if(user.getText().toString().matches("") || pass.getText().toString().matches("")) {
            Toast.makeText(this, "Both a username and password are required", Toast.LENGTH_SHORT).show();
        } else {
            ParseUser.logInInBackground(user.getText().toString(), pass.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if (e == null && parseUser != null) {
                        Log.i("Success", parseUser.getUsername() + " logged in!");
                        showUserList();
                    } else {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Incorrect username/password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void signUp(View v) {
        if(user.getText().toString().matches("") || pass.getText().toString().matches("")) {
            Toast.makeText(this, "Both a username and password are required.", Toast.LENGTH_SHORT).show();
        } else {
            ParseUser newUser = new ParseUser();
            newUser.setUsername(user.getText().toString());
            newUser.setPassword(pass.getText().toString());
            newUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.i("Success", user.getText().toString() + " signed up!");
                        showUserList();
                    } else {
                        Toast.makeText(MainActivity.this, "This username is taken already.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setTitle("Social Media");

    /*ParseObject score = new ParseObject("Score");
    score.put("username", "Gabriela" );
    score.put("score", 65);
    score.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if(e == null) {
          //Ok
          Log.i("Success", "We saved the score");
        } else {
          e.printStackTrace();
        }
      }
    });

      ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
      query.getInBackground("PYPFG0t6Vj", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject parseObject, ParseException e) {
              if(e == null && parseObject != null) {

                  //update score
                  parseObject.put("score", 85);
                  parseObject.saveInBackground();

                  Log.i("username", parseObject.getString("username"));
                  Log.i("score", Integer.toString(parseObject.getInt("score")));
              }
          }
      });*/

    /*ParseObject tweet = new ParseObject("Tweet");
    tweet.put("username", "Stael");
    tweet.put("tweet", "Thank You BasedGod!");
    tweet.saveInBackground(new SaveCallback() {
        @Override
        public void done(ParseException e) {
            if (e == null) {
                Log.i("Successful", "Successfully saved");
            } else {
                e.printStackTrace();
            }
        }
    });


    ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweet");
    query.getInBackground("SOOqcAP5sv", new GetCallback<ParseObject>() {
        @Override
        public void done(ParseObject parseObject, ParseException e) {
            if(e == null && parseObject != null) {

                parseObject.put("tweet", "All Hail The Mighty Thor!");
                parseObject.saveInBackground();

                Log.i("Username", parseObject.getString("username"));
                Log.i("Tweet", parseObject.getString("tweet"));
            }
        }
    });
    */

    //Grab all the scores
     /* ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

      query.whereEqualTo("username", "Gabriela");
      //In case of multiple Gabriela's
      query.setLimit(1);

      query.findInBackground(new FindCallback<ParseObject>() {
          @Override
          public void done(List<ParseObject> list, ParseException e) {
              if(e == null) {
                  if(list.size() > 0) {
                      for(ParseObject object : list) {
                          Log.i("username", object.getString("username"));
                          Log.i("score", Integer.toString(object.getInt("score")));
                      }
                  }
              }
          }
      });


     ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
     query.whereLessThan("score", 70);
     query.findInBackground(new FindCallback<ParseObject>() {
         @Override
         public void done(List<ParseObject> list, ParseException e) {
             if(e == null) {
                 if(list.size() > 0) {
                     for(ParseObject object : list) {
                        object.put("score", object.getInt("score") + 20);
                        object.saveInBackground();
                         Log.i("username", object.getString("username"));
                         Log.i("score", Integer.toString(object.getInt("score")));
                     }
                 }
             }
         }
     });*/

     //Setting up usernames and password as well as logging in
     /*
     ParseUser user = new ParseUser();
     user.setUsername("Stael");
     user.setPassword("password");

     user.signUpInBackground(new SignUpCallback() {
         @Override
         public void done(ParseException e) {
             if(e == null) {
                 //OK
                 Log.i("Sign Up OK!", "We Made It!");
             } else {
                 e.printStackTrace();
             }
         }
     });

     ParseUser.logInInBackground("Stael", "password", new LogInCallback() {
         @Override
         public void done(ParseUser parseUser, ParseException e) {
             if(parseUser != null) {
                 Log.i("Success", "We logged in!");
             } else {
                 e.printStackTrace();
             }
         }
     });

     */

     user = (EditText) findViewById(R.id.userfield);
     pass = (EditText) findViewById(R.id.passfield);
     imageView = (ImageView) findViewById(R.id.imageView);
     background = (RelativeLayout) findViewById(R.id.background);

     imageView.setOnClickListener(this);
     background.setOnClickListener(this);


     if(ParseUser.getCurrentUser() != null) {
         Log.i("Signed In", ParseUser.getCurrentUser().getUsername());
         showUserList();
     } else {
         Log.i("No Luck", "Not signed in  :-(");
     }
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}