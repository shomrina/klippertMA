package nightClub;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 *
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

    public void openClub() {
        int countPeople = generatePeopleToday();

        //генерация людей  сразными типами в произвольном порядке
        people = new ArrayList<>();
        for (int i = 0; i < countPeople; i++) {
            people.add(Person.typeOfPerson(new Random().nextInt(1)));   //todo 1 - это число типов людей, доступных в типах. Поменять и лучше вынести в  переменную
        }

        musicAction.changeMusicBySchedule();        //start music

        //перебор людей пока играет музыка
        for (Person person : people) {
            person.doAction(musicAction.getMusicStyle());  //полиморфный вызов метода
        }

        //генерация музыки
      //  for (int i = 0; i < 10; i ++) { } //todo здесь 10 это произвольное число треков. пока = 10. вынести в параметр. или как-то еще определять длительность. хотя  врамках таймера еще большой вопрос что это за число


    }





    public static void main( String[] args )
    {
        NightClub nightClub = new NightClub(150);
        nightClub.openClub();

/*        Person person = new Person();
        person.doAction();*/




    }
}
