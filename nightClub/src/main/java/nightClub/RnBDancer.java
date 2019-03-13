package nightClub;

public class RnBDancer extends Person {

    @Override
    public void doAction(MusicAction.MusicStyle musicStyle) {
        if (musicStyle == MusicAction.MusicStyle.RNB) {
            System.out.println(toString() + "Я танцую под RnB");
        }
        else super.doAction(musicStyle);

    }
}
