package wu.victor.testaidl;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by wuguonan on 2017/2/8 0008.
 */

public class TestService extends Service {

  private  BinServiceBind myBind;
  IMyAidlCallback callback;

  //bind
  public class BinServiceBind extends ITestService.Stub {

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public void printMyself() throws RemoteException {
      Log.e("wgn_BinServiceBind", "printMyself():" + "Process.myPid=" + Process.myPid() + ",  Process.myTid=" + Process.myTid());
      try {
        synchronized (BinServiceBind.this) {
          wait(500);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    @Override
    public IBinder asBinder() {
      return super.asBinder();
    }

    @Override
    public int add(int a,int b) {
      Log.e("wgn_BinServiceBind", "add():" + "Process.myPid=" + Process.myPid() + ",  Process.myTid=" + Process.myTid());
      return a + b;
    }

    @Override
    public MyData getMyData() throws RemoteException {
      MyData myData = new MyData("32", "victor");
      Log.e("wgn_BinServiceBind", "getMyData():" + "Process.myPid=" + Process.myPid()
          + ",  Process.myTid=" + Process.myTid()
          + "myData" + myData);
      return myData;
    }

    @Override
    public void getMyDataAsync() throws RemoteException {
      new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
        @Override
        public void run() {
          if (callback != null) {
            MyData myData = new MyData("32", "victor");
            Log.e("wgn_BinServiceBind", "getMyData():" + "Process.myPid=" + Process.myPid()
                + ",  Process.myTid=" + Process.myTid()
                + "myData" + myData);
            try {
              callback.onDataCallBack(myData);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
      }, 600);

    }

    @Override
    public void registerCallBack(IMyAidlCallback callback) throws RemoteException {
      Log.e("wgn_BinServiceBind", "registerCallBack():" + "Process.myPid="
          + Process.myPid() + ",  Process.myTid=" + Process.myTid());

      TestService.this.callback = callback;
    }
  }



  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return myBind;
  }



  @Override
  public void onCreate() {
    super.onCreate();
    Log.e("wgn_TestService", "onCreate():" + "Process.myPid=" + Process.myPid() + ",  Process.myTid=" + Process.myTid());
    myBind = new BinServiceBind();
  }

  @Override
  public boolean onUnbind(Intent intent) {
    Log.e("wgn_TestService", "onUnbind():" + "Process.myPid=" + Process.myPid() + ",  Process.myTid=" + Process.myTid());
    return super.onUnbind(intent);
  }

  @Override
  public void onDestroy() {
    Log.e("wgn_TestService", "onDestroy():" + "Process.myPid=" + Process.myPid() + ",  Process.myTid=" + Process.myTid());
    super.onDestroy();
  }
}
