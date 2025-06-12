package q10_CaixeiroViajante;

public class Cidade {
    private int id;
    private int x;
    private int y;

    public Cidade(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int obterId() {
        return id;
    }

    public int obterX() {
        return x;
    }

    public int obterY() {
        return y;
    }

    @Override
    public String toString() {
        return "C" + id + "(" + x + "," + y + ")";
    }
}
