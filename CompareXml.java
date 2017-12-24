import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;
import org.xmlunit.builder.DiffBuilder;

public class CompareXml {

	public static void main(String[] args) throws SAXException, IOException {

		final String control = "<a><amount>1</amount><date>2017-10-10</date><state>VA</state></a>";
		final String test = "<a><amount>2</amount><date>2016-10-10</date><state>WI</state></a>";

		List<String> elementNames = new ArrayList<String>();
		elementNames.add("date");
		elementNames.add("state");

		org.xmlunit.diff.Diff myDiff = DiffBuilder.compare(control).withTest(test).ignoreWhitespace().ignoreComments()
				.withDifferenceEvaluator(new ElementDifferenceEvaluator(elementNames))
				.checkForSimilar().build();

		System.out.println(myDiff.toString());
		System.out.println(myDiff.hasDifferences());
		System.out.println(myDiff.getDifferences());

	}

}
