import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NimGuiV2 extends JDialog {
    private JPanel contentPane;
    private JButton start;
    private JButton exit;
    private JButton takeRow1;
    private JButton takeRow2;
    private JButton takeRow3;
    private JLabel row1;
    private JLabel row2;
    private JLabel row3;
    private JTextArea log;
    private JTextField row1count;
    private JTextField row2count;
    private JTextField row3count;
    private HashMap<Integer,Integer> rows = new HashMap<Integer, Integer>();
    int[][] board = new int[5][3];
    private boolean kiTurn = false;
    private boolean gameOver = false;

    public NimGuiV2() {
        rows.put(1, 3);
        rows.put(2, 4);
        rows.put(3, 5);

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
                if(Integer.parseInt(row1count.getText())>=1){
                    take(1,Integer.parseInt(row2count.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "Es muss mindestens ein Stäbchen genommen werden!");
                }
            }
        };

        ActionListener listenerRow2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Integer.parseInt(row2count.getText())>=1){
                    take(2,Integer.parseInt(row2count.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "Es muss mindestens ein Stäbchen genommen werden!");
                }
            }
        };

        ActionListener listenerRow3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Integer.parseInt(row3count.getText())>=1){
                    take(3,Integer.parseInt(row3count.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "Es muss mindestens ein Stäbchen genommen werden!");
                }
            }
        };

        takeRow1.addActionListener(listenerRow1);
        takeRow2.addActionListener(listenerRow2);
        takeRow3.addActionListener(listenerRow3);
    }

    private void printLog(String s){
        log.append(s + "\n");
    }

    private void initBoard(){
        for(int i = 0; i < 3; i++){
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
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print("["+board[i][j]+"]");
            }
            System.out.println();
        }
        System.out.println("----------------");
        int[] cols = sumUpCols();
        for (int col : cols) {
            System.out.print("[" + col + "]");
        }
        System.out.println();
        System.out.println();
    }

    private boolean testSolution(Integer[] solution){
        int[][] bc = board.clone();
        bc[solution[0]-1] = integerToByte(rows.get(solution[0])-solution[1]);

        int[] colSums = {0,0,0};
        // Da wir wissen, dass das Array nicht länger als 3 ist, können wir eine feste Zahl verwenden
        for(int i=0; i<3; i++){
            for(int j=0; j<bc.length; j++){
                colSums[i] = colSums[i]+bc[j][i];
            }
        }

        /*System.out.println("Test der Variante: ");
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print("["+bc[i][j]+"]");
            }
            System.out.println();
        }
        System.out.println("----------------");
        for(int i = 0; i < colSums.length; i++){
            System.out.print("["+colSums[i]+"]");
        }
        System.out.println();
        System.out.println();*/

        for (int colSum : colSums) {
            if (colSum % 2 != 0)
                return false;
        }

        return true;
    }

    private int maxAvailableSticks(){
        int max = 0;
        for(Map.Entry<Integer,Integer> e: rows.entrySet()){
            max = Math.max(e.getValue(),max);
        }
        return max;
    }

    private Integer[] bestRow(int[] colSums){
        int[] oddCols = {0,0,0};
        boolean found = false;
        ArrayList<Integer[]> possible = new ArrayList<Integer[]>();
        for(int i=0; i<colSums.length; i++){
            if(colSums[i]%2 != 0)
                oddCols[i] = 1;
        }
        int neededSticks = binaryToInt(oddCols);
        for(Map.Entry<Integer,Integer> e: rows.entrySet()){
            if(e.getValue()>=neededSticks){
                Integer[] res = {e.getKey(),neededSticks};
                if(testSolution(res)){
                    possible.add(res);
                    found = true;
                }
            }
        }
        if(!found && neededSticks > maxAvailableSticks()){
            int nextSmallest = 0;
            if(maxAvailableSticks() >= 4)
                nextSmallest = 4;
            if(maxAvailableSticks() < 4)
                nextSmallest = 2;
            // System.out.println("nextsmallest: "+nextSmallest+" maxava "+maxAvailableSticks()+" needed "+neededSticks);
            for(Map.Entry<Integer,Integer> e: rows.entrySet()){
                if(e.getValue()>=nextSmallest){
                    for(int i=e.getValue();i>0;i--){
                        Integer[] res = {e.getKey(),i};
                        if(testSolution(res)){
                            possible.add(res);
                        }
                    }
                }
            }
        }
        // Wenn es mehr als eine Möglichkeit gibt, ein Stäbchen zu wählen, tue dies per Zufall
        if(possible.size() > 1){
            Long choose = Math.round(Math.random() * (possible.size() - 1));
            return possible.get(choose.intValue());
        } else if(possible.size() == 1){
            return possible.get(0);
        } else {
            return null;
        }
    }

    private ArrayList<Integer> availableRows(){
        ArrayList<Integer> avail = new ArrayList<Integer>();
        for(Map.Entry<Integer,Integer> e: rows.entrySet()){
            if(e.getValue() > 0){
                avail.add(e.getKey());
            }
        }
        return avail;
    }

    private Integer binaryToInt(int[] binary){
        Double res = 0.0;
        for(int i=0; i<binary.length;i++){
            res = res + (binary[i] * Math.pow(2, i));
            //System.out.println(res + " "+ binary[i]+""+(Math.pow(2,i)));
        }

        return res.intValue();
    }



    private void kiTakeStick(){
        //System.out.println("vorher:");
        //printBoard();
        int row,count;
        Integer[] choose = bestRow(sumUpCols());
        if(choose != null){
            row = choose[0];
            count = choose[1];
        } else {
            //printBoard();
            ArrayList<Integer> avail = availableRows();
            //System.out.println(avail);
            Long rnd = Math.round(Math.random() * (avail.size()-1));
            //System.out.println(rnd);
            row = avail.get(rnd.intValue());
            count = rows.get(row);
            rnd = Math.round(Math.random() * count+1);
            count = rnd.intValue();
        }
        //System.out.println("Reihe " + row + " Menge " + count);
        take(row, count);
        //System.out.println("nachher:");
        //printBoard();
    }

    private void take(int row, int count){
        if(rows.get(row) >= count){
            rows.put(row,rows.get(row)-count);
            updateBoard();
            updateGui();
            if(kiTurn)
                printLog("Computer entfernt "+count+" Stäbchen aus Zeile "+row);
            else
                printLog("Spieler entfernt "+count+" Stäbchen aus Zeile "+row);
            if(allTaken()){
                gameOver=true;
                if(kiTurn){
                    JOptionPane.showMessageDialog(null,"Der Computer hat gewonnen!");
                    printLog("Der Computer gewinnt!");
                }else{
                    JOptionPane.showMessageDialog(null,"Der Spieler hat gewonnen!");
                    printLog("Der Spieler gewinnt!");
                }
            } else {
                nextTurn();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Die Reihe " + row + " enthält nicht genug Stäbchen!");
        }
    }

    private boolean allTaken() {
        for(Map.Entry<Integer,Integer> e: rows.entrySet()){
            if(e.getValue() > 0)
                return false;
        }
        return true;
    }

    private void nextTurn() {
        if(kiTurn){
            kiTurn = false;
            setButtonState(true);
            row1count.setText("1");
            row2count.setText("1");
            row3count.setText("1");
        }else{
            kiTurn = true;
            setButtonState(false);
            kiTakeStick();
            row1count.setText("1");
            row2count.setText("1");
            row3count.setText("1");
        }
    }

    private void setButtonState(boolean active){
        takeRow1.setEnabled(active);
        takeRow2.setEnabled(active);
        takeRow3.setEnabled(active);
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
    }

    private void start() {
        if(gameOver){
            rows.put(1, 3);
            rows.put(2, 4);
            rows.put(3, 5);
            updateGui();
            updateBoard();
            gameOver = false;
            //printBoard();
        }
        kiTurn = true;
        kiTakeStick();
    }

    private void onExit() {
        dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        NimGuiV2 dialog = new NimGuiV2();
        dialog.pack();
        dialog.setVisible(true);
    }
}
