package abstractfactory2;

import abstractfactory2.abst.Button;
import abstractfactory2.abst.GuiFactory;
import abstractfactory2.abst.TextArea;
import abstractfactory2.concrete.FactoryInstance;

public class Main {
    public static void main(String[] args) {
//        final GuiFactory guiFactory = new LinuxGuiFactory();
//        final GuiFactory guiFactory = new MacGuiFactory();
//        final GuiFactory guiFactory = new WinGuiFactory();
        final GuiFactory guiFactory = FactoryInstance.getGuiFactory();

        final Button button = guiFactory.createButton();
        final TextArea textArea = guiFactory.createTextArea();

        button.click();
        System.out.println("textArea.getText() = " + textArea.getText());

        System.out.println("System.getProperty(\"os.name\") = " + System.getProperty("os.name"));
    }
}
