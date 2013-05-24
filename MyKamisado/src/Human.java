/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 23.05.13
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
public class Human extends Player {
    private Field pos = null;

    public Human(KColor color) {
        this.color = color;
    }

    public synchronized Field nextMove(){
        try {
            wait();

        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return pos;
    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public synchronized void move(Field f) {
        this.pos = f;
        notify();
    }
}
