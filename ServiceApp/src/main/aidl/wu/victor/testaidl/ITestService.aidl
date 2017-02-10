// ITestService.aidl
package wu.victor.testaidl;
import wu.victor.testaidl.MyData;
import wu.victor.testaidl.IMyAidlCallback;

// Declare any non-default types here with import statements

interface ITestService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void printMyself();

    int add(int a,int b);

    MyData getMyData();

    void getMyDataAsync();

    void registerCallBack(in IMyAidlCallback callback);
}
