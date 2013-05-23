/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 23.05.13
 * Time: 11:54
 * To change this template use File | Settings | File Templates.
 */
public abstract class Player implements Runnable{
    private KColor color;

    public KColor getColor(){
        return color;
    }
}
