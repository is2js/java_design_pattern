package templatemethod.connectHelper;

public abstract class AbstConnectHelper {
    protected abstract String doSecurity(String info);

    protected abstract boolean authentication(String id, String password);

    protected abstract int authorization(String userName);

    protected abstract String connection(String info);

    // 템플릿 메서드
    public String requestConnection(String encodedInfo) {
        //단계1: 보안 -> 암호화된 문자열을 받아, 디코드 -> id와 password를 할당한다.
        final String decodedInfo = doSecurity(encodedInfo);
        final String id = "aaa";
        final String password = "bbb";

        //단계2: 인증 -> 보내준 id와 password를 검증한다.
        // -> 인증 실패시 에러내서 다음진행못하게
        // my) true시 if문 내부에서 진행(?) -> false시 에러내서 못 진행 + 자동true시 if문 없이 아래에서 이어서 진행
        //     early return = early continue/break(반복문) = early thr!!!
        final boolean isAuthenticated = authentication(id, password);
        if (!isAuthenticated) {
            throw new Error("아이디 암호 불일치");
        }

        //단계3: 권한 -> 정액제 등록한 사람만 접속가능
        // -> 권한은 int로 여러 종류를 가질 수 있다 t/f가 아님.
        final String userName = "userName";
        final int authorization = authorization(userName);
        switch (authorization) {
            case -1: // 10시 제한 정책에 걸린 미성년자
                break;
            case 0: // GM
                break;
            case 1: // 유료 회원 -> 아템 확를 높음. 경험치 많음.
                break;
            case 2: // 무료 회원 -> 아템 확률 낮음. 경험치 적음.
                break;
            case 3: // 권한 없음
                break;
            default: // 기타 for 확장성
                break;

        }
        //
        return connection(decodedInfo);
    }
}
