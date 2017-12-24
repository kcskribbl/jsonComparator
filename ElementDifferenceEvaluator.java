import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.ComparisonResult;
import org.xmlunit.diff.DifferenceEvaluator;

public class ElementDifferenceEvaluator implements DifferenceEvaluator {

	private String elementName;

	private List<String> elementNames;

	public ElementDifferenceEvaluator(String elementName) {
		this.elementName = elementName;
	}

	public ElementDifferenceEvaluator(List<String> elementNames) {
		this.elementNames = elementNames;
	}

	@Override
	public ComparisonResult evaluate(Comparison comparison, ComparisonResult outcome) {
		if (outcome == ComparisonResult.EQUAL)
			return outcome; // only evaluate differences.
		final Node controlNode = comparison.getControlDetails().getTarget();
		final Node testNode = comparison.getTestDetails().getTarget();
		if (controlNode.getParentNode() instanceof Element && testNode.getParentNode() instanceof Element) {
			Element controlElement = (Element) controlNode.getParentNode();
			Element testElement = (Element) testNode.getParentNode();
			if (elementName != null) {
				if (controlElement.getNodeName().equals(elementName)) {
					return ComparisonResult.SIMILAR;
				}
			} else if (elementNames != null) {
				for (String elString : elementNames) {
					if (controlElement.getNodeName().equals(elString)) {
						return ComparisonResult.SIMILAR;
					}
				}

			}
		}
		return outcome;
	}
}
