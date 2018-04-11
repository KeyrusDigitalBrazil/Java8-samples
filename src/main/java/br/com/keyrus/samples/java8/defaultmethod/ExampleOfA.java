package br.com.keyrus.samples.java8.defaultmethod;

public class ExampleOfA implements A {

    public static void main(String[] args) {

        new ExampleOfA().weDONTneedToImplementThis();

        new ExampleOfA().weNeedToImplementThis();

    }

    @Override
    public void weNeedToImplementThis() {

        System.out.println("This is a plain old method...");

    }
}