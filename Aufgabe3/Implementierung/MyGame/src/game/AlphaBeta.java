package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tobi
 * Date: 15.05.13
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */
public class AlphaBeta {

    private static final boolean TRACE = false;
    private static final boolean TRACEX = false;
    private final int startDepth;
    //    private final List<Mark> ausganssituation;
    Mark dummy = new Mark(new Player("Dummy", true));
    private int nextMove = -1;

    public AlphaBeta(int depth, Circle ausganssituation) {
        this.startDepth = depth;
//        this.ausganssituation = ausganssituation;
        max(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, ausganssituation.copy());
//        if (TRACE) System.out.print(Integer.MIN_VALUE + " < " + Integer.MAX_VALUE);
    }

    private int min(final int depth, int alpha, int beta, final Circle circle) {

        if (TRACE) System.out.printf("MIN: alpha %d beta %d circle %s%n", alpha, beta, circle);

        //Mögliche Spielzüge
        List<Integer> possibleMoves = circle.getUniqueFreePositions();

        int minValue = beta;
        if (depth != 0) {
            if (TRACE) System.out.printf("MIN: depth %d%n", depth);
            if (TRACE) System.out.printf("MIN: PossibleMoves %s%n", possibleMoves);

            //Über alle Spielzüge iterrieren
            for (int currentMove : possibleMoves) {
                Circle currentCircle = circle.copy();

                int positionForCurrrentMove = currentMove;
                if (TRACE) System.out.printf("MIN: currentMove %d%n", positionForCurrrentMove);
                currentCircle.set(dummy, positionForCurrrentMove);
                if (TRACE) System.out.printf(" MIN: currentCircle %s%n", currentCircle);

                //Wenn der aktuelle Spielzug sofort zum Verlieren führt...
                if ((currentCircle.getNext(positionForCurrrentMove) != null) || currentCircle.getPrev(positionForCurrrentMove) != null) {
                    if (TRACE)
                        System.out.println("---------" + currentCircle.getPrev(positionForCurrrentMove) + " || " + currentCircle.getNext(positionForCurrrentMove));
                    if (TRACE) System.out.println(" MIN: currentMove -> loose");
                } else {
                    int newValue = max(depth - 1, alpha, getMinValue(possibleMoves), currentCircle);
                    if (newValue < minValue) {
                        if (TRACE || TRACEX) System.out.println("MIN: minvalue changed");
                        minValue = newValue;
//                        if (minValue <= alpha) {
//
//                            break;
//                        }
                        if (startDepth == depth) {
                            nextMove = currentMove;
                        }
                    }
                }
            }

        } else {
            if (TRACE) System.out.printf("MIN: depth %d%n", depth);

            int heuristik = 0;
            for (int currentMove : possibleMoves) {
                Circle currentCircle = circle.copy();
                int positionForCurrrentMove = currentMove;
                if ((currentCircle.getPrev(positionForCurrrentMove) != null) && (currentCircle.getNext(positionForCurrrentMove) != null)) {
                    heuristik--;
                    if (heuristik < minValue) {

                        if (TRACE || TRACEX) System.out.println("MIN: minvalue changed by heuristik");
                        minValue = heuristik;
//                        if (minValue <= alpha) {
//
//                            break;
//                        }
                        if (startDepth == depth) {
                            nextMove = currentMove;
                        }
                    }
                }
            }


        }
        if (nextMove == -1) {
            nextMove = new ArrayList<>(circle.getFreePositions()).get(0);
        }
        if (TRACE || TRACEX) System.out.printf("MIN: minValueToReturn = %d%n", minValue);
        return minValue;
    }

    private int max(final int depth, int alpha, int beta, final Circle circle) {

        if (TRACE) System.out.printf("MAX: alpha %d beta %d circle %s%n", alpha, beta, circle);
        //Mögliche Spielzüge
        List<Integer> possibleMoves = circle.getUniqueFreePositions();

        int maxValue = alpha;
        if (depth != 0) {
            if (TRACE) System.out.printf("MAX: depth %d%n", depth);
            if (TRACE) System.out.printf("MAX: PossibleMoves %s%n", possibleMoves);

            //Über alle Spielzüge iterrieren
            for (int currentMove : possibleMoves) {
                Circle currentCircle = circle.copy();
                int positionForCurrrentMove = currentMove;
                if (TRACE) System.out.printf("MAX: currentMove %d%n", positionForCurrrentMove);
                currentCircle.set(dummy, positionForCurrrentMove);
                if (TRACE) System.out.printf(" MAX: currentCircle %s%n", currentCircle);
                //Wenn der aktuelle Spielzug sofort zum Verlieren führt...
                if ((currentCircle.getNext(positionForCurrrentMove) != null) || currentCircle.getPrev(positionForCurrrentMove) != null) {
                    if (TRACE)
                        System.out.println("---------" + currentCircle.getPrev(positionForCurrrentMove) + " || " + currentCircle.getNext(positionForCurrrentMove));
                    if (TRACE) System.out.println(" MAX: currentMove -> loose");
                } else {
                    int newValue = min(depth - 1, getMaxValue(possibleMoves), beta, currentCircle);
                    if (newValue > maxValue) {

                        if (TRACE || TRACEX) System.out.println("MAX: maxvalue changed");
                        maxValue = newValue;
//                        if (maxValue >= beta) {
//
//                            break;
//                        }
                        if (startDepth == depth) {
                            nextMove = currentMove;
                        }
                    }
                }
            }


        } else {
            if (TRACE) System.out.printf("MAX: depth %d%n", depth);
            int heuristik = 0;
            for (int currentMove : possibleMoves) {
                Circle currentCircle = circle.copy();
                int positionForCurrrentMove = currentMove;
                if ((currentCircle.getPrev(positionForCurrrentMove) != null) && (currentCircle.getNext(positionForCurrrentMove) != null)) {
                    heuristik++;
                    if (heuristik > maxValue) {

                        if (TRACE || TRACEX) System.out.println("MAX: maxvalue changed by heuristik");
                        maxValue = heuristik;
//                        if (maxValue >= beta) {
//
//                            break;
//                        }
                        if (startDepth == depth) {
                            nextMove = currentMove;
                        }
                    }
                }
            }


        }
        if (nextMove == -1) {
            nextMove = new ArrayList<>(circle.getFreePositions()).get(0);
        }
        if (TRACE || TRACEX) System.out.printf("MAX: maxValueToReturn = %d%n", maxValue);
        return maxValue;
    }

    private int getMaxValue(List<Integer> list) {
        return Collections.max(list);
    }

    private int getMinValue(List<Integer> list) {
        return Collections.min(list);
    }

    public int getMarkPositionForNextMove() {
        return 0;
    }

    public int getNextMove() {
        return nextMove;
    }


}
