package org.huber.keytool.action;

import java.io.File;
import org.huber.keytool.ui.filefilter.KeyStoreFileFilterFactory;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 * Encapsulate validating, and launching the key store open wizard.
 * <p>
 * This launcher is used by the drop target listener, and the cookie node action.
 *
 * @see org.huber.keytool.action.LoadKeyStoreDropTargetListener
 * @see org.huber.keytool.action.LoadKeyStoreCookieAction
 */
class LoadKeyStoreWizardLauncher {
    
    /*
     * Launch the key store open wizard
     */
    void launchWizard(File keyStoreFile) {
        final LoadKeyStoreAction lksa = (LoadKeyStoreAction)LoadKeyStoreAction.get(LoadKeyStoreAction.class);
        lksa.performActionWith( keyStoreFile );
    }
    
    /**
     * Validate the key store file
     */
    boolean isValidKeyStore(FileObject fo) {
        File theFile = FileUtil.toFile(fo);
        return isValidKeyStore(theFile);
    }
    /**
     * Validate the key store file
     */
    boolean isValidKeyStore(File keyStoreFile) {
        boolean isValid = true;
        
        isValid = isValid && keyStoreFile != null;
        if (isValid) {
            isValid = isValid && keyStoreFile.exists();
            isValid = isValid && keyStoreFile.canRead();
            isValid = isValid && keyStoreFile.isAbsolute();
            isValid = isValid && keyStoreFile.isFile();
            isValid = isValid && KeyStoreFileFilterFactory.accept(keyStoreFile);
        }
        return isValid;
    }
}
