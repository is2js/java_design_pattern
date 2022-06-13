package abstractfactory2.concrete;

import abstractfactory2.abst.Button;
import abstractfactory2.abst.GuiFactory;
import abstractfactory2.abst.TextArea;
import java.util.Locale;

public class FactoryInstance {
    public static GuiFactory getGuiFactory() {
        switch (getOsCode()) {
            case 1:
                return new LinuxGuiFactory();
            case 2:
                return new WinGuiFactory();
            default:
                return new MacGuiFactory();
        }
    }

    private static int getOsCode() {
        final String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        int osNumber = 0;
        if (osName.contains("linux")) {
            osNumber = 1;
        }
        if (osName.contains("win")) {
            osNumber = 2;
        }
        return osNumber;
    }

    static class LinuxButton implements Button {
        @Override
        public void click() {
            System.out.println("리눅스 버튼 클릭");
        }

    }

    static class LinuxTextArea implements TextArea {
        @Override

        public String getText() {
            return "리눅스 텍스트에어리어";
        }
    }

    static class LinuxGuiFactory implements GuiFactory {

        @Override
        public Button createButton() {
            return new LinuxButton();
        }

        @Override
        public TextArea createTextArea() {
            return new LinuxTextArea();
        }

    }

    public static class MacButton implements Button {
        @Override
        public void click() {
            System.out.println("맥 버튼");
        }
    }

    public static class MacTextArea implements TextArea {
        @Override
        public String getText() {
            return "맥 텍스트 에이리어";
        }
    }

    public static class MacGuiFactory implements GuiFactory {
        @Override
        public Button createButton() {
            return new MacButton();
        }

        @Override
        public TextArea createTextArea() {
            return new MacTextArea();
        }
    }

    public static class WinButton implements Button {
        @Override
        public void click() {
            System.out.println("윈도우 버튼");
        }
    }

    public static class WinTextArea implements TextArea {
        @Override
        public String getText() {
            return "윈도우 텍스트 에이리어";
        }
    }

    public static class WinGuiFactory implements GuiFactory {
        @Override
        public Button createButton() {
            return new WinButton();
        }

        @Override
        public TextArea createTextArea() {
            return new WinTextArea();
        }
    }
}
