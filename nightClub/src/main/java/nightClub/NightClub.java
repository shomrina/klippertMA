package nightClub;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Основной класс, описывающий клуб  + метод для запуска программы
 *
 */
public class NightClub
{
    private int maxPeople;   //поле, задающее максимальное кол-во человек в клубе
    private List<Person> people;

    //конструктор. При создании объекта сразу указываем сколько людей этот клуб вмещает
    public NightClub(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    MusicAction musicAction = new MusicAction();

    /**Метод генерации случайного числа посетителей в данный момент. Не превышает максимальное*/
    private int generatePeopleToday() {
        Random r = new Random();
        return r.nextInt(maxPeople + 1);
    }

    /**Основной метод, открывающий клуб, в котормо происходит генерация случайного числа посетителей, запуск и генерация музыки и определение действий
     * @param amountTrack (@code int) передается на вход параметр - сколько мелодий планируется поставить за текущий сеанс*/
    public void openClub(int amountTrack) {
        int countPeople = generatePeopleToday();  //случайное число людей. пришедших в клуб
        musicAction.setAmountTrack(amountTrack);     //установка общего кол-ва треков поставленных на дискотеке //todo может это надо тоже в конструктор запихнуть?

        //генерация людей  сразными типами в произвольном порядке //todo может вынести в отдельный метод? где его лучше разместить? тут в клубе или в персоне? или убрать в MusicAction где я передаю их? или вовсе объелинить с тем методом?
        people = new ArrayList<>();
        for (int i = 0; i < countPeople; i++) {
            people.add(Person.typeOfPerson(new Random().nextInt(5)));   //todo 2 - это число типов людей, доступных в типах. Поменять и лучше вынести в  переменную
        }

        musicAction.setPeople(people);  //передала людей
        musicAction.changeMusicBySchedule(musicAction);        //start music

    }


    public static void main( String[] args )
    {
        NightClub nightClub = new NightClub(50);
        nightClub.openClub(10);

    }
}
