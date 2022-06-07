package templatemethod;

import templatemethod.connectHelper.AbstConnectHelper;
import templatemethod.connectHelper.DefaultConnectHelper;

public class Main {
    public static void main(String[] args) {
        final AbstConnectHelper helper = new DefaultConnectHelper();

        helper.requestConnection("아이디 암호가 암호화된 정보");
    }
}
