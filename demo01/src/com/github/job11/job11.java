package com.github.job11;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



/*
Factory<? extends Part> 此处意为下边界:
    指继承自Part的类或Part都可以

注册工厂:
    将工厂类注册进父类容器中统一交由父类的容器管理
k
 */
public class job11 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Part.createRandom());
        }
    }
}


interface Factory<T>{
    T create();
}


class Part{
    public String toString(){
        return getClass().getSimpleName();
    }

    static List<Factory<? extends Part>> partFactories = new ArrayList<>();
    static {
        partFactories.add(new FanBelt.ThisFactory());
        partFactories.add(new AirFilter.ThisFactory());
        partFactories.add(new CabinAirFilter.ThisFactory());
        partFactories.add(new OilFilter.ThisFactory());
        partFactories.add(new FanBelt.ThisFactory());
        partFactories.add(new PowerSteeringBelt.ThisFactory());
        partFactories.add(new GeneratFanBelt.ThisFactory());
    }

    private static Random rand = new Random(47);
    public static Part createRandom(){
        int n = rand.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }
}


class Filter extends Part{}


class FuelFilter extends Filter{
    static class ThisFactory implements Factory<FuelFilter>{
        @Override
        public FuelFilter create() {
            return new FuelFilter();
        }
    }
}


class AirFilter extends Filter{
    static class ThisFactory implements Factory<AirFilter>{
        @Override
        public AirFilter create() {
            return new AirFilter();
        }
    }
}


class CabinAirFilter extends Filter{
    static class ThisFactory implements Factory<CabinAirFilter>{
        @Override
        public CabinAirFilter create() {
            return new CabinAirFilter();
        }
    }
}


class OilFilter extends Filter{
    static class ThisFactory implements Factory<OilFilter>{
        @Override
        public OilFilter create() {
            return new OilFilter();
        }
    }
}


class Belt extends Part{}


class FanBelt extends Belt{
    static class ThisFactory implements Factory<FanBelt>{
        @Override
        public FanBelt create() {
            return new FanBelt();
        }
    }
}


class GeneratFanBelt extends Belt{
    static class ThisFactory implements Factory<GeneratFanBelt>{
        @Override
        public GeneratFanBelt create() {
            return new GeneratFanBelt();
        }
    }
}


class PowerSteeringBelt extends Belt{
    static class ThisFactory implements Factory<PowerSteeringBelt>{
        @Override
        public PowerSteeringBelt create() {
            return new PowerSteeringBelt();
        }
    }
}

