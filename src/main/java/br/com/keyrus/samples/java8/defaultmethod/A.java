package br.com.keyrus.samples.java8.defaultmethod;

public interface A {

    void weNeedToImplementThis();

    default void weDONTneedToImplementThis() {

        System.out.println("Calling default method ...");

    }

}
