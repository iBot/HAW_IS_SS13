package game;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 12.05.13
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
public class Player {
    public boolean isHuman() {
        return isHuman;
    }

    private String name;
    private boolean isHuman;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("name='").append(name).append('\'');
        sb.append(", isHuman=").append(isHuman);
        sb.append('}');
        return sb.toString();
    }

//    public Player(String name){
//        this.name= name;
//    }
    public Player(String name, boolean isHuman){
        this.name= name;
        this.isHuman = isHuman;
    }
}
