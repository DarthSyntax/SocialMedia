/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("f4a20ff29529203ff51b729e30219f129ba72466")
            .clientKey("e857989a3666e89e8af3a303da1afe2f7b66ea49")
            .server("http://18.218.15.110:80/parse/")
            .build()
    );

    /*Setting Bitnami application password to 'M4dPLpK8LTnv'         #
    [   15.303134] bitnami[1162]: #        (the default application username is 'user')
    This is used to login to http://18.218.15.110:80/apps
    */




    //ParseUser.enableAutomaticUser();

    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true);
    defaultACL.setPublicWriteAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);

  }
}
