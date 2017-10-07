/*
 * ProjectHelper.java
 *
 * Created on 02. Juni 2007, 12:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.misc;

import org.netbeans.api.project.Project;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author HuberB1
 */
public class ProjectHelper {
    
    /** Creates a new instance of ProjectHelper */
    public ProjectHelper() {
    }
    
    public static Project findProject( Node node ) {
        Project project = null;
        Node theNode = node;
        
        while (project == null && theNode != null) {
            Lookup lookup = theNode.getLookup();
            project = (Project)lookup.lookup(Project.class);
            if (project == null) {
                project = (Project)theNode.getCookie(Project.class);
            }
            theNode = theNode.getParentNode();
        }
        return project;
    }
}
