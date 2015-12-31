package net.zhuoweizhang.screenoffbrowser;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class net.zhuoweizhang.screenoffbrowser.BrowserActivityTest \
 * net.zhuoweizhang.screenoffbrowser.tests/android.test.InstrumentationTestRunner
 */
public class BrowserActivityTest extends ActivityInstrumentationTestCase2<BrowserActivity> {

    public BrowserActivityTest() {
        super("net.zhuoweizhang.screenoffbrowser", BrowserActivity.class);
    }

}
