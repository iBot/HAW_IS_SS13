package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 15.05.13
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */
public class AlphaBeta {

//    private final int depth;
//    private final List<Mark> ausganssituation;

    public AlphaBeta(int depth, List<Mark> ausganssituation){
//        this.depth=depth;
//        this.ausganssituation = ausganssituation;
        max(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, new ArrayList<>(ausganssituation));
//        System.out.print(Integer.MIN_VALUE + " < " + Integer.MAX_VALUE);
    }

    private void min(final int depth, int alpha, int beta, List<Mark> positions){


    }

    private void max(final int depth, int alpha, int beta, List<Mark> positions){
        if (depth != 0){

        }else{

        }
    }

    public int getMarkPositionForNextMove(){
        return 0;
    }




}
