package com.github.job11;
import com.github.typeinfo.pets.*;
import com.github.typeinfo.pets.Pet;
import com.github.typeinfo.pets.PetCreator;
import java.util.*;


public class job09 {
    public static void main(String[] args) {
        PetCount.countPets(Pets.creator);

    }

    public static void fun2(int forNum, int toNum){
        System.out.println(((1 + toNum) * toNum / 2) - ( forNum  * (forNum - 1) / 2));
        System.out.println(((1+toNum)*toNum - forNum*(forNum-1))/2);
        System.out.println((toNum + forNum) * (toNum - forNum + 1) / 2);
        return;
    }
}


class LiteraPetCreator extends PetCreator {

    @SuppressWarnings("unchecked")
    public static final List<Class<? extends Pet>> allTypes =
            Collections.unmodifiableList(Arrays.asList(
               Pet.class, Dog.class, Cat.class, Rodent.class,
               Mutt.class, Pug.class, EgyptianMau.class, Manx.class,
               Cymric.class, Rat.class, Mouse.class, Hamster.class
            ));

    public static final List<Class<? extends Pet>> types = allTypes.subList(allTypes.indexOf(Mutt.class),allTypes.size());

    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }
}


class Pets{
    public static final PetCreator creator = new LiteraPetCreator();


    public static Pet randompet(){
        return creator.randomPet();
    }
    public static Pet[] createArray(int size){
        return creator.createArray(size);
    }
    public static ArrayList<Pet> createArrayList(int size){
        return creator.arrayList(size);
    }
}