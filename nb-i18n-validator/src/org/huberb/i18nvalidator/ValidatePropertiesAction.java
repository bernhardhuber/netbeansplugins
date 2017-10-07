package org.huberb.i18nvalidator;

import java.io.File;
import org.huberb.i18nvalidator.validation.MessageFormatValidator;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.actions.CookieAction;

public final class ValidatePropertiesAction extends CookieAction {

    protected void performAction(final Node[] activatedNodes) {
        // create the task
        final Runnable task = new Runnable() {
            public void run() {
                PropertiesActivatedNodeHandler  panh = new PropertiesActivatedNodeHandler();
                panh.handleActivatedNodes( activatedNodes );
            }
        };
        // start it
        RequestProcessor.getDefault().post(task);
    }

    /**
     * Handle a single selected file.
     */
    static class PropertiesActivatedNodeHandler extends AbstractPropertiesActivatedNodeHandler {
        private FileFilter ff;
        public PropertiesActivatedNodeHandler() {
            super(true);
            ff = new FileFilter();
        }
        protected void handleSingleFileObject(FileObject fo) {
//            System.out.println( "FO " + fo.getNameExt() + ", " +
//                    "mime " + fo.getMIMEType() + ", " +
//                    "path " + fo.getPath()
//                    );
            if (ff.filter(fo)) {
                File file = FileUtil.toFile(fo);
                //System.out.println("File " + file.getPath() );
                MessageFormatValidator mfv = new MessageFormatValidator();
                mfv.validate(file);
            }
        }
    }
    static class FileFilter {
        boolean filter( FileObject fo ) {
            boolean okay = true;

            okay = okay && fo.isData();
            okay = okay && !fo.isFolder();

            File file = FileUtil.toFile(fo);

            okay = okay && file.isFile();
            okay = okay && file.canRead();

            // filter name
            final String name = file.getName();
            okay = okay && name.endsWith( "properties" );
            okay = okay && !name.contains("log4j");
            okay = okay && !name.contains("build");

            // filter parent
            final String parent = file.getParent();
            okay = okay && !parent.contains("nbproject");
            okay = okay && !parent.contains("build");
            okay = okay && !parent.contains("CVS");
            return okay;
        }
    }

    protected int mode() {
        return CookieAction.MODE_ALL;
    }

    public String getName() {
        return NbBundle.getMessage(ValidatePropertiesAction.class, "CTL_ValidatePropertiesAction");
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
        return HelpCtx.DEFAULT_HELP;
    }

    protected boolean asynchronous() {
        return false;
    }

}

