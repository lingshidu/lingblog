package cn.ling.util.markdown.ext.curtain.internal;

import cn.ling.constant.CommonConstants;
import cn.ling.util.markdown.ext.cover.internal.CoverDelimiterProcessor;
import cn.ling.util.markdown.ext.curtain.Curtain;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.delimiter.DelimiterProcessor;
import org.commonmark.parser.delimiter.DelimiterRun;

/**
 * 定界
 */
public class CurtainDelimiterProcessor implements DelimiterProcessor {
    @Override
    public char getOpeningCharacter() {
        return '@';
    }

    @Override
    public char getClosingCharacter() {
        return '@';
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
			// Wrap nodes between delimiters in hei_mu.
			Node curtain = new Curtain();
			return CoverDelimiterProcessor.sourceSpansMethod(openingRun, closingRun, opener, curtain);
		}
		return 0;
	}
}
