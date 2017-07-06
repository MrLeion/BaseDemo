package org.tzl.basedemo;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * author: tangzenglei
 * created on: 2017/5/2 下午4:05
 * description:
 */

@SdkSuppress(minSdkVersion = 18)
public class ListViewTest {

    private UiDevice mDevice;
    private static final String BASIC_SAMPLE_PACKAGE
                                                   = "org.tzl.basedemo";

    private static final String BASIC_SAMPLE_ACTIVITY
            = "org.tzl.basedemo.activity.UiAnimatorActivity";
    private static final int LAUNCH_TIMEOUT = 2*60*60*1000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";


    @Before
    public void startMainActivityFromHomeScreen() throws InterruptedException {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
//        Assert.assertThat(launcherPackage, CoreMatchers.notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

//         Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);


//        Thread.sleep(LAUNCH_TIMEOUT);


    }




    @Test
    public void setUp() throws RemoteException {
        // Launch a simple calculator app
//        Context context = getInstrumentation().getContext();
//        Intent intent = context.getPackageManager()
//                .getLaunchIntentForPackage(BASIC_SAMPLE_ACTIVITY);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        // Clear out any previous instances
//        context.startActivity(intent);
//        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_ACTIVITY).depth(0)), LAUNCH_TIMEOUT);


//        new UiObject(new UiSelector()
//        .className("android.widget.ListView")
//                .instance(1)
//        );


        /**
         * ==========================================================================================
         * api---UiDevice
         * ==========================================================================================
         */
//        try {
//        // 输入按键
//        mDevice.pressBack();
//        mDevice.pressBack();
//        mDevice.pressHome();
//            Thread.sleep(1000);
//        mDevice.pressMenu();
//        Thread.sleep(1000);
//        mDevice.pressBack();
//        Thread.sleep(1000);
//        mDevice.pressRecentApps();
//        Thread.sleep(1000);
//        mDevice.pressHome();
//        Thread.sleep(1000);
//        mDevice.click(240, 1100);
//        Thread.sleep(2000);
//        mDevice.click(670, 1100);
//        Thread.sleep(2000);
//        mDevice.pressKeyCode(KeyEvent.KEYCODE_H);
//        Thread.sleep(1000);
//        mDevice.pressKeyCode(KeyEvent.KEYCODE_H, 1);
//        Thread.sleep(1000);
//        mDevice.pressKeyCode(KeyEvent.KEYCODE_J);
//        Thread.sleep(1000);
//        mDevice.pressKeyCode(KeyEvent.KEYCODE_J, 1);
//        Thread.sleep(1000);
//        mDevice.swipe(30, 400, 600, 400, 10);
//        Thread.sleep(1000);
//        mDevice.pressHome();
//        Thread.sleep(1000);
//        mDevice.drag(660, 860, 360, 360, 50);
//        Thread.sleep(1000);
//        mDevice.sleep();
//        Thread.sleep(1000);
//        mDevice.wakeUp();
//        Thread.sleep(1000);
//        mDevice.swipe(370, 1000, 370, 200, 50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        for (int i = 0; i < 20; i++) {

            UiScrollable listview = new UiScrollable(new UiSelector()
                    .className("android.widget.ListView")
            );


//            UiCollection collection = new UiCollection(new UiSelector()
//                    .className("android.widget.ListView"));
//
//
//            UiSelector child = new UiSelector().className("android.widget.TextView");


            try {
                String text = "第" +i+"行";//根据实际情况进行修改
                UiSelector selector = new UiSelector()
                        .text(text);
                UiObject item = listview.getChild(selector);//根据文本识别点击控件
                item.click();

                /**
                 * 设置条目自动向后移动
                 */
                listview.setAsVerticalList();//设置垂直方向滑动
                listview.scrollIntoView(selector);


            } catch (UiObjectNotFoundException e) {
//                e.printStackTrace();

            }



        }

    }












}