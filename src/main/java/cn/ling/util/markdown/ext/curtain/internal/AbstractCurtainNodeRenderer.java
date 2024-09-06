package cn.ling.util.markdown.ext.curtain.internal;

import cn.ling.util.markdown.ext.curtain.Curtain;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;

import java.util.Collections;
import java.util.Set;

abstract class AbstractCurtainNodeRenderer implements NodeRenderer {
    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return Collections.singleton(Curtain.class);
    }
}
