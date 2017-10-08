package org.netbeans.modules.policysupport.actions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.openide.ErrorManager;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CookieAction;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 * This action parses the policy.
 * <p>
 * The <code>sun.security.provider.PolicyParser</code> is used for
 * parsing the policy.
 * <p>
 * The action is tied to the policy mime-type.
 *
 */
public final class ParsePolicyAction extends CookieAction {
    
    /**
     * Parse the policy
     *
     * @param activatedNodes this array define the policy file(s)
     */
    protected void performAction(Node[] activatedNodes) {
        final DataObject c = (DataObject) activatedNodes[0].getCookie(DataObject.class);
        final FileObject fo = c.getPrimaryFile();
        if (fo != null && !fo.isFolder()) {
            final File file = FileUtil.toFile( fo );
            readPolicyFile( file );
        }
    }
    
    /**
     * Parse the policy
     *
     * @param file the policy file
     */
    protected void readPolicyFile( File file ) {
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            
            // clear (reset) the output window
            final OutputWriter ow = getOutputWriter();
            ow.reset();
            
            sun.security.provider.PolicyParser policyParser = new sun.security.provider.PolicyParser();
            policyParser.read( fr );
            
            // TODO show the parsed policy objects
            
            // show a success message in the output window
            showSuccess( file );
        } catch (sun.security.provider.PolicyParser.ParsingException pe) {
            // show the parsing exception in the output window
            showParsingException(pe);
        } catch (IOException ioe) {
            ErrorManager.getDefault().notify(ioe);
        } finally {
            // always close the parse policy file
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException ex) {
                    // ignore this exception
                }
            }
        }
    }

    /**
     * Show a simple success message in the output window
     *
     * @param file the policy file
     */
    private void showSuccess( File file ) {
        final OutputWriter ow = getOutputWriter();
        final String message = NbBundle.getMessage( ParsePolicyAction.class, "MSG_SUCCESS",
                new Object[] {file.getAbsolutePath() } );
        ow.println( message );
    }
    /**
     * Show the parsing exception in the output window
     *
     * @param pe the policy parser parsing exception
     */
    private void showParsingException( sun.security.provider.PolicyParser.ParsingException pe ) {
        final OutputWriter ow = getOutputWriter();
        ow.println( pe );
    }

    /**
     * Get an NB OutputWriter.
     *
     * @return OutputWriter to write messages to.
     */
    private OutputWriter getOutputWriter() {
        final String tabName = NbBundle.getMessage( ParsePolicyAction.class, "CTL_TABNAME");
        InputOutput io = IOProvider.getDefault().getIO(tabName, false);
        OutputWriter ow = io.getOut();
        return ow;
        
    }
    
    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }
    
    public String getName() {
        return NbBundle.getMessage(ParsePolicyAction.class, "CTL_ParsePolicyAction");
    }
    
    protected Class[] cookieClasses() {
        return new Class[] {
            DataObject.class
        };
    }
    
    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() javadoc for more details
        putValue("noIconInMenu", Boolean.TRUE);
    }
    
    public HelpCtx getHelpCtx() {
        final HelpCtx helpCtx = HelpCtx.findHelp( "org.netbeans.modules.policysupport.about" );
        return helpCtx;
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
}

