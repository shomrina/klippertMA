package nightClub;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Основной класс, описывающий клуб  + метод для запуска программы
 */

public class NightClub
{
    private int maxPeople;                                              //поле, задающее максимальное кол-во человек в клубе

    //конструктор. При создании объекта сразу указываем сколько людей этот клуб вмещает
    public NightClub(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    /**Метод генерации случайного числа посетителей в данный момент. Не превышает максимальное*/
    private int generatePeopleToday() {
        Random r = new Random();
        return r.nextInt(maxPeople + 1);
    }

    /**Основной метод, открывающий клуб, в котормо происходит генерация случайного числа посетителей, запуск и генерация музыки и определение действий
     * @param amountTrack (@code int) передается на вход параметр - сколько мелодий планируется поставить за текущий сеанс*/
    public void openClub(int amountTrack) {
        int countPeople = generatePeopleToday();                        //случайное число людей. пришедших в клуб

        //генерация людей  сразными типами в произвольном порядке
        List<Person> people = new ArrayList<>();
        generatePerson(countPeople, people);

        //работа с музыкой
        MusicAction musicAction = new MusicAction(people);              //создается объект класса, в который передаем пришедших людей
        musicAction.changeMusicBySchedule(amountTrack);                 //start music
    }

    public void generatePerson(int countPeople, List<Person> people) {
        Random random = new Random();
        Person.Dancer[] dancers = Person.Dancer.values();
        for (int i = 0; i < countPeople; i++) {
            people.add(Person.createPerson(dancers[random.nextInt(dancers.length)]));
        }
    }

    public static void main( String[] args )
    {
        NightClub nightClub = new NightClub(50);
        nightClub.openClub(10);
    }
}
