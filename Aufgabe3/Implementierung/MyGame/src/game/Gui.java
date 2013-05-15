package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 15.05.13
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */
public class Gui implements IGui{
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
    private JButton button20;
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private HashMap<JButton,Integer> fields = new HashMap<JButton, Integer>();

    public Gui(){
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
        buttons.add(button20);

        fields.put(button1,0);
        fields.put(button2,0);
        fields.put(button3,0);
        fields.put(button4,0);
        fields.put(button5,0);
        fields.put(button6,0);
        fields.put(button7,0);
        fields.put(button8,0);
        fields.put(button9,0);
        fields.put(button10,0);
        fields.put(button11,0);
        fields.put(button12,0);
        fields.put(button13,0);
        fields.put(button15,0);
        fields.put(button16,0);
        fields.put(button17,0);
        fields.put(button18,0);
        fields.put(button19,0);
        fields.put(button20,0);
    }

    public boolean markField(int fieldnr, int player){
        JButton button = buttons.get(fieldnr-1);
        if (((player == 1) || (player == 2)))
            if (fields.get(button) == 0) {
                if (player == 1) {
                    button.setBackground(Color.BLACK);
                    button.setForeground(Color.WHITE);
                    button.setText("X");
                    fields.put(button, 1);
                } else {
                    button.setBackground(Color.WHITE);
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

    @Override
    public int getField(int fieldnr) {
        return fields.put(buttons.get(fieldnr-1),1);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
