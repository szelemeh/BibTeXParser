import model.*;
import parsers.Parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        StringTokenizer t = new StringTokenizer("mar = ", "=");
        String n = t.nextToken();
        String v = t.nextToken();
        System.out.println("name: -"+n+"-");
        System.out.println("value: -"+v+"-\n"+"Is empty: "+v.isEmpty()+"\nIs blank: "+v.isBlank());

    }
}
