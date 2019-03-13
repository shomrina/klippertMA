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

    public Sex getSex() {
        return sex;
    }

    //Вспомогательный метод. перечисление возможных типов людей. приходящих в клуб и подчиняющихся типу person для генерации случайного выбора
    public static Person typeOfPerson(int type){
        if (type == 0) return new Person();
        else if (type == 1) return new HipHopDancer(); //todo добавить еще танцоров сюда. когда будут созданы
        else if (type == 2) return new RnBDancer();
        else if (type == 3) return new ElectroDancer();
        else if (type == 4) return new PopDancer();
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
