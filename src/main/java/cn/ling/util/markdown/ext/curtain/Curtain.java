package cn.ling.util.markdown.ext.curtain;

import org.commonmark.node.CustomNode;
import org.commonmark.node.Delimited;

/**
 * A heimu node containing text and other inline nodes nodes as children.
 */
public class Curtain extends CustomNode implements Delimited {
	private static final String DELIMITER = "@@";

	@Override
	public String getOpeningDelimiter() {
		return DELIMITER;
	}

	//TODO 暂时无法解决 markdown 方法内部实现相同（怀疑是用的切面）
	@Override
	public String getClosingDelimiter() {
		return DELIMITER;
	}
}
