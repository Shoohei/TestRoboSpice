package com.shoohei.robospice.rest.request;

import android.net.Uri;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import com.shoohei.robospice.rest.dto.TestOutput;

/**
 * Test request
 */
public class TestRequest extends SpringAndroidSpiceRequest {

//        private String apiKey;
//        private String countryCode;
//        private String city;

        public TestRequest(/*String apiKey, String countryCode, String city*/) {
            super(TestOutput.class);
//            this.apiKey = apiKey;
//            this.countryCode = countryCode;
//            this.city = city;
        }

        @Override
        public TestOutput loadDataFromNetwork() throws Exception {

            // With Uri.Builder class we can build our url is a safe manner
//            Uri.Builder uriBuilder = Uri.parse(String.format("http://api.wunderground.com/api/%s/conditions/q/%s/%s.json", apiKey, countryCode, city)).buildUpon();

            Uri.Builder uriBuilder = Uri.parse("http://echo.jsontest.com/key/value/otherkey/othervalue").buildUpon();

            String url = uriBuilder.build().toString();

            return getRestTemplate().getForObject(url, TestOutput.class);
        }

        /**
         * This method generates a unique cache key for this request. In this case our cache key depends just on the
         * keyword.
         *
         * @return
         */
        public String createCacheKey() {
            return "TestOutput4"/* + countryCode + "." + city*/;
        }
}
