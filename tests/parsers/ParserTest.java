package parsers;

import model.Name;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@SuppressWarnings("unchecked")
public class ParserTest {
    Parser parser = new Parser("//");
    @Test
    public void getLastNames() {
        String fieldValue = "Stas von Shelemekh and kon Slowiak, Kuba and qon Balicka, Dr ,Alina";
//        ArrayList<Name> names = (ArrayList<Name>) parser.getNames(fieldValue);
//        assertEquals(names.get(0), new Name("Stas", "Shelemekh", "V.", null));
//        assertEquals(names.get(0), new Name("Kuba", "Slowiak", "K.", null));
//        assertEquals(names.get(0), new Name("Alina", "Balicka", "Q.", "Dr"));

        String test = "Stas von Shelemekh";
        assertEquals(
                parser.parseRawNameWithoutCommas(test),
                new Name("Stas", "Shelemekh", "von", null));
    }
}