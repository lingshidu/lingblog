package cn.ling.util.markdown.ext.cover.internal;

import cn.ling.constant.CommonConstants;
import cn.ling.util.markdown.ext.cover.Cover;
import org.commonmark.node.Node;
import org.commonmark.node.Nodes;
import org.commonmark.node.SourceSpans;
import org.commonmark.node.Text;
import org.commonmark.parser.delimiter.DelimiterProcessor;
import org.commonmark.parser.delimiter.DelimiterRun;

/**
 * 定界
 */
public class CoverDelimiterProcessor implements DelimiterProcessor {
    @Override
    public char getOpeningCharacter() {
        return '%';
    }

    @Override
    public char getClosingCharacter() {
        return '%';
    }

    @Override
    public int getMinLength() {
        return 2;
    }

	@Override
	public int process(DelimiterRun openingRun, DelimiterRun closingRun) {
		if (openingRun.length() >= CommonConstants.TWO && closingRun.length() >= CommonConstants.TWO) {
			// Use exactly two delimiters even if we have more, and don't care about internal openers/closers.
			Text opener = openingRun.getOpener();

			// Wrap nodes between delimiters in cover.
			Node cover = new Cover();

			return sourceSpansMethod(openingRun, closingRun, opener, cover);
		}
		return 0;
	}

	public static int sourceSpansMethod(DelimiterRun openingRun, DelimiterRun closingRun, Text opener, Node cover) {
		SourceSpans sourceSpans = new SourceSpans();
		sourceSpans.addAllFrom(openingRun.getOpeners(2));

		for (Node node : Nodes.between(opener, closingRun.getCloser())) {
			cover.appendChild(node);
			sourceSpans.addAll(node.getSourceSpans());
		}

		sourceSpans.addAllFrom(closingRun.getClosers(2));
		cover.setSourceSpans(sourceSpans.getSourceSpans());

		opener.insertAfter(cover);
		return 2;
	}
}
