package composite;

public class Main {
    public static void main(String[] args) {

        //folders: root, home, garam, music, picture, doc, usr
        //files: track1, track2, pic1, doc1, java
        final Folder root = new Folder("root");
        final Folder home = new Folder("home");
        final Folder garam = new Folder("garam");
        final Folder music = new Folder("music");
        final Folder picture = new Folder("picture");
        final Folder doc = new Folder("doc");
        final Folder usr = new Folder("usr");

        final File track1 = new File("track1");
        final File track2 = new File("track2");
        final File pic1 = new File("pic1");
        final File doc1 = new File("doc1");
        final File java = new File("java");
        // root
        //      home
        //          garam
        //                  music
        //                          track1(file)
        //                          track2(file)
        //                  picture
        //                          pic1(file)
        //                  doc
        //                          doc1(file)
        //      usr
        //          java(file)
        root.addComponent(home);
        home.addComponent(garam);
        garam.addComponent(music);
        music.addComponent(track1);
        music.addComponent(track2);
        garam.addComponent(picture);
        picture.addComponent(pic1);
        garam.addComponent(doc);
        doc.addComponent(doc1);
        root.addComponent(usr);
        usr.addComponent(java);

        show(root);
    }

    private static void show(final Component component) {
        System.out.println(component.getClass().getName()
            + "|"
            + component.getName());

        if (component instanceof Folder) {
            for (final Component child : ((Folder) component).getChildren()) {
                show(child);
            }
        }
    }
}
