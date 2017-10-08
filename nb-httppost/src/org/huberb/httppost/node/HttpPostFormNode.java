/*
 * HttpPostFormNode.java
 *
 * Created on 27. September 2006, 22:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.httppost.node;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.Action;
import org.huberb.httppost.actions.DeleteNodeAction;
import org.huberb.httppost.actions.EditNodeAction;
import org.huberb.httppost.actions.SaveNodeAction;
import org.huberb.httppost.model.HttpPostForm;
import org.openide.ErrorManager;
import org.openide.actions.PropertiesAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.nodes.Node.Property;
import org.openide.nodes.Sheet;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author HuberB1
 */
public class HttpPostFormNode extends AbstractNode {
    
    /**
     * Creates a new instance of HttpPostFormNode
     */
    public HttpPostFormNode( HttpPostForm hpf ) {
        super(Children.LEAF, Lookups.singleton( hpf ) );
        
        this.setDisplayName( buildDisplayName( hpf ) );
    }
    
    protected String buildDisplayName( HttpPostForm hpf ) {
        String url = hpf.getUrl();
        String displayName = NbBundle.getMessage( HttpPostFormNode.class, "HttpPostForm.displayName", new Object[] {
            url,
            new Date( hpf.getStartDate() ),
            new Date( hpf.getEndDate() ),
        });
        //---
        if (displayName.length() > 80) {
            StringBuilder sb = new StringBuilder();
            sb.append( displayName.toCharArray(), 0, 20 );
            sb.append( "..." );
            displayName = sb.toString();
        }
        return displayName;
    }
    
    public HttpPostForm getHttpPostForm() {
        final HttpPostForm hpf = (HttpPostForm)getLookup().lookup(HttpPostForm.class);
        return hpf;
    }
    
    /**
     *
     * @param i
     * @return
     */
    public Image getIcon(int i) {
        Image retValue = null;
        final HttpPostForm hpf = (HttpPostForm)getLookup().lookup(HttpPostForm.class);
        final NodeIconController nic = new NodeIconController();
        retValue = nic.getIcon(hpf);
        return retValue;
    }
    
    /**
     * Display the same icon as used in getIcon
     * {@inheritDoc}
     */
    public Image getOpenedIcon(int i) {
        return getIcon(i);
    }
    
    /**
     * Create a properties sheet
     *
     * @return Sheet of this node
     */
    protected Sheet createSheet() {
        final Sheet sheet = Sheet.createDefault();
        final Sheet.Set set = sheet.createPropertiesSet();
        final HttpPostForm hpf = getHttpPostForm();
        
        try {
            final Property[] props = new Property[] {
                //----
                PropertySupportReflectionFactory.createNodeProperty(hpf, String.class, "url"),
                PropertySupportReflectionFactory.createNodeProperty(hpf, String.class, "method"),
                PropertySupportReflectionFactory.createNodeProperty(hpf, String.class, "contentType"),
                PropertySupportReflectionFactory.createNodeProperty(hpf, Boolean.class, "followRedirects"),
                PropertySupportReflectionFactory.createNodeProperty(hpf, Boolean.class, "useCache"),                
                PropertySupportReflectionFactory.createNodeProperty(hpf, String.class, "requestData" ),
                
                PropertySupportReflectionFactory.createNodeProperty(hpf, Date.class, "startDateAsDate",
                        "getStartDateAsDate", null, true, false ),
                PropertySupportReflectionFactory.createNodeProperty(hpf, Date.class, "endDateAsDate",
                        "getEndDateAsDate", null, true, false ),
                PropertySupportReflectionFactory.createNodeProperty(hpf, Long.class, "duration",
                        "getDuration", null, true, false ),
                PropertySupportReflectionFactory.createNodeProperty(hpf, Long.class, "startDate",
                        "getStartDate", null, true, false ),
                PropertySupportReflectionFactory.createNodeProperty(hpf, Long.class, "endDate",
                        "getEndDate", null, true, false ),
                
                
                //----
                PropertySupportReflectionFactory.createNodeProperty(hpf, String.class, "responseData", true, false ),
                PropertySupportReflectionFactory.createNodeProperty(hpf, String.class, "responseMessage", true, false ),
                PropertySupportReflectionFactory.createNodeProperty(hpf, Integer.class, "responseCode", true, false ),
            };
            for (int i = 0; i < props.length; i++ ) {
                set.put(props[i]);
            }
        } catch (NoSuchMethodException ex) {
            ErrorManager.getDefault().notify(ex);
        }
        sheet.put(set);
        return sheet;
    }
    
    
    public Action[] getActions(boolean b) {
        List<Action> lst = new ArrayList<Action>();
        
        Action saveAction = null;
        Node parentNode = this.getParentNode();
        if (parentNode != null && parentNode instanceof HttpPostFormFolderNode) {
            HttpPostFormFolderNode hpffn = (HttpPostFormFolderNode)parentNode;
            
            if (hpffn.getCanSave()) {
                saveAction = SaveNodeAction.get( SaveNodeAction.class );
            }
        }
        lst.add(EditNodeAction.get(EditNodeAction.class));
        lst.add(DeleteNodeAction.get(DeleteNodeAction.class));
        if (saveAction != null) {
            lst.add( saveAction );
        }
        lst.add( null );
        lst.add(PropertiesAction.get(PropertiesAction.class));
        
        return (Action[])lst.toArray(new Action[lst.size()]);
    }
    
    public boolean canRename() {
        return false;
    }

}
