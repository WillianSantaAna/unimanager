package pt.iade.unimanager.models.repositories;

import java.util.ArrayList;

import pt.iade.unimanager.models.Computer;
import pt.iade.unimanager.models.Laboratory;
import pt.iade.unimanager.models.Material;
import pt.iade.unimanager.models.Room;

public class RoomRepository {
    private static ArrayList<Room> rooms = new ArrayList<>();

    public static ArrayList<Room> getRooms() {
        return rooms;
    }

    public static Laboratory getLaboratory(String designation) {
        for (Room room : rooms)
            if (room.getDesignation().equals(designation))
                if (room instanceof Laboratory)
                    return (Laboratory)room;
        return null;
    }

    public static void populate() {
        ArrayList<Material> materials1 = new ArrayList<>();
        ArrayList<Material> materials2 = new ArrayList<>();
        ArrayList<String> specificationsPC1 = new ArrayList<>();
        
        Material whiteboard = new Material("Whiteboard");
        Material chair = new Material("Chair");
        Material table = new Material("Table");

        specificationsPC1.add("Intel Xeon");
        specificationsPC1.add("16GB Ram");
        specificationsPC1.add("1TB HDD");
        specificationsPC1.add("Ecra 21");

        Computer pc1 = new Computer("Lenovo ThinkStation", specificationsPC1);

        materials1.add(whiteboard);
        materials1.add(chair);
        materials1.add(table);

        materials2.add(whiteboard);
        materials2.add(chair);
        materials2.add(table);
        materials2.add(pc1);

        rooms.add(new Laboratory("13", 20, materials1));
        rooms.add(new Laboratory("21", 20, materials2));
        rooms.add(new Room("17", 20));
        rooms.add(new Room("28", 30));
        rooms.add(new Room("33", 40));
    }
}
