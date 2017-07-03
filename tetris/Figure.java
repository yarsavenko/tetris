package tetris;


public class Figure {
    private int fig[][];
    public String name;
    public Figure(int a[], int b[], int c[], int d[], String name){
        fig = new int[4][4];
        fig[0] = a;
        fig[1] = b;
        fig[2] = c;
        fig[3] = d;
        this.name = name;
    }

    public int[][] getFig()
    {
        return fig;
    }

    public int[][] clearRot(int rot[][])
    {

        int a = 0;
        while (a < 4){
            if (rot[0][a] == 1 || rot[1][a] == 1 || rot[2][a] == 1 || rot[3][a] == 1)
                break;
            a++;
        }
        int c = 0;
        if (a == 0){
            return rot;
        }
        while (a < 4){
            rot[0][c] = rot[0][a]; rot[0][a] = 0;
            rot[1][c] = rot[1][a]; rot[1][a] = 0;
            rot[2][c] = rot[2][a]; rot[2][a] = 0;
            rot[3][c] = rot[3][a]; rot[3][a] = 0;
            a++;
            c++;
        }
        return rot;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Figure clone = new Figure(this.fig[0],this.fig[1], this.fig[2], this.fig[3], this.name );
        return clone;
    }

    public  void rotateOne() {
        int rot[][] = new int[4][4];
        rot[0][0] = this.fig[3][0];
        rot[0][1] = this.fig[2][0];
        rot[0][2] = this.fig[1][0];
        rot[0][3] = this.fig[0][0];
        rot[1][0] = this.fig[3][1];
        rot[1][1] = this.fig[2][1];
        rot[1][2] = this.fig[1][1];
        rot[1][3] = this.fig[0][1];
        rot[2][0]= this.fig[3][2];
        rot[2][1]= this.fig[2][2];
        rot[2][2] = this.fig[1][2];
        rot[2][3] = this.fig[0][2];
        rot[3][0] = this.fig[3][3];
        rot[3][1] = this.fig[2][3];
        rot[3][2] = this.fig[1][3];
        rot[3][3] = this.fig[0][3];
        this.fig = clearRot(rot);

    }
}
