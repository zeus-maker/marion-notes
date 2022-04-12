package oom;

import java.util.ArrayList;
import java.util.List;

public class ListOOM {

    private Byte[] name = new Byte[1024 * 10];

    /**
     * -Xms50m -Xmx50m
     * java -Xms50m -Xmx50m ListOOM.java
     */
    public static void main(String[] args) {
        List<ListOOM> listOOMS = new ArrayList<>();

        while (true) {
            listOOMS.add(new ListOOM());
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
