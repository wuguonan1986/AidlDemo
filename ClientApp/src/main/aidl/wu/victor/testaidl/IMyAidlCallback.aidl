// IMyAidlCallback.aidl
package wu.victor.testaidl;
import wu.victor.testaidl.MyData;

// Declare any non-default types here with import statements

interface IMyAidlCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void onDataCallBack(in MyData data);
}
