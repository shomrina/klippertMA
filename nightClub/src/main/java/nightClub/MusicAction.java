package nightClub;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/** Класс, описывающий поведение музыки, а также содержащий таймер.
 * Содержит поля для хранения стиля музыки, число треков на текущую ночью
 * Также есть список людей, пришедших на дискотеку и метод, определяющий их поведение в зависимости от стиля
 * */

public class MusicAction extends TimerTask {
    private MusicStyle musicStyle;
    private int amountTrack;  //переменная для определения количества треков за ночь в клубе
    private List<Person> people; //значение этого поля задается извне, когда люди сгенерированы

    private Timer timer = new Timer();

    //геттеры и сеттеры
    public void setAmountTrack(int amountTrack) {
        this.amountTrack = amountTrack;
    }

    public void setPeople(List people) {
        this.people = people;
    }

    public MusicStyle getMusicStyle() {
        return musicStyle;
    }

    /** Описание доступных музыкальных стилей*/
    public enum MusicStyle {
        RNB,
        ELECTROHOUSE,
        POP
    }

    /** метод для выбора случайной мелодии*/
    public MusicStyle getRandonMusicStyle() {
        Random random = new Random();
        return MusicStyle.values()[random.nextInt(MusicStyle.values().length)];
    }

    //переопределение метода run() для TimerTask. Данный метод выполняется при выполнении scheduleAtFixedRate в Timer
    @Override
    public void run() {
        MusicStyle m = getRandonMusicStyle();
        this.musicStyle = m;
        System.out.println("currentMusicStyle = " + m);
        //триггер
        System.out.println(amountTrack--);  //обратный отсчет от заданного кол-ва треков
        if (amountTrack < 0) this.timer.cancel();  //остановка таймера

        //перебор людей пока играет музыка
        for (Person person : this.people) {  //todo надо выделить в отдельный метод? здесь както в концепт не вписывается его выделение
            person.doAction(getMusicStyle());  //полиморфный вызов метода
        }

    }

    /** Метод, запускающий таймер, который активирует метод run()
     * В итоге генерится случайный стиль музыки каждые 5 секунд и идет определение действий пришедших в клуб людей.
     * @param musicAction (@code MusicAction) передается на вход созданный в методе клуба инстанс MusicAction
     * period (последний параметр) определяет частоту выполнения того, что описано в run().*/
    //метод, сменяющий мелодию каждые 5 секунд
    public void changeMusicBySchedule(MusicAction musicAction) {
        this.timer.scheduleAtFixedRate(musicAction, 0, 5000);  //todo стоит ли 5000 вынести в параметр? и я так и не поняла, он интеджер или лонг?
    }
}
