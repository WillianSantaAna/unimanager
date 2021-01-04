package pt.iade.unimanager.models.repositories;

import java.util.ArrayList;

import pt.iade.unimanager.models.Laboratory;
import pt.iade.unimanager.models.Room;

public class RoomRepository {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Laboratory> laboratories = new ArrayList<>();

    public static ArrayList<Room> getRooms() {
        return rooms;
    }

    public static Laboratory getLaboratory(String designation) {
        for (Laboratory laboratory : laboratories)
            if (laboratory.getDesignation().equals(designation))
                return laboratory;
        return null;
    }

    public static void populate() {

    }
}
