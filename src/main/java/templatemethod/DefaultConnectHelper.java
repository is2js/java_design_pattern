package templatemethod;

public class DefaultConnectHelper extends AbstConnectHelper {
    @Override
    protected String doSecurity(final String info) {
        System.out.println("디코드");
        return info;
    }

    @Override
    protected boolean authentication(final String id, final String password) {
        System.out.println("DB정보를 불러와 아이디/암호 확인 과정");
        return true; //여기선 무조건 맞는 id/passwd라고 가정하고 true만 넘긴다.
    }

    @Override
    protected int authorization(final String userName) {
        System.out.println("권한 확인");
        return 0; // 여기선 Gm만 접속했다고 가정하고 0만 넘긴다.
    }

    @Override
    protected String connection(final String info) {
        System.out.println("[마지막 접속 단계] 원래는확인이 다 끝난 친구로서, 스레드나 접속 주소를 넘겨준다.");
        return null;
    }
}
