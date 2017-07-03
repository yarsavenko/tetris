package tetris;


import static tetris.Constants.WIN_HEIGHT;
import static tetris.Constants.WIN_WIDTH;

public class Field {
    private int x;
    private int y;
    private int fX;
    private int fY;
    private Figure curFigure;
    private Figure nextFigure;
    private int field[][];
    private int score;

    public Figure getNextFigure(){
        return  nextFigure;
    }
    public int getScore(){
        return score;
    }
    public void removeOne(int a){
        this.score++;
        int b = 0;
        while (b < this.field[a].length){
            this.field[a][b] = 0;
            b++;
        }
        while (a > 0){
            field[a] = field[a - 1];
            a--;
        }
    }
    public void tryRemove(){
        int a = 0;
        int b;
        int c;
        while (a < this.field.length){
            b = 0;
            c = 0;
            while (b < this.field[a].length){
                if (this.field[a][b] == 1)
                    c++;
                b++;
            }
            if (b == c)
                removeOne(a);
            a++;
        }
    }
    public int[][] getField(){
        return field;
    }
    public boolean saveField(){
        int count = 0;
        for (int k[]: field){
            System.out.print(count+" ");
            for (int h: k){
                System.out.print(h+" ");
            }
            System.out.println();
            count+=25;
        }
        System.out.println();
        int yy = y;
        int yyy = y;
        int xxx;
        int asd = 0;
        int bsd = 0;
        while (asd < curFigure.getFig().length){
            System.out.println(y);
            xxx = x;
            bsd = 0;
            while (bsd < curFigure.getFig()[asd].length){
                if (xxx < WIN_WIDTH * 25 && yyy < WIN_HEIGHT * 25 &&((curFigure.getFig()[asd][bsd] == 1 && yyy == fY - 25 )|| ( asd > 0 &&curFigure.getFig()[asd-1][bsd] == 1 && field[yyy/25][xxx/25]==1) ||
                        (curFigure.getFig()[0][0] == 1 && curFigure.getFig()[3][0] == 1)&& y+100 < fY && field[y/25+4][x/25]==1))
                        {

                    yy =y;
                            System.out.println("yyy = "+yyy);
                            System.out.println("yyy = "+yy);
                    for (int k[] :curFigure.getFig()){
                        int xa = x;
                        for (int l:k){
                            if (l == 1)
                                field[(yy)/25][xa/25] = 1;
                            xa+=25;
                        }
                        yy+=25;
                    }
                    x = (WIN_WIDTH/2)*25;
                    y = 100;
                    curFigure = null;
                    return true;
                }
                xxx+=25;
                bsd++;
            }
            asd++;
            yyy+=25;
        }
        return false;
    }
    public Field(int fx, int fy){
        this.fX = fx;
        this.fY = fy;
        field = new int[fy/25][fx/25];
        this.score = 0;
        initField();
    }

    private void initField(){
        for(int a[]:field){
            for(int b: a){
                b = 0;
            }
        }
    }
    public boolean isFigure(){
        if (curFigure == null)
            return false;
        return true;
    }
    public Figure getCurFigure() {
        return curFigure;
    }

    public void setCurFigure(Figure curFigure, Figure nextFigure) {
        if (this.nextFigure == null)
            this.curFigure = curFigure;
        else
            this.curFigure = this.nextFigure;
        this.nextFigure = nextFigure;
    }

    public int getX() {
        return x;
    }
    private int getKray(){
                int kray = fX;
        int a = 3;
        while (a >= 0)
        {
            if (curFigure.getFig()[0][a] == 0 &&curFigure.getFig()[1][a] == 0 &&curFigure.getFig()[2][a] == 0 && curFigure.getFig()[3][a] == 0)
                kray-=0;
            else
                kray-=25;
            a--;
        }
        return kray + 25;
    }
    public boolean isTouch(int move){

        int xa = x;
        int ya = y;
        for(int row[]: curFigure.getFig()){
            xa = x;
            for (int dd: row){
                if (dd == 1 && move > 0 && xa < fX - 25 && field[ya/25][xa/25+1] == 1)
                    return false;
                if (dd == 1 && move < 0 && xa > 0 && field[ya/25][xa/25 - 1]==1)
                    return false;
                xa+=25;
            }
            ya+=25;
        }
        return true;
    }
    public void setX(int x) {
        if (curFigure == null)
            this.x = x;
        else {
            int a = getKray();
            if (isTouch(x) && this.x + x >= 0 && this.x + x <a)
                this.x += x;
        }
    }
    public boolean isOver(){
        int a = 0;
        while (a < field[fY/25/4].length){
            if (field[4][a] == 1)
                return true;
            a++;
        }
        return false;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
