import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 23.05.13
 * Time: 08:52
 * To change this template use File | Settings | File Templates.
 */
public class KColor extends Color {

    private static final long serialVersionUID = 1L;

    /**
     * Farbcode von 1-8.
     */
    private int code;

    /**
     * Name von Orange bis Braun
     */
    private String name;

    public KColor(Color c, String s, int i) {
        super(c.getRGB());
        name = s;
        code = i;
    }

    public KColor(int r, int g, int b, String s, int i) {
        super(r, g, b);
        name = s;
        code = i;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return name.substring(0, 3);
    }
}
