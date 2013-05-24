package game;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 12.05.13
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
public final class Board {

    private Circle circle;
    private Player looser;

    public Board(int size){

        if (size<=0){
            throw new Error(String.format("Size must be greater than 0 but it is %d", size));
        }

        this.circle = new Circle(size);
    }

    public Player getLooser(){
        return this.looser;
    }

    public boolean isGameFinished(){
        return this.looser != null;
    }

    public boolean isMarkedAtPosition(final int position){
        boolean result = false;
        if (isInRange(position)){
            if (this.circle.get(position) != null){
                result = true;
            }
        } else {
            throwPositionOutOfBound(position);
        }
        return result;
    }

    public Mark getMarkAtPosition(final int position){
        Mark result = null;
        if (isInRange(position)){
            result = this.circle.get(position);
        }
        return result;
    }

    public int getSize(){
        return this.circle.size();
    }

    public boolean setMarkAtPosition(final Mark mark, final int position){
        boolean result = false;
        if (!isMarkedAtPosition(position) && !isGameFinished()){
            this.circle.set(mark, position);
            result = true;

            if ((getPrev(position) != null) || (getNext(position) != null)){
                looser = mark.getPlayer();
            }
        }
        return result;
    }

    public boolean isInRange(final int index){
        boolean result = false;
        if ((index >= 0) && (index < circle.size())){
            result = true;
        }
        return result;
    }

    private Mark getPrev(final int position){
        Mark result = null;
        if (isInRange(position)){

            if (position == 0){
                result = circle.get(circle.size()-1);
            } else {
                result = circle.get(position-1);
            }

        } else {
            throwPositionOutOfBound(position);
        }
        return result;
    }

    private Mark getNext(final int position){
        Mark result = null;
        if (isInRange(position)){

            if (position == circle.size()-1){
                result = circle.get(0);
            } else {
                result = circle.get(position+1);
            }

        } else {
            throwPositionOutOfBound(position);
        }
        return result;
    }

    private void throwPositionOutOfBound(final int position){
        throw new Error(String.format("Position out of Bound. Expectation: between 0 and %d; Actual: %d", this.circle.size() - 1, position));
    }

    @Override
    public String toString(){
        final String nlt = "\n\t";
        final String dp = ":\t";
        StringBuilder sb = new StringBuilder("Board:");
        for (int i = 0; i< circle.size(); i++){
            sb.append(nlt).append(i).append(dp).append(circle.get(i));
        }
        return sb.toString();
    }

    public Circle getCircle(){
        return circle.copy();
    }

}
