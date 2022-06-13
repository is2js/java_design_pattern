package factorymethod.concrete;

import factorymethod.framework.Item;
import factorymethod.framework.ItemCreator;
import java.util.Date;

public class HpPotionCreator extends ItemCreator {
    @Override
    protected void requestItemsInfo() {
        System.out.println("DB에 체력회복물약 정보 요청");
    }

    @Override
    protected Item createItem() {
        return new HpPotion();
    }

    @Override
    protected void createItemLog() {
        System.out.println("체력회복물약을 새로 생성했습니다." + new Date());
    }
}
