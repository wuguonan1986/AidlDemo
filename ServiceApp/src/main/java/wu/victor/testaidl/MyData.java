package wu.victor.testaidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wuguonan on 2017/2/9 0009.
 */

public class MyData implements Parcelable {
  String age;
  String name;

  public  MyData(String age, String name) {
    this.age = age;
    this.name = name;
  }

  protected MyData(Parcel in) {
    age = in.readString();
    name = in.readString();
  }

  public static final Creator<MyData> CREATOR = new Creator<MyData>() {
    @Override
    public MyData createFromParcel(Parcel in) {
      return new MyData(in);
    }

    @Override
    public MyData[] newArray(int size) {
      return new MyData[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(age);
    dest.writeString(name);
  }
}
