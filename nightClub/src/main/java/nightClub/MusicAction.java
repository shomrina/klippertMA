package nightClub;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/** Класс, описывающий поведение музыки, а также содержащий таймер.
 * Содержит поле для установки числа треков на текущую ночь
 * Также есть список людей, пришедших на дискотеку и метод, определяющий их поведение в зависимости от стиля
 * */

public class MusicAction extends TimerTask {

    private int amountTrack;                            //переменная для определения количества треков за ночь в клубе
    private List<Person> people;                        //значение этого поля задается извне, когда люди сгенерированы
    private int musicIntervalMC = 5000;

    private Timer timer = new Timer();

    //конструктор, получающий на вход список пришедших людей
    public MusicAction(List<Person> people) {
        this.people = people;
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
        MusicStyle m = getRandonMusicStyle();           //генерация случайного типа музыки
        System.out.println("currentMusicStyle = " + m);
        //триггер
        System.out.println(amountTrack--);              //обратный отсчет от заданного кол-ва треков
        if (amountTrack < 0) this.timer.cancel();       //остановка таймера

        //перебор людей пока играет музыка
        for (Person person : this.people) {
            person.doAction(m);                         //полиморфный вызов метода
        }
    }

    /** Метод, запускающий таймер, который активирует метод run()
     * В итоге генерится случайный стиль музыки каждые musicIntervalMC секунд и идет определение действий пришедших в клуб людей.
     * в качестве TimerTask передается на вход scheduleAtFixedRate созданный в методе клуба инстанс MusicAction
     * period (последний параметр) определяет частоту выполнения того, что описано в run().
     * @param amountTrack определяет кол-во запусков того, что лежит в методе run()*/
    public void changeMusicBySchedule(int amountTrack) {
        this.amountTrack = amountTrack;
        this.timer.scheduleAtFixedRate(this, 0, musicIntervalMC);
    }
}
