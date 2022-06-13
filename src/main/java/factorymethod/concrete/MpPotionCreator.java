package factorymethod.concrete;

import factorymethod.framework.Item;
import factorymethod.framework.ItemCreator;
import java.util.Date;

public class MpPotionCreator extends ItemCreator {
    @Override
    protected void requestItemsInfo() {
        System.out.println("DB에 마력회복물약 정보 요청");
    }

    @Override
    protected Item createItem() {
        return new MpPotion();
    }

    @Override
    protected void createItemLog() {
        System.out.println("마력회복물약을 새로 생성했습니다." + new Date());
    }
}
