package nightClub;

public class ElectroDancer extends Person {

    @Override
    public void doAction(MusicAction.MusicStyle musicStyle) {
        if (musicStyle == MusicAction.MusicStyle.ELECTROHOUSE) {
            System.out.println(toString() + "Я танцую под ELECTROHOUSE");
        }
        else super.doAction(musicStyle);

    }
}
