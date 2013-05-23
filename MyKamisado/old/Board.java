import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 22.05.13
 * Time: 13:57
 * To change this template use File | Settings | File Templates.
 */
public class Board {
    private GraphicButton button1;
    private JPanel panel1;
    private GraphicButton button2;
    private GraphicButton button3;
    private GraphicButton button4;
    private GraphicButton button5;
    private GraphicButton button6;
    private GraphicButton button7;
    private GraphicButton button8;
    private GraphicButton button9;
    private GraphicButton button10;
    private GraphicButton button11;
    private GraphicButton button12;
    private GraphicButton button13;
    private GraphicButton button14;
    private GraphicButton button15;
    private GraphicButton button16;
    private GraphicButton button17;
    private GraphicButton button18;
    private GraphicButton button19;
    private GraphicButton button20;
    private GraphicButton button21;
    private GraphicButton button22;
    private GraphicButton button23;
    private GraphicButton button24;
    private GraphicButton button25;
    private GraphicButton button26;
    private GraphicButton button27;
    private GraphicButton button28;
    private GraphicButton button29;
    private GraphicButton button30;
    private GraphicButton button31;
    private GraphicButton button32;
    private GraphicButton button33;
    private GraphicButton button34;
    private GraphicButton button35;
    private GraphicButton button36;
    private GraphicButton button37;
    private GraphicButton button38;
    private GraphicButton button39;
    private GraphicButton button40;
    private GraphicButton button41;
    private GraphicButton button42;
    private GraphicButton button43;
    private GraphicButton button44;
    private GraphicButton button45;
    private GraphicButton button46;
    private GraphicButton button47;
    private GraphicButton button48;
    private GraphicButton button49;
    private GraphicButton button50;
    private GraphicButton button51;
    private GraphicButton button52;
    private GraphicButton button53;
    private GraphicButton button54;
    private GraphicButton button55;
    private GraphicButton button56;
    private GraphicButton button57;
    private GraphicButton button58;
    private GraphicButton button59;
    private GraphicButton button60;
    private GraphicButton button61;
    private GraphicButton button62;
    private GraphicButton button63;
    private GraphicButton button64;

    private GraphicButton[][] field = new GraphicButton[8][8];

    public Board() {
        field[0][0] = button1;
        field[0][1] = button2;
        field[0][2] = button3;
        field[0][3] = button4;
        field[0][4] = button5;
        field[0][5] = button6;
        field[0][6] = button7;
        field[0][7] = button8;
        field[1][0] = button9;
        field[1][1] = button10;
        field[1][2] = button11;
        field[1][3] = button12;
        field[1][4] = button13;
        field[1][5] = button14;
        field[1][6] = button15;
        field[1][7] = button16;
        field[2][0] = button17;
        field[2][1] = button18;
        field[2][2] = button19;
        field[2][3] = button20;
        field[2][4] = button21;
        field[2][5] = button22;
        field[2][6] = button23;
        field[2][7] = button24;
        field[3][0] = button25;
        field[3][1] = button26;
        field[3][2] = button27;
        field[3][3] = button28;
        field[3][4] = button29;
        field[3][5] = button30;
        field[3][6] = button31;
        field[3][7] = button32;
        field[4][0] = button33;
        field[4][1] = button34;
        field[4][2] = button35;
        field[4][3] = button36;
        field[4][4] = button37;
        field[4][5] = button38;
        field[4][6] = button39;
        field[4][7] = button40;
        field[5][0] = button41;
        field[5][1] = button42;
        field[5][2] = button43;
        field[5][3] = button44;
        field[5][4] = button45;
        field[5][5] = button46;
        field[5][6] = button47;
        field[5][7] = button48;
        field[6][0] = button49;
        field[6][1] = button50;
        field[6][2] = button51;
        field[6][3] = button52;
        field[6][4] = button53;
        field[6][5] = button54;
        field[6][6] = button55;
        field[6][7] = button56;
        field[7][0] = button57;
        field[7][1] = button58;
        field[7][2] = button59;
        field[7][3] = button60;
        field[7][4] = button61;
        field[7][5] = button62;
        field[7][6] = button63;
        field[7][7] = button64;

        for (int i=0; i<field.length; i++){
            for(int j=0; j<field[i].length; j++){
                GraphicButton b = field[i][j];
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    }
                });
            }
        }

        button1.setBackground();
    }


}

