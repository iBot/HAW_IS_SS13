package game;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 15.05.13
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */
public class AlphaBeta {
    
    private static boolean TRACE = false;

    private final int startDepth;
//    private final List<Mark> ausganssituation;
    Mark dummy = new Mark(new Player("Dummy",true));
    private int nextMove = -1;

    public AlphaBeta(int depth, Circle ausganssituation){
        this.startDepth=depth;
//        this.ausganssituation = ausganssituation;
        max(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, ausganssituation.copy());
//        if (TRACE) System.out.print(Integer.MIN_VALUE + " < " + Integer.MAX_VALUE);
    }

    private int  min(final int depth, int alpha, int beta, final Circle circle){

        if (TRACE) System.out.println("MIN: alpha "+alpha+" beta "+beta+ " circle "+circle);

        //Mögliche Spielzüge
        Map<Integer,Integer > possibleMoves = circle.getFreePositions();

        int minValue = beta;
        if (depth != 0){
            if (TRACE) System.out.println("MIN: depth "+depth);
            if (TRACE) System.out.println("MIN: PossibleMoves "+possibleMoves.entrySet());

            //Über alle Spielzüge iterrieren
            for (Map.Entry<Integer,Integer> currentMove : possibleMoves.entrySet()){
                Circle currentCircle = circle.copy();

                int positionForCurrrentMove = currentMove.getKey();
                if (TRACE) System.out.println("MIN: currentMove "+positionForCurrrentMove);
                currentCircle.set(dummy, positionForCurrrentMove);
                if (TRACE) System.out.println(" MIN: currentCircle " + currentCircle);

                //Wenn der aktuelle Spielzug sofort zum Verlieren führt...
                if ((currentCircle.getNext(positionForCurrrentMove)!=null)||currentCircle.getPrev(positionForCurrrentMove)!=null){
                    if (TRACE) System.out.println("---------" + currentCircle.getPrev(positionForCurrrentMove)+ " || " +currentCircle.getNext(positionForCurrrentMove));
                    if (TRACE) System.out.println(" MIN: currentMove -> loose");
                    currentMove.setValue(Integer.MAX_VALUE);
                } else {
                    int newValue = max(depth - 1, alpha, getMinValue(possibleMoves), currentCircle);
                    currentMove.setValue(newValue);
                    if (newValue < minValue){
                        minValue = newValue;
                        if (minValue <= beta){
                            if (startDepth == depth){
                                nextMove = currentMove.getKey();
                            }
                            break;
                        }
                    }
                }
            }

        }else{
            if (TRACE) System.out.println("MIN: depth "+depth);

            int heuristik=0;
            for (Map.Entry<Integer,Integer> currentMove : possibleMoves.entrySet()){
                Circle currentCircle = circle.copy();
                int positionForCurrrentMove = currentMove.getKey();
                if ((currentCircle.getPrev(positionForCurrrentMove)!=null)&&(currentCircle.getNext(positionForCurrrentMove)!=null)){
                    heuristik--;
                    if (heuristik < minValue){
                        minValue = heuristik;
                        if (minValue <= beta){
                            if (startDepth == depth){
                                nextMove = currentMove.getKey();
                            }
                            break;
                        }
                    }
                }
            }
            if (nextMove == -1 ){
                nextMove = new ArrayList<>(circle.getFreePositions().keySet()).get(0);
            }

        }

        if (TRACE) System.out.println("MAX: minValueToReturn = "+minValue);
        return minValue;
    }

    private int max(final int depth, int alpha, int beta, final Circle circle){

        if (TRACE) System.out.println("MAX: alpha "+alpha+" beta "+beta+ " circle "+circle);
        //Mögliche Spielzüge
        Map<Integer,Integer > possibleMoves = circle.getFreePositions();

        int maxValue = alpha;
        if (depth != 0){
            if (TRACE) System.out.println("MAX: depth "+depth);
            if (TRACE) System.out.println("MAX: PossibleMoves "+possibleMoves.entrySet());

            //Über alle Spielzüge iterrieren
            for (Map.Entry<Integer,Integer> currentMove : possibleMoves.entrySet()){
                Circle currentCircle = circle.copy();
                int positionForCurrrentMove = currentMove.getKey();
                if (TRACE) System.out.println("MAX: currentMove "+positionForCurrrentMove);
                currentCircle.set(dummy,positionForCurrrentMove);
                if (TRACE) System.out.println(" MAX: currentCircle "+currentCircle);
                //Wenn der aktuelle Spielzug sofort zum Verlieren führt...
                if ((currentCircle.getNext(positionForCurrrentMove)!=null)||currentCircle.getPrev(positionForCurrrentMove)!=null){
                    if (TRACE) System.out.println("---------" + currentCircle.getPrev(positionForCurrrentMove)+ " || " +currentCircle.getNext(positionForCurrrentMove));
                    if (TRACE) System.out.println(" MAX: currentMove -> loose");
                    currentMove.setValue(Integer.MIN_VALUE);
                } else {
                    int newValue = min(depth - 1, getMaxValue(possibleMoves), beta, currentCircle);
                    currentMove.setValue(newValue);
                    if (newValue > maxValue){
                        maxValue = newValue;
                        if (maxValue >= beta){
                            if (startDepth == depth){
                                nextMove = currentMove.getKey();
                            }
                            break;
                        }
                    }
                }
            }
            if (nextMove == -1 ){
                nextMove = new ArrayList<>(circle.getFreePositions().keySet()).get(0);
            }

        }else{
            if (TRACE) System.out.println("MAX: depth "+depth);
            int heuristik=0;
            for (Map.Entry<Integer,Integer> currentMove : possibleMoves.entrySet()){
                Circle currentCircle = circle.copy();
                int positionForCurrrentMove = currentMove.getKey();
                if ((currentCircle.getPrev(positionForCurrrentMove)!=null)&&(currentCircle.getNext(positionForCurrrentMove)!=null)){
                    heuristik++;
                    if (heuristik > maxValue){
                        maxValue = heuristik;
                        if (maxValue >= beta){
                            if (startDepth == depth){
                                nextMove = currentMove.getKey();
                            }
                            break;
                        }
                    }
                }
            }


        }
        if (TRACE) System.out.println("MAX: maxValueToReturn = "+maxValue);
        return maxValue;
    }

    private int getMaxValue(Map<Integer,Integer> map){
        return Collections.max(map.values());
    }


    private int getMinValue(Map<Integer,Integer> map){
        return Collections.min(map.values());
    }

    public int getMarkPositionForNextMove(){
        return 0;
    }

    public int getNextMove(){
        return nextMove;
    }



}
