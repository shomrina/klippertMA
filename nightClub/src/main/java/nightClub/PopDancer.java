package nightClub;

public class PopDancer extends Person {

    @Override
    public void doAction(MusicAction.MusicStyle musicStyle) {
        if (musicStyle == MusicAction.MusicStyle.POP) {
            System.out.println(toString() + "Я танцую под Pop-музыку");
        }
        else super.doAction(musicStyle);

    }
}
