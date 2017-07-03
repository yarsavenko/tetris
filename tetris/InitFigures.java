package tetris;

import java.util.ArrayList;
import java.util.Collection;

public class InitFigures {
    public static Collection<Figure> initFig()
    {
        Collection<Figure> figures = new ArrayList<>();
        int a[] = {1,1,1,1};
        int b[] = {1,0,0,0};
        int n[] = {0,0,0,0};
        Figure f = new Figure(a, n,n,n,"F1");
        figures.add(f);
        int c[] = {1,1,1,0};
        f = new Figure(c,b,n,n, "F2");
        figures.add(f);
        f = new Figure(b,c,n,n, "F3");
        figures.add(f);
        int d[] = {1,1,0,0};
        int e[] = {0,1,1,0};
        f = new Figure(d,e,n, n, "F4");
        figures.add(f);
        f = new Figure(d,d,n,n, "F5");
        figures.add(f);
        f = new Figure(e,d,n, n, "F6");
        figures.add(f);
        int g[] = {0,1,0,0};
        f = new Figure(g,c,n,n, "F7");
        figures.add(f);
        return figures;
    }
}
