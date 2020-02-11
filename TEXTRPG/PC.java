import java.util.*;

public class PC {
    String name;
    int hp;
    int accuracy;
    int inRoom = 0;
    List<Item> item =  new ArrayList<Item>();
    List<Item> wornItems =  new ArrayList<Item>();

    public void look()
    {
        System.out.println("hp:" + hp);
        System.out.println("accuracy:" + accuracy);
    }


    public void remove(String[] x)
    {
        for (int i = 0; i < wornItems.size(); i++) {

            if (wornItems.get(i).id.equals(x[1])) {

                System.out.println("You Remove a " + wornItems.get(i).id);
                item.add(wornItems.get(i));
                wornItems.remove(i);
            }
        }
    }

    public void eq(String[] x)
    {
        for (int i = 0; i < wornItems.size(); i++) {

            System.out.println(wornItems.get(i).name + ":" + wornItems.get(i).wearloc);
        }
    }

    public void wear(String[] x) {
        if(wornItems.size() == 0) {

            for (int i = 0; i < item.size(); i++) {
                if(x[1].equalsIgnoreCase(item.get(i).id) && item.get(i).isWearable) {
                    wornItems.add(item.get(i));
                    System.out.println("You wear a " + item.get(i).name);
                    item.remove(i);
                    break;
                }
            }
        }
        else 
        {   boolean isWearing = false;
            for (int i = 0; i < wornItems.size(); i++) {
                for(int z = 0; z < item.size(); z++)
                {
                    
                    if (x[1].equalsIgnoreCase(item.get(z).id) && item.get(z).isWearable)
                    {
                        
                        if(item.get(z).wearloc.equals(wornItems.get(i).wearloc)) {
                            System.out.println("You already have Something worn in that location.");
                            isWearing = true;
                        }
                    }
                }
                if (!isWearing)
                {
                    wornItems.add(item.get(i));
                    System.out.println("You wear a " + item.get(i).name);
                    item.remove(i);

                    break;
                }
            }

        }
    }


    

}