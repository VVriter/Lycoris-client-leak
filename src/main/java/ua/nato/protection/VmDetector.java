package ua.nato.protection;

import java.lang.reflect.Method;

public class VmDetector {
  /*  public static String detectVMWare() {
        final String[] array = { System.getenv("PROCESSOR_IDENTIFIER"), System.getenv("NUMBER_OF_PROCESSORS"), HWID1(), HWID2(), HWID4(), method_1937(), HWID4() };
        final StringBuilder sb = new StringBuilder();
        final String[] array2;
        final int length = (array2 = array).length;
        int i = 0;
        int n = 0;
        while (i < length) {
            final String s = array2[n];
            final StringBuilder sb2 = sb;
            sb2.append(s);
            if (sb2.toString().contains("VMware")) {
                FutureMessage.showMessageBox("Error!", "Virtual machine detected! You may not run Future on a virtual computer", 0);
                final String s2 = "java.lang.Shutdown";
                try {
                    final Method declaredMethod = Class.forName(s2).getDeclaredMethod("exit", Integer.TYPE);
                    final Object o = null;
                    final Method method = declaredMethod;
                    method.setAccessible(true);
                    method.invoke(o, 0);
                }
                catch (Exception ex) {
                    throw new RuntimeException("Failed to load! Please post on the forums with the error code \"0x38F\" to get help.");
                }
            }
            i = ++n;
        }
        return sb.toString();
    } */
}
