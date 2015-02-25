package com.shoohei.robospice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.shoohei.robospice.rest.CustomJacksonSpringAndroidSpiceService;
import com.shoohei.robospice.rest.request.TestRequest;
import com.shoohei.robospice.rest.dto.TestOutput;

import roboguice.util.temp.Ln;


public class MainActivity extends ActionBarActivity {

    private SpiceManager contentManager = new SpiceManager( CustomJacksonSpringAndroidSpiceService.class );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Ln.getConfig().setLoggingLevel(Log.VERBOSE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            performRequest();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void performRequest() {

        TestRequest request = new TestRequest();
        contentManager.execute(request, request.createCacheKey(), DurationInMillis.ONE_MINUTE, new TestRequestListener() );
    }

    private class TestRequestListener implements RequestListener<TestOutput> {

        @Override
        public void onRequestFailure( SpiceException e ) {
            Log.e("TestRequestListener", e.getMessage());
            Toast.makeText(MainActivity.this, "Error during request: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onRequestSuccess(TestOutput testOutput ) {

            if ( testOutput == null) {
                return;
            }

            Toast.makeText(MainActivity.this, "OK : Key : " + testOutput.getKey(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        contentManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        contentManager.shouldStop();
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
