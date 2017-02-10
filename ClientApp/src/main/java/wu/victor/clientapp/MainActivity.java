package wu.victor.clientapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import wu.victor.testaidl.ITestService;
import wu.victor.testaidl.MyData;

public class MainActivity extends AppCompatActivity {

  View bind;
  View unBind;
  View add;
  View data;
  View asyncData;
  ITestService sBinder;

  //用来绑定链接的
  private ServiceConnection conn = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

      // 当绑定时 会从这个回调接口得到  binder对象   通过binder对象绑定的
      sBinder = ITestService.Stub.asInterface(service);
      Log.e("wgn_ServiceConnection", "onServiceConnected():" + "before print");
      try {
        sBinder.printMyself();
      } catch (Exception e) {
        e.printStackTrace();
      }
      Log.e("wgn_ServiceConnection", "onServiceConnected():" + "after print");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
      Log.e("wgn_ServiceConnection", "onServiceDisconnected():" + "ComponentName=" + name);
    }
  };
  ;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    bind = findViewById(R.id.bind);
    unBind = findViewById(R.id.unbind);
    add = findViewById(R.id.add);
    data = findViewById(R.id.data);
    asyncData = findViewById(R.id.asyncdata);
    bind.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("wu.victor.testaidl", "wu.victor.testaidl.TestService"));

        bindService(intent,
            conn, Context.BIND_AUTO_CREATE);
      }
    });

    unBind.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (sBinder != null) {
          unbindService(conn);
          sBinder = null;
        }
      }
    });

    add.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (sBinder != null) {
          //  然后使用 binder对象的方法
          int result = 0;
          try {
            result = sBinder.add(11, 11);
            Log.e("wgn_Test", ":" + "Process.myPid=" + Process.myPid()
                + ",  Process.myTid=" + Process.myTid() + ",  result=" + result);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    });

    data.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (sBinder != null) {
          //  然后使用 binder对象的方法
          try {
            MyData myData = sBinder.getMyData();
            Log.e("wgn_Test", ":" + "Process.myPid=" + Process.myPid()
                + ",  Process.myTid=" + Process.myTid() + ",  myData=" + myData
                + ",  age=" + myData.age
                + ",  name=" + myData.name);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    });

    asyncData.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (sBinder != null) {
          //  然后使用 binder对象的方法
          try {
            sBinder.registerCallBack(new ClientCallback());
            sBinder.getMyDataAsync();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    });

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }
}
