package io.github.mwaggett.snapechat;

import android.app.Application;

import com.parse.Parse;

public class SnapechatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "2UuRpLD4E3H0WReSgfqlCU7VgfYxATlqUgdQ9t04", "MEbPyquPOBUmbviiPQaSmNwCNbDYESNX6OmYjWzx");
    }
}
