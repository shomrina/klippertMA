package nightClub;

public class HipHopDancer extends Person {

    @Override
    public void doAction(MusicAction.MusicStyle musicStyle) {
        if (musicStyle == MusicAction.MusicStyle.RNB) {
            System.out.println(toString() + "Я танцую хип-хоп под RnB");
        }
        else super.doAction(musicStyle);

    }
}
