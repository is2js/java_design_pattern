package factorymethod;

import factorymethod.concrete.HpPotionCreator;
import factorymethod.concrete.MpPotionCreator;
import factorymethod.framework.Item;
import factorymethod.framework.ItemCreator;

public class Main {
    public static void main(String[] args) {
        ItemCreator potionCreator;
        potionCreator = new HpPotionCreator();
        final Item hpPotion = potionCreator.create();

        potionCreator = new MpPotionCreator();
        final Item mpPotion = potionCreator.create();

        hpPotion.use();
        mpPotion.use();

    }
}
