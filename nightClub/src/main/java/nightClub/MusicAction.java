package nightClub;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MusicAction extends TimerTask {
    private MusicStyle musicStyle;

    //перечисление доступных музыкальных стилей
    public enum MusicStyle {
        RNB,
        ELECTROHOUSE,
        POP
    }

    public MusicStyle getMusicStyle() {
        return musicStyle;
    }

    //метод для выбора случайной мелодии
    public MusicStyle getRandonMusicStyle() {
        Random random = new Random();
        return MusicStyle.values()[random.nextInt(MusicStyle.values().length)];
    }

    //переопределение метода ран для таска, который в таймер поместим
    @Override
    public void run() {
        MusicStyle m = getRandonMusicStyle();
        this.musicStyle = m;
        System.out.println("currentMusicStyle = " + m);
        //todo допистаь какойто триггер по остановке таймера..как о узнает что программа завершится?
    }


    //метод, сменяющий мелодию каждые 5 секунд
    public void changeMusicBySchedule() {
        new Timer().scheduleAtFixedRate(new MusicAction(), 0L, 5000L);
    }
}
