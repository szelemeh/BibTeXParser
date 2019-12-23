package parsers;

import model.Name;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@SuppressWarnings("unchecked")
public class NameParserTest {
    NameParser parser = new NameParser();
    @Test
    public void getLastNames() {
        String fieldValue = "Stas Stan von Me best Shelemekh Kulpa and kon Pon don Slowiak, Kuba and qon Son ron Balicka, Dr Nive, Alina";
        ArrayList<Name> names = (ArrayList<Name>) parser.getNames(fieldValue);
        assertEquals(new Name("Stas~Stan~", "Shelemekh~Kulpa~", "von~Me~best~", null), names.get(0));
        assertEquals(new Name("Kuba~", "Slowiak~", "kon~Pon~don~", null), names.get(1));
        assertEquals(new Name("Alina~", "Balicka~", "qon~Son~ron~", "Dr~Nive~"), names.get(2));
    }
}