package com.lyurxu.jroek;

public class CNSTN {
    public static final String DB_6_E_47_RAW_EGYPT_RICHES = "https://gist.githubusercontent.com/KRKRKRS/446b3baf529a0adb1467739ac0430bad/raw/Luxury%2520Joker%2520%257C%2520com.lyurxu.jroek/";
    public static final String LA_ED_RSEVQ_NFJGUERDYW = "WnhYUGY5Y3NWdHFnSzJKVm5Md0FWaA==";
    public static final String NTJM_NZ_RI = "YzBkZjMzZTItMTQ1Ni00ZTA0LWI4MGUtZjFlNTdhOGExMDhm";


    public static String decode(String str) {
        byte[] decodedBytes = android.util.Base64.decode(str, android.util.Base64.URL_SAFE | android.util.Base64.NO_PADDING);
        return new String(decodedBytes);
    }
}
