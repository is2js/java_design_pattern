package factorymethod.framework;

public abstract class ItemCreator {

    //템플릿 메소드로 step들 호출하여 Item객체 생성
    public Item create() {
        //1.
        requestItemsInfo();
        //2.
        final Item item = createItem();
        //3.
        createItemLog();
        return item;
    }

    //step들 정의
    //1. DB에 아이템 정보 요청
    abstract protected void requestItemsInfo();

    //2. 아이템 생성
    abstract protected Item createItem();

    //3. 아이템 생성 로그 기록
    abstract protected void createItemLog();

}
