import javax.swing.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NimGui extends JDialog {
    private JPanel contentPane;
    private JButton start;
    private JButton exit;
    private JButton takeRow1;
    private JButton takeRow2;
    private JButton takeRow3;
    private JButton takeRow4;
    private JButton takeRow5;
    private JLabel row1;
    private JLabel row2;
    private JLabel row3;
    private JLabel row4;
    private JLabel row5;
    private JList log;
    private HashMap<Integer,Integer> rows = new HashMap<Integer, Integer>();
    int[][] board = new int[5][3];
    private boolean kiTurn = false;

    public NimGui() {
        rows.put(1, 1);
        rows.put(2, 2);
        rows.put(3, 3);
        rows.put(4, 4);
        rows.put(5, 5);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(start);
        initBoard();

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        });

// call onExit() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onExit();
            }
        });

// call onExit() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        ActionListener listenerRow1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                take(1);
            }
        };

        ActionListener listenerRow2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                take(2);
            }
        };

        ActionListener listenerRow3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                take(3);
            }
        };

        ActionListener listenerRow4 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                take(4);
            }
        };

        ActionListener listenerRow5 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                take(5);
            }
        };

        takeRow1.addActionListener(listenerRow1);
        takeRow2.addActionListener(listenerRow2);
        takeRow3.addActionListener(listenerRow3);
        takeRow4.addActionListener(listenerRow4);
        takeRow5.addActionListener(listenerRow5);
    }

    private void initBoard(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = 0;
            }
        }
        updateBoard();
    }

    public void updateBoard(){
        for(Map.Entry<Integer,Integer> e: rows.entrySet()){
            int[] val = integerToByte(e.getValue());
            for(int i=0; i<val.length;i++){
                board[e.getKey()-1][i] = val[i];
            }
        }
    }

    private int[] integerToByte(Integer num){
        int[] bytes = {0,0,0};
        //System.out.println(Integer.toBinaryString(num));
        String valAsString = new StringBuffer(Integer.toBinaryString(num)).reverse().toString();
        char[] valAsChars = valAsString.toCharArray();
        for(int i = 0; i < valAsChars.length; i++){
            bytes[i] = Integer.parseInt(String.valueOf(valAsChars[i]));
        }
        return bytes;
    }

    private int[] sumUpCols(){
        int[] colSums = {0,0,0};
        // Da wir wissen, dass das Array nicht länger als 3 ist, können wir eine feste Zahl verwenden
        for(int i=0; i<3; i++){
            for(int j=0; j<board.length; j++){
                colSums[i] = colSums[i]+board[j][i];
            }
        }
        return colSums;
    }

    private void printBoard(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 3; j++){
                System.out.print("["+board[i][j]+"]");
            }
            System.out.println();
        }
        System.out.println("----------------");
        int[] cols = sumUpCols();
        for(int i = 0; i < cols.length; i++){
            System.out.print("["+cols[i]+"]");
        }
        System.out.println();
        System.out.println();
    }

    private int bestRow(int[] colSums){
        ArrayList<Integer> oddCols = new ArrayList<Integer>();
        ArrayList<Integer> possible = new ArrayList<Integer>();
        for(int i=0; i<colSums.length; i++){
            if(colSums[i]%2 != 0)
                oddCols.add(i+1);
        }
        for(Integer oc: oddCols){
            for(int i=0;i<board.length;i++){
                if(board[i][oc-1] == 1){
                    possible.add(i+1);
                }
            }
        }
        // Wenn es mehr als eine Möglichkeit gibt, ein Stäbchen zu wählen, tue dies per Zufall
        if(possible.size() > 1){
            Long choose = Math.round(Math.random() * (possible.size() - 1));
            return possible.get(choose.intValue());
        } else {
            return possible.get(0);
        }
    }

    private boolean winningPosition(){
        int[] colSums = sumUpCols();
        int oddCols = 0;
        for (int colSum : colSums) {
            if (colSum % 2 != 0) {
                oddCols++;
            }
        }
        return oddCols % 2 == 1;
    }

    private ArrayList<Integer> availableRows(){
        ArrayList<Integer> avail = new ArrayList<Integer>();
        for(Map.Entry<Integer,Integer> e: rows.entrySet()){
            if(e.getValue() > 0){
                avail.add(e.getKey());
            }
        }
        System.out.println(avail);
        return avail;
    }

    private void kiTakeStick(){
        printBoard();
        int choose = bestRow(sumUpCols());
        if(!winningPosition()){
            System.out.println("In loosing positionl... :(");
            ArrayList<Integer> avail = availableRows();
            Long rnd = Math.round(Math.random() * (avail.size() - 1));
            choose = rnd.intValue();
        }
        System.out.println(choose);
        take(choose);
    }

    private void take(int row){
        if(rows.get(row) > 0){
            rows.put(row,rows.get(row)-1);
            updateBoard();
            updateGui();
            nextTurn();
        } else {
            JOptionPane.showMessageDialog(null,"Die Reihe "+row+" enthält keine Stäbchen mehr!");
        }
    }

    private void nextTurn() {
        if(kiTurn){
            kiTurn = false;
            setButtonState(true);
        }else{
            kiTurn = true;
            setButtonState(false);
            kiTakeStick();
        }
    }

    private void setButtonState(boolean active){
        takeRow1.setEnabled(active);
        takeRow2.setEnabled(active);
        takeRow3.setEnabled(active);
        takeRow4.setEnabled(active);
        takeRow5.setEnabled(active);
    }

    private String genStickText(int num){
        String s = "";
        for(int i=num;i>0;i--){
            s = s+"I";
        }
        return s;
    }

    private void updateGui() {
        row1.setText(genStickText(rows.get(1)));
        row2.setText(genStickText(rows.get(2)));
        row3.setText(genStickText(rows.get(3)));
        row4.setText(genStickText(rows.get(4)));
        row5.setText(genStickText(rows.get(5)));
    }

    private void start() {
        kiTurn = true;
        kiTakeStick();
    }

    private void onExit() {
        dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        NimGui dialog = new NimGui();
        dialog.pack();
        dialog.setVisible(true);
    }
}
