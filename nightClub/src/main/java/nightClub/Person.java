package nightClub;

import java.util.Random;

/**Общий класс. объединяющий и описывающий общее поведение людей*/

public class Person {
    private String name;
    private Sex sex;

    /**конструктор, генерирующий случайное имя и пол человека*/
    public Person() {
        this.name = "Person " + (int) (Math.random()*10000);
        this.sex = setRandomSex();
    }

    //варианты для пола человека
     public enum Sex {
        MAN,
        WOMAN
    }

    //Перечисление доступных типов людей
    public enum Dancer {
        HIPHOPDANCER,
        RNBDANCER,
        ELECTRODANCER,
        POPDANCER
    }

   //фабрика, создающая людей разных типов из числа доступных
    public static Person createPerson(Dancer dancer){
        if (dancer == Dancer.HIPHOPDANCER) return new HipHopDancer();
        else if (dancer == Dancer.RNBDANCER) return new RnBDancer();
        else if (dancer == Dancer.ELECTRODANCER) return new ElectroDancer();
        else if (dancer == Dancer.POPDANCER) return new PopDancer();
        else throw new IllegalArgumentException("wrong type");
    }

    //метод для генерации случайного значения поля из енама
    public Sex setRandomSex() {
        Random random = new Random();
        return Sex.values()[random.nextInt(Sex.values().length)];
    }

    /**Метод, определяющий действие человека
     * @param musicStyle  {@code MusicAction} передает играющий стиль музыки, который будет определять действие*/
        public void doAction(MusicAction.MusicStyle musicStyle) {
        System.out.println(toString() + "Я не умею танцевать. Я пойду в бар и буду пить водку.");
        }

    @Override
    public String toString() {
        return name + ", " + sex + ".";
    }
}
