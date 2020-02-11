import java.util.*;

public class Room
{
    int number;
    String name;

    List<String> desc = new ArrayList<String>();
    List<String> exits = new ArrayList<String>();

    List<NPC> npc =  new ArrayList<NPC>();
    List<Item> item =  new ArrayList<Item>();
    public Room(int x)
    {
        number = x;
    }
}