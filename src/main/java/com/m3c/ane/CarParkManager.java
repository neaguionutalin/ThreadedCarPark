package com.m3c.ane;


public class CarParkManager {
    private CarPark carPark;

    public CarParkManager(CarPark carPark) {
        this.carPark = carPark;
    }

    public void requestCarSpaces() throws InterruptedException {
        synchronized (carPark) {
            if (carPark.hasAvailableSpaces()) {
                carPark.addCar();
                System.out.println(Thread.currentThread().getName() + " parked - Spaces available -> " + carPark.getSpaces());

            } else {
                System.out.println("No spaces available - " + Thread.currentThread().getName() + " wait");
                carPark.wait();
                carPark.addCar();
                System.out.println(Thread.currentThread().getName() + " parked - Spaces available -> " + carPark.getSpaces());
            }
        }
    }
    public void leaveCarPark()
    {
        synchronized (carPark) {
            carPark.removeCar();
            carPark.notify();
            System.out.println(Thread.currentThread().getName() + " leaving the car park - Available spaces -> " + carPark.getSpaces());
        }
    }
}
