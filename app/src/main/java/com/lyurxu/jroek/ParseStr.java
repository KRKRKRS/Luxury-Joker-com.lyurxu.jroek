package com.lyurxu.jroek;

import static com.lyurxu.jroek.F_B_K.AD_ID;
import static com.lyurxu.jroek.LJ.AppsFl_Id;

import com.onesignal.OneSignal;

public class ParseStr {

    String[] keys = {CNSTN.decode("Pw=="), CNSTN.decode("JnN1YjY9"), CNSTN.decode("JnN1Yjc9"), CNSTN.decode("JnN1YjI9"), CNSTN.decode("JnN1YjM9"), CNSTN.decode("JnN1YjQ9"), CNSTN.decode("JnN1YjU9")};


    String parse(String input) {
        String[] params = input.split("::");
        StringBuilder result = new StringBuilder();
        result.append(params[0]).append("?")
                .append(CNSTN.decode("YnVuZGxlPQ==")).append(CNSTN.decode("Y29tLmV0Z3B5LnJzaWVjaA=="))
                .append(CNSTN.decode("JmFkX2lkPQ==")).append(AD_ID)
                .append(CNSTN.decode("JmFwcHNfaWQ9")).append(AppsFl_Id)
                .append(CNSTN.decode("JmRldl9rZXk9")).append(CNSTN.decode(CNSTN.AFKey));

        for (int i = 1; i < params.length; i++) {
            result.append(keys[i]).append(params[i]);
        }

        String teamName = params[1];
        OneSignal.sendTag(CNSTN.decode("c3ViX2FwcA=="), teamName);
        return String.valueOf(result);
    }

}
