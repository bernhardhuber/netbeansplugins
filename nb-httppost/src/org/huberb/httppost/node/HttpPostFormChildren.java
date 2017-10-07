/*
 * HttpPostFormChildren.java
 *
 * Created on 27. September 2006, 22:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.httppost.node;

import org.huberb.httppost.model.HttpPostForm;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author HuberB1
 */
public class HttpPostFormChildren extends Children.Keys {
    
    /** Creates a new instance of HttpPostFormChildren */
    public HttpPostFormChildren() {
    }

    @Override
    protected Node[] createNodes(Object object) {
        final HttpPostForm hpf = (HttpPostForm)object;
        final HttpPostFormNode hpfn = new HttpPostFormNode(hpf);
        return new Node[] {hpfn};
    }
    
}
