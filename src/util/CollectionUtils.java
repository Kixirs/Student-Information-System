package util;

import model.BaseEntity;
import java.util.Collection;

public class CollectionUtils {
    public static void printAll(Collection<? extends BaseEntity> items) {
        for (BaseEntity item : items) {
            System.out.println("ID: " + item.getId() + ", Name: " + item.getName());
        }
    }
}