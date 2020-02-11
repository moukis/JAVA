public class NPC
{
    String name;
    String id = "NPC";
    String desc;
    int hp;
    int accuracy;

    /*public NPC(String name, String id ,String desc, int hp, int accuracy)
    {
        this.name = name;
        this.id = id;
        this.desc = desc;
        this.hp = hp;
        this.accuracy = accuracy;
    }*/
    public void look()
    {
        System.out.println(name);
        System.out.println("accuracy:" + accuracy);
        System.out.println("hp:" +hp);
    }
}