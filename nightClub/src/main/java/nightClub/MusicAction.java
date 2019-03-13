package nightClub;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MusicAction extends TimerTask {
    private MusicStyle musicStyle;
    private int amountTrack;  //переменная для определения количества треков за ночь в клубе
    private Timer timer = new Timer();

    public void setAmountTrack(int amountTrack) {
        this.amountTrack = amountTrack;
    }

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
        //триггер
        System.out.println(amountTrack--);  //обратный отсчет от заданного кол-ва треков
        if (amountTrack < 0) this.timer.cancel();  //остановка таймера


    }


    //метод, сменяющий мелодию каждые 5 секунд
    public void changeMusicBySchedule() {
        this.timer.scheduleAtFixedRate(new MusicAction(), 0, 5000); //todo или L приписать к цифрам надо?
    }
}
