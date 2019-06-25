package com.github.java11;

import java.util.ArrayList;

/*
空对象:
    将每一个空对象作为成员占位,等到合适的时候覆盖他们
    尽管局部会有缺陷,整体依然能运转
 */

public class job17 {
    public static void main(String[] args) {
        Staff staff = new Staff("President", "CTO", "Marketing Manager", "Product Manager",
                "Project lead", "Software Engineer", "Software Engineer", "Software Engineer",
                "Software Engineer", "Test Engineer", "Technical Writer");

        staff.fillPosition("President", new Person("Me","Last","The Top, Lonely At"));

        staff.fillPosition("Project lead",new Person("Janet","Planner","The Burbs"));

        if (staff.positionAvailable("Software Engineer")) {
            staff.fillPosition("Software Engineer",new Person("Bob","Coder","Bright Light city"));
        }

        for (Position position : staff) {
            System.out.println(position);
        }


    }
}


class Position{
    private String title;
    private Person person;

    public Position(String title, Person person) {
        this.title = title;
        this.person = person;
        if (this.person == null){
            this.person =Person.Null;
        }
    }

    public Position(String title) {
        this.title = title;
        this.person = Person.Null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        if(this.person == null){
            this.person = Person.Null;
        }
    }

    @Override
    public String toString() {
        return "Position{" +
                "title='" + title + '\'' +
                ", person=" + person +
                '}';
    }
}


class Staff extends ArrayList<Position>{
    public void add(String title,Person person){
        add(new Position(title,person));
    }

    public void add(String...titles){
        for (String title : titles) {
            add(new Position(title));
        }
    }

    public Staff(String...titles){
        add(titles);
    }

    public boolean positionAvailable(String title){
        for (Position position : this) {
            if(position.getTitle().equals(title) && position.getPerson() == Person.Null){
                return true;
            }
        }
        return false;
    }

    public void fillPosition(String title, Person hire){
        for (Position position : this) {
            if (position.getTitle().equals(title) && position.getPerson() == Person.Null) {
                position.setPerson(hire);
                return;
            }
        }
        throw new RuntimeException(
                "Position " + title + " not available"
        );
    }

}
