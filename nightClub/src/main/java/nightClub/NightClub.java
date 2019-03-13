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

    public NightClub(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    MusicAction musicAction = new MusicAction();

    //генерация случайного числа посетителей в данный момент. Не превышает максимальное
    private int generatePeopleToday() {
        Random r = new Random();
        return r.nextInt(maxPeople + 1);
    }

    public void openClub(int amountTrack) {
        int countPeople = generatePeopleToday();  //случайное число людей. пришедшах в клуб
        musicAction.setAmountTrack(amountTrack);     //установка общего кол-ва треков поставленных на дискотеке //todo может это надо тоже в конструктор запихнуть?

        //генерация людей  сразными типами в произвольном порядке
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
