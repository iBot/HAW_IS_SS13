package game;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 12.05.13
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
public final class Mark {

    Player player;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("M{");
        sb.append(player);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mark mark = (Mark) o;

        if (!player.equals(mark.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return player.hashCode();
    }

    public Mark(Player player){
        this.player=player;
    }

    public Player getPlayer() {
        return player;
    }

}
