package org.example.AtomicReference;

import java.util.concurrent.atomic.AtomicReference;

public class Example {
    public static void main(String[] args) {
        String oldName = "old name";
        String newName = "new name";
        AtomicReference<String> reference = new AtomicReference<>(oldName);

        reference.set("unexpected name"); //in this case this "unexpected name" does not equal "old name", so we cant change it to new value

        if (reference.compareAndSet("old name", newName)) { // basically in this line-> if the reference value is "old name" we replace it with new value
            System.out.println("New Value is " + reference.get());
        } else {
            System.out.println("Nothing changed");
        }
    }
}
