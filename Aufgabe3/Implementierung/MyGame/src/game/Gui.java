package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 15.05.13
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */
public class Gui implements IGui {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JButton button0;
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private HashMap<JButton, Integer> fields = new HashMap<JButton, Integer>();
    private java.util.List<PlayerMovedListener> listenerList;
    private Board b;
    private Player human, computer;

    public Gui(Board b, Player human, Player computer) {
        this.b = b;
        if (computer.isHuman()) {
            throw new Error("Computer-Player " + computer + " is a human player!");
        }
        if (!human.isHuman()) {
            throw new Error("Human-Player " + human + " is not a human player!");
        }
        this.computer = computer;
        this.human = human;

        listenerList = new ArrayList<>();


        buttons.add(button0);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);
        buttons.add(button8);
        buttons.add(button9);
        buttons.add(button10);
        buttons.add(button11);
        buttons.add(button12);
        buttons.add(button13);
        buttons.add(button14);
        buttons.add(button15);
        buttons.add(button16);
        buttons.add(button17);
        buttons.add(button18);
        buttons.add(button19);


        fields.put(button0, 0);
        fields.put(button1, 0);
        fields.put(button2, 0);
        fields.put(button3, 0);
        fields.put(button4, 0);
        fields.put(button5, 0);
        fields.put(button6, 0);
        fields.put(button7, 0);
        fields.put(button8, 0);
        fields.put(button9, 0);
        fields.put(button10, 0);
        fields.put(button11, 0);
        fields.put(button12, 0);
        fields.put(button13, 0);
        fields.put(button14, 0);
        fields.put(button15, 0);
        fields.put(button16, 0);
        fields.put(button17, 0);
        fields.put(button18, 0);
        fields.put(button19, 0);

        // Buttons zum Funktionieren bringen ;)
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(1, 1);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(2, 1);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(3, 1);
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(4, 1);
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(5, 1);
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(6, 1);
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(7, 1);
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(8, 1);
            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(9, 1);
            }
        });
        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(10, 1);
            }
        });
        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(11, 1);
            }
        });
        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(12, 1);
            }
        });
        button13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(13, 1);
            }
        });
        button14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(14, 1);
            }
        });
        button15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(15, 1);
            }
        });
        button16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(16, 1);
            }
        });
        button17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(17, 1);
            }
        });
        button18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(18, 1);
            }
        });
        button19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(19, 1);
            }
        });
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markField(0, 1);
            }
        });
    }

//    public Gui(){
//
//    }

    public static void main(String[] args) {

        Board board = new Board(20);
        Player human = new Player("Human", true);
        Player computer = new Player("Computer", false);
        Gui gui = new Gui(board, human, computer);
        new AI(board, gui, computer);

        JFrame frame = new JFrame("Gui");
        frame.setContentPane(gui.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public boolean markField(int fieldnr, int player) {
        boolean result;
        try {
            if (b.isMarkedAtPosition(fieldnr)) {
                System.out.println(b.getCircle());
                JOptionPane.showMessageDialog(null, "Spieler "+player+" Feld " + fieldnr + " ist bereits belegt!");
                result = false;
            } else {
                b.setMarkAtPosition(new Mark(human), fieldnr);
                buttons.get(fieldnr).setEnabled(false);
                boolean returnValue = setField(fieldnr, player);
                Runnable computerMove = null;
                if (player == 1) {
                    for (PlayerMovedListener pml : listenerList) {
                        final PlayerMovedListener currentPML = pml;
                        computerMove = new Runnable() {
                            @Override
                            public void run() {
                                currentPML.computerMove();
                            }
                        };
                    }
                }
                if (b.isGameFinished()) {
                    for (JButton button : buttons) {
                        button.setEnabled(false);
                    }
                    JOptionPane.showMessageDialog(null, "Spiel ist vorbei!");
                } else {
                    if (computerMove!=null) computerMove.run();
                }
                result = returnValue;
                // TODO: (Einfaches) Markieren eines Feldes in 'Board'
            }
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        } catch (OffBoardException o) {
            JOptionPane.showMessageDialog(null, o.getMessage());
            return false;
        }
        return result;
    }

    private boolean setField(int fieldnr, int player) throws OffBoardException {
        System.out.printf("Spieler %d markiert Feld %d%n", player, fieldnr);
        JButton button = buttons.get(fieldnr);
        if (fieldnr > 19 || fieldnr < 0) {
            throw new OffBoardException("Das Spielfeld geht von 0 bis 19 (Gewählter Wert: " + fieldnr + ")");
        }
        if (((player == 1) || (player == 2)))
            if (fields.get(button) == 0) {
                if (player == 1) {
                    button.setBackground(Color.BLACK);
                    button.setForeground(Color.WHITE);
                    button.setText("X");
                    fields.put(button, 1);
                } else {
                    button.setBackground(Color.BLUE);
                    button.setForeground(Color.WHITE);
                    button.setText("O");
                    fields.put(button, 2);
                }
                return true;
            } else {
                return false;
            }
        else {
            return false;
        }
    }

    public int getField(int fieldnr) {
        return fields.put(buttons.get(fieldnr), 1);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void subscribeForPlayerMovedEvet(PlayerMovedListener playerMovedListener) {
        if (!listenerList.contains(playerMovedListener)) {
            listenerList.add(playerMovedListener);
        }
    }
}
