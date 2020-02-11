import java.util.*;

public class Game_Objects
{
    static PC pc = new PC();
    static ArrayList<Room> room = new ArrayList<Room>();
   
    static List<Object> NPCDataBase = new ArrayList<Object>();
    static List<Object> ItemDataBase = new ArrayList<Object>();


    static Combat combat = new Combat();
    static RNG rng = new RNG();

    static List<Object> allNPCs = new ArrayList<Object>();


    public static void initializeNPCArray() {
        NPCDataBase.add(new NPC());
        NPCDataBase.add(new Troll());   
        NPCDataBase.add(new Dragon());
    }
    public static void initializeItemArray()
    {
        ItemDataBase.add(new Item());
        ItemDataBase.add(new Flaming_Sword());
        ItemDataBase.add(new Diamond_Ring());
    }
/*
    public static void initializeArray()
    {
        allNPCs.add(new NPC());
        allNPCs.add(new Goblin());
        allNPCs.add(new Troll());
        allNPCs.add(new Dragon());
        allNPCs.add(new Murloc());
    }*/
}