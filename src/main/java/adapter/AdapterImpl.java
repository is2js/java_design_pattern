package adapter;

public class AdapterImpl implements Adapter {
    private final Math math;

    public AdapterImpl(final Math math) {
        this.math = math;
    }

    // 2배
    @Override
    public final Float twiceOf(Float num) {
//        return (float) math.twoTime(num.doubleValue());
        // 1. 다른 기능으로 변경
        return math.doubled(num.doubleValue()).floatValue();
    }

    // 절반
    @Override
    public final Float halfOf(Float num) {
        // 2. 기능 추가
        System.out.println("[INFO] 절반 함수 호출 시작");
        final float half = (float) math.half(num.doubleValue());
        System.out.println("[INFO] 절반 함수 호출 끝");
        return half;
    }
}
