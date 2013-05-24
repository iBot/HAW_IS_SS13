/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 23.05.13
 * Time: 11:41
 * To change this template use File | Settings | File Templates.
 */
public class Computer extends Player{

    public Computer(KColor color) {
        this.color = color;
    }

    @Override
    public void run() {
    }

    @Override
    public Field nextMove() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public synchronized void move(Field f) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
