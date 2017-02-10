package wu.victor.clientapp;

import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

import wu.victor.testaidl.IMyAidlCallback;
import wu.victor.testaidl.MyData;

/**
 * Created by wuguonan on 2017/2/9 0009.
 */

public class ClientCallback extends IMyAidlCallback.Stub {

  @Override
  public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

  }

  @Override
  public void onDataCallBack(MyData data) throws RemoteException {
    Log.e("wgn_Test", "onDataCallBack:" + "Process.myPid=" + Process.myPid()
        + ",  Process.myTid=" + Process.myTid() + ",  myData=" + data
        + ",  age=" + data.age
        + ",  name=" + data.name);
  }

  @Override
  public IBinder asBinder() {
    return null;
  }

}
