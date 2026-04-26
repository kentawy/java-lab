package org.example;

public class FoldablePhone extends SmartPhone {
    private int screenCount;

    public FoldablePhone(String brand, String model, double price, int storageCapacity, OperatingSystem os, double cameraMegapixels, int screenCount) throws InvalidFieldValueException {
        super(brand, model, price, storageCapacity, os, cameraMegapixels);
        this.screenCount = screenCount;
    }

    public int getScreenCount() {
        return screenCount;
    }

    @Override
    public String toFileString() {
        return "FoldablePhone;" + getBrand() + ";" + getModel() + ";" + getPrice() + ";" + getStorageCapacity() + ";" + getOs().name() + ";" + getCameraMegapixels() + ";" + screenCount;
    }

    @Override
    public String toString() {
        return String.format("FoldablePhone{brand='%s', model='%s', price=%.2f, storage=%dGB, OS=%s, camera=%.1fMP, screens=%d}",
                getBrand(), getModel(), getPrice(), getStorageCapacity(), getOs(), getCameraMegapixels(), screenCount);
    }
}
