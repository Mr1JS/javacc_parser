import static org.junit.Assert.*;

import org.junit.Test;

//Hier muss der von Ihnen generierte Code importiert werden
import genParser.ExpressionParser;
import genParser.ParseException;
import genParser.TokenMgrError;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;

public class ExpressionParserTest {

	private ExpressionParser parser;

	/**
	 * Methode zur Initialisierung des Parsers
	 * 
	 * @param str
	 */
	private void setUp(String str) {
		StringReader sr = new StringReader(str);
		Reader r = new BufferedReader(sr);
		try {
			parser.ReInit(r);
		} catch (NullPointerException e) {
			parser = new ExpressionParser(r);
		}
	}

	/**
	 * Ab hier kommen die Ausdruecke, die vom Parser als nicht syntaktisch korrekt
	 * erkannt werden sollen.
	 * 
	 * @throws expressionParser.ParseException
	 */

	@Test(expected = ParseException.class)
	public void test_fehlendeSchliessendeKlammer() throws ParseException {

		setUp("(a+b.c");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_fehlendeOeffnendeKlammer() throws ParseException {

		setUp("a+b).c");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_fehlendeSchliessendeEckigeKlammer() throws ParseException {

		setUp("[a.b*");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_fehlendeOeffnendeEckigeKlammer() throws ParseException {

		setUp("a.b]*");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_fehlendeSchliessendeGeschweifteKlammer() throws ParseException {

		setUp("{a+b*");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_fehlendeOeffnendeGeschweifteKlammer() throws ParseException {

		setUp("a+b}*");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_beginntMitPlus() throws ParseException {

		setUp("+b*");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_beginntMitPunkt() throws ParseException {

		setUp(".b+c");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_beginntMitStern() throws ParseException {

		setUp("*b.a*");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_endetMitPlus() throws ParseException {

		setUp("a.b+");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_endetMitPunkt() throws ParseException {

		setUp("b+c.");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_fehlenderOperator() throws ParseException {

		setUp("b+ca");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_rundStattEckig() throws ParseException {

		setUp("(a.b)*");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_geschweiftStattEckig() throws ParseException {

		setUp("{a.b}*");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_rundStattGeschweift() throws ParseException {

		setUp("(a+b)*");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_eckigStattGeschweift() throws ParseException {

		setUp("[a+b]*");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_eckigStattRund() throws ParseException {

		setUp("[a+b].c");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_geschweiftStattRund() throws ParseException {

		setUp("c.{a+b}");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_unnoetigeRundeKlammern() throws ParseException {

		setUp("(a+b)");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_unnoetigeGeschweifteKlammern() throws ParseException {

		setUp("{a+b}");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_unnoetigeEckigeKlammern() throws ParseException {

		setUp("[a.b]");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			System.out.println(e);
		}
	}

	@Test(expected = ParseException.class)
	public void test_unnoetigeRundeKlammernVorn() throws ParseException {

		setUp("(a+b)+c");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_unnoetigeRundeKlammernHinten() throws ParseException {

		setUp("a+(b+c)");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_unnoetigeFalscheRundeKlammernVorn() throws ParseException {

		setUp("(a.b).c");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_unnoetigeFalscheRundeKlammernHinten() throws ParseException {

		setUp("a.(b.c)");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_unnoetigeRundeKlammernBeiStern() throws ParseException {

		setUp("(b)*");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_unnoetigeEckigeKlammernBeiStern() throws ParseException {

		setUp("[b]*");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_unnoetigeGeschweifteKlammernBeiStern() throws ParseException {

		setUp("{b}*");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_mehrereKleeneSterne() throws ParseException {

		setUp("a**");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_mehrereKleeneSterneMitGeschweiftenKlammern() throws ParseException {

		setUp("{a+c}**");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	@Test(expected = ParseException.class)
	public void test_mehrereKleeneSterneMitEckigenKlammern() throws ParseException {

		setUp("[a+c]**");

		try {
			parser.start();
		} catch (TokenMgrError e) {
			;
		}
	}

	/**
	 * Ab hier kommen die Ausdruecke, die korrekt geparst werden sollen.
	 * 
	 **/

	@Test
	public void test_geschweifteKlammern() {

		setUp("{a+b}*");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_eckigeKlammern() {

		setUp("[a.b]*");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_rundeKlammernVorn() {

		setUp("(a+b).c");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_rundeKlammernHinten() {

		setUp("c.(a+b)");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_rundeKlammernMitte() {

		setUp("c.(a+b).a");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_rundeKlammernUeberall() {

		setUp("(c+a).(a+b).(a+b)");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_rundeKlammernVornUndHinten() {

		setUp("(c+a).b.(a+b)");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_ohneKlammernAlternative() {

		setUp("c+a+b");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_ohneKlammernKonkatenation() {

		setUp("c.a.b");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_AussenSternVerfeinertDurchSternInKonkatenationHinten() {

		setUp("[c.b*]*");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_AussenSternVerfeinertDurchSternInKonkatenationVorn() {

		setUp("[a*.b]*");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_AussenSternVerfeinertDurchSternInKonkatenationBeide() {

		setUp("[a*.b*]*");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_AussenSternVerfeinertDurchSternInAlternative() {

		setUp("{c*+b}*");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_AussenSternVerfeinertDurchDreiAlternativen() {

		setUp("[(b+c).(a+b).(c+a)]*");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_AussenSternVerfeinertDurchAlternativeVorn() {

		setUp("[(b+c).a]*");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_AussenSternVerfeinertDurchAlternativeHinten() {

		setUp("[c.(a+b)]*");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_AussenSternVerfeinertDurchDreiKonkatenationen() {

		setUp("{b.c+a.b+c.a}*");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_AussenSternVerfeinertDurchKonkatenationVorn() {

		setUp("{b.c+a}*");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_AussenSternVerfeinertDurchKonkatenationHinten() {

		setUp("{c+a.b}*");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_SternAlsVerfeinerungVonKonkatenationHinten() {

		setUp("c.a*");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_SternAlsVerfeinerungVonKonkatenationVorn() {

		setUp("a*.c");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_SternAlsVerfeinerungVonKonkatenationMitte() {

		setUp("c.[a.b]*.{b+c}*.a");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_SternAlsVerfeinerungVonAlternativeHinten() {

		setUp("c+a*");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_SternAlsVerfeinerungVonAlternativeVorn() {

		setUp("a*+c");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_SternAlsVerfeinerungVonAlternativeMitte() {

		setUp("c+[a.b]*+{b+c}*+a");
		try {
			parser.start();

		} catch (ParseException p) {
			fail(p.getMessage());
		} catch (TokenMgrError e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Ab hier kommen die Ausdruecke, die es nicht bis zum Parser schaffen.
	 */

	@Test(expected = TokenMgrError.class)
	public void test_falschesTerminal() throws TokenMgrError {

		setUp("a.b.c.a.d");

		try {
			parser.start();
		} catch (ParseException p) {
			;
		}
	}

	@Test(expected = TokenMgrError.class)
	public void test_falscherOperator() throws TokenMgrError {

		setUp("a|b.c");

		try {
			parser.start();
		} catch (ParseException p) {
			;
		}
	}

}