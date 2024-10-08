package cn.ling.util.markdown.ext.cover.internal;

import cn.ling.util.markdown.ext.cover.Cover;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;

import java.util.Collections;
import java.util.Set;

abstract class AbstractCoverNodeRenderer implements NodeRenderer {
    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return Collections.singleton(Cover.class);
    }
}
