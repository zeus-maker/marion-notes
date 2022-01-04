package com.marion.gof.factory.sample;

public class DrinksFactory {

    public DrinksService productDrinks(Class<?> className) {

        try {
            Object instance = Class.forName(className.getName()).newInstance();
            return (DrinksService) instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
