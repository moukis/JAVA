import java.util.*;
public class Game_Logic
{
    public Game_Logic()
    {
        Game_Objects.room.add(new Room(1));
        Game_Objects.room.get(0).name = "Floating in Space";
        Game_Objects.room.get(0).desc.add("Decription line 1");
        Game_Objects.room.get(0).desc.add("Decription line 2");
        Game_Objects.room.get(0).desc.add("Decription line 3");
        Game_Objects.room.get(0).desc.add("Decription line 4");
        Game_Objects.room.get(0).exits.add("South 2");
        Game_Objects.room.get(0).exits.add("North 3");

    }
    public void waitforCommand() 
    {
        if(Game_Objects.pc.inRoom == 0){
            createCharacter();
        }
        System.out.println("What Do?");
        Scanner sc = new Scanner(System.in);
        String com = sc.nextLine();
        String[] words = com.split(" ");
        processCommand(words);
    }
    public void processCommand(String[] x) {
        if (x[0].equals("look")) {
            look(x);
        }
        if (x[0].equals("summon")) {
            try {
                summon(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (x[0].equals("create")) {
            try {
                create_item(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (x[0].equals("get")) {
            try {
                get(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (x[0].equals("wear")) {
            try {
                Game_Objects.pc.wear(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (x[0].equals("eq")) {
            try {
                Game_Objects.pc.eq(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (x[0].equals("remove")) {
            try {
                Game_Objects.pc.remove(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void create_item(String[] x) throws Exception {
        if (x.length == 1) {
            System.out.println("Create What Exactly ?");
        }
        if (x.length ==  2) {
            for (int i = 0; i < Game_Objects.ItemDataBase.size(); i++) {
                Item localItem = (Item) Game_Objects.ItemDataBase.get(i);
                if (localItem.id.equalsIgnoreCase(x[1])) {

                    for (int y = 0; y < Game_Objects.room.size(); y++) {
                        if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) {
                            
                            try {
                                Game_Objects.room.get(y).item.add((Item) 
                                Class.forName(localItem.id)
                                .getDeclaredConstructor().newInstance());
                                // .newInstance());
                            } catch(InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }

                            System.out.println("You created a " 
                            + Game_Objects.room.get(y).item.get(Game_Objects.room.get(y).item.size() - 1).name);
                        }
                    }
                }
            }
        }
    }


    public void get(String[] x) {
        if (x.length == 1) {
            System.out.println("Get What Extactly ?");
        }
        if (x.length == 2) {
            for (int i = 0; i < Game_Objects.ItemDataBase.size(); i++) {

                for (int y = 0; y < Game_Objects.room.size(); y++) {
                    if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) {
                        for (int z = 0; z < Game_Objects.room.get(y).item.size(); z++) {
                            if (x[1].equalsIgnoreCase(Game_Objects.room.get(y).item.get(z).id)) {
                                Item localItem = Game_Objects.room.get(y).item.get(z);

                                Game_Objects.pc.item.add(localItem);
                                System.out.println("You picked a " +
                                localItem.name);
                                Game_Objects.room.get(y).item.remove(z);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void look(String[] x)
    {
        if(x.length == 1) {
            for (int i = 0; i < Game_Objects.room.size(); i++) {
                if(Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) {
                    System.out.println(Game_Objects.room.get(i).name);
                    for (int y = 0; y < Game_Objects.room.get(i).desc.size(); y++) {
                        System.out.println(Game_Objects.room.get(i).desc.get(y));
                    }
                    System.out.println("Exits:");
                    for(int y = 0; y < Game_Objects.room.get(i).exits.size(); y++) {
                        String exitNameFull = Game_Objects.room.get(i).exits.get(y);
                        String[] exitName = exitNameFull.split(" ");
                        System.out.println(exitName[0]);
                    }
                    for (int y = 0; y < Game_Objects.room.get(i).npc.size(); y++) {

                        System.out.println(Game_Objects.room.get(i).npc.get(y).desc);
                    }
                    for (int y = 0; y < Game_Objects.room.get(i).item.size(); y++) {
                        
                        System.out.println(Game_Objects.room.get(i).item.get(y).desc);
                    }
                }
            }
        }
        if (x.length == 2) {
            if (x[1].equals("self")) {
                Game_Objects.pc.look();
                System.out.println("You are carring:");
                for (int i = 0; i < Game_Objects.pc.item.size(); i++) {
                    System.out.println(Game_Objects.pc.item.get(i).name);
                }
            }
            for (int y = 0; y < Game_Objects.room.size(); y++) {
                if( Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) {
                    for (int i = 0; i < Game_Objects.room.get(y).npc.size(); i++) {
                        if (x[1].equalsIgnoreCase(Game_Objects.room.get(y).npc.get(i).id)) {
                            Game_Objects.room.get(y).npc.get(i).look();
                        }
                    }
                }
            }
        }
    }

    public void summon(String[] x) throws Exception
    {
        if (x.length == 1) {
            System.out.println("Summon What Exactly ?");
        }
        if (x.length == 2) {
            for (int i = 0; i < Game_Objects.NPCDataBase.size(); i++) {
                NPC localNPC = (NPC) Game_Objects.NPCDataBase.get(i);
                if (localNPC.id.equalsIgnoreCase(x[1])) {
                    for (int y = 0; y < Game_Objects.room.size(); y++) {
                        if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) {
                            try{
                                Game_Objects.room.get(y).npc.add((NPC) 
                                Class.forName(localNPC.id) 
                                .getDeclaredConstructor().newInstance());
                                /*Class.forName(localNPC.id).newInstance());*/
                                System.out.println("You summon a " + 
                                Game_Objects.room.get(y).npc
                                .get(Game_Objects.room.get(y).npc.size() - 1).name);
                            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public void createCharacter() {
        System.out.println("Welcome to the Game. What is your name ?");
        Scanner sc = new Scanner(System.in);
        Game_Objects.pc.name = sc.next();
        System.out.println("The has given to you 100hp and 70 accuracy to start");
        Game_Objects.pc.hp = 100;
        Game_Objects.pc.accuracy = 70;
        Game_Objects.pc.inRoom = 1;
    }
}