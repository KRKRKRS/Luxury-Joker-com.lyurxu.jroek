package com.lyurxu.jroek;

import static com.lyurxu.jroek.LJ.AD_IDlyurxu;
import static com.lyurxu.jroek.LJ.AppsFl_Idlyurxu;
import static com.lyurxu.jroek.LJ.keyDefaultlyurxu;

import com.onesignal.OneSignal;

public class ParseStrlyurxu {

    String[] keyslyurxu = {decodlyurxue("Pw=="), decodlyurxue("JnN1YjY9"), decodlyurxue("JnN1Yjc9"), decodlyurxue("JnN1YjI9"), decodlyurxue("JnN1YjM9"), decodlyurxue("JnN1YjQ9"), decodlyurxue("JnN1YjU9")};

    String parselyurxu(String input) {
        String[] paramslyurxu = input.split("::");
        StringBuilder resultlyurxu = new StringBuilder();
        resultlyurxu.append(paramslyurxu[0]).append("?")
                .append(decodlyurxue("YnVuZGxlPQ==")).append(decodlyurxue("Y29tLmx5dXJ4dS5qcm9law=="))
                .append(decodlyurxue("JmFkX2lkPQ==")).append(AD_IDlyurxu)
                .append(decodlyurxue("JmFwcHNfaWQ9")).append(AppsFl_Idlyurxu)
                .append(decodlyurxue("JmRldl9rZXk9")).append(decodlyurxue(CNSTNlyurxu.AFKeylyurxu));

        for (int i = 1; i < paramslyurxu.length; i++) {
            resultlyurxu.append(keyslyurxu[i]).append(paramslyurxu[i]);
        }

        String teamNamelyurxu = paramslyurxu[1];
        OneSignal.sendTag(decodlyurxue("c3ViX2FwcA=="), teamNamelyurxu);
        return String.valueOf(resultlyurxu);
    }


  public  String parseOrganiclyurxu(String inputlyurxu) {
        return inputlyurxu + keyDefaultlyurxu +
                decodlyurxue("P2J1bmRsZT0=") + "com.lyurxu.jroek" +
                decodlyurxue("JmFkX2lkPQ==") + AD_IDlyurxu +
                decodlyurxue("JmFwcHNfaWQ9") + AppsFl_Idlyurxu +
                decodlyurxue("JmRldl9rZXk9") + decodlyurxue(CNSTNlyurxu.AFKeylyurxu);
    }

    public static String decodlyurxue(String str) {
        byte[] decodedBytes = android.util.Base64.decode(str, android.util.Base64.URL_SAFE | android.util.Base64.NO_PADDING);
        return new String(decodedBytes);
    }
}
