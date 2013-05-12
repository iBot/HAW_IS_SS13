package game;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 12.05.13
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
public class Player {

    private String name;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("P{'");
        sb.append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Player(String name){
        this.name= name;
    }
}
