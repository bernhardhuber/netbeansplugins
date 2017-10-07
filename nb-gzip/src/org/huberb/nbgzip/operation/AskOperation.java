/*
 * AskOperation.java
 *
 * Created on 16. Jänner 2006, 21:53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbgzip.operation;

import org.huberb.nbgzip.option.AskOption;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.util.NbBundle;

/**
 * Encapsulate asking the user here.
 *
 * @author HuberB1
 */
public class AskOperation {
    private final AskOption askOption;
    
    /** Creates a new instance of AskOperation */
    public AskOperation() {
        askOption = AskOption.getDefault();
    }
    
    public AskUserOption askCompress(FileObject fo) {
        if (!askOption.getAskCompress().booleanValue()) {
            return AskUserOption.YES;
        }
        
        final String fileName = fo.getNameExt();
        final String title = NbBundle.getMessage( AskOperation.class, "LBL_TitleCompress");
        final String message = NbBundle.getMessage( AskOperation.class, "LBL_AskCompress", fileName );
        
        return askTheQuestion( title, message );
    }
    
    public AskUserOption askUncompress(FileObject fo) {
        final AskOption askOption = AskOption.getDefault();
        if (!askOption.getAskUncompress().booleanValue()) {
            return AskUserOption.YES;
        }
        
        final String fileName = fo.getNameExt();
        final String title = NbBundle.getMessage( AskOperation.class, "LBL_TitleUncompress");
        final String message = NbBundle.getMessage( AskOperation.class, "LBL_AskUncompress", fileName );
        
        return askTheQuestion( title, message );
    }
    
    public AskUserOption askDelete(FileObject fo) {
        final AskOption askOption = AskOption.getDefault();
        if (!askOption.getAskDelete().booleanValue()) {
            return AskUserOption.NO;
        }
        
        final String fileName = fo.getNameExt();
        final String title = NbBundle.getMessage( AskOperation.class, "LBL_TitleDelete");
        final String message = NbBundle.getMessage( AskOperation.class, "LBL_AskDelete", fileName );
        
        return askTheQuestion( title, message );
    }
        
    protected AskUserOption askTheQuestion( String title, String message ) {
        //AskUserOption askOption;
        final String[] options = AskUserOption.getOptions();
        final String initialOption = AskUserOption.getInitialOption();
        final NotifyDescriptor d = new NotifyDescriptor(message, title,
                NotifyDescriptor.DEFAULT_OPTION,
                NotifyDescriptor.QUESTION_MESSAGE,
                options, initialOption );
        
        final Object retVal = DialogDisplayer.getDefault().notify( d );
        
        return AskUserOption.getAskOption(retVal);
    }
    
    public static enum AskUserOption {
        YES(NbBundle.getMessage(AskUserOption.class, "ASK_YES")),
        NO(NbBundle.getMessage(AskUserOption.class, "ASK_NO")),
        YES_TO_ALL(NbBundle.getMessage(AskUserOption.class, "ASK_YES_TO_ALL")),
        CANCEL(NbBundle.getMessage(AskUserOption.class, "ASK_CANCEL"));
        
        private final String option;
        
        AskUserOption(String newOption) {
            this.option = newOption;
        }
        
        public String getOption() {
            return this.option;
        }
        
        public static String[] getOptions() {
            final String[] options = new String[AskUserOption.values().length];
            int i = 0;
            for (AskUserOption auo : AskUserOption.values() ) {
                options[i++] = auo.getOption();
            }
            return options;
        }
        public static AskUserOption getDefault() {
            return NO;
        }
        public static String getInitialOption() {
            return getDefault().getOption();
        }
        public static AskUserOption getAskOption( Object retVal ) {
            for (AskUserOption auo : AskUserOption.values() ) {
                final String o = auo.getOption();
                if (retVal.equals( o )) {
                    return auo;
                }
            }
            return getDefault();
        }
    }
}
