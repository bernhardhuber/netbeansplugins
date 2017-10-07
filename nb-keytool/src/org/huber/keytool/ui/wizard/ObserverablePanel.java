/*
 * ObserverablePanel.java
 *
 * Created on 17. Februar 2006, 23:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.ui.wizard;

/**
 * The interface of an visual wizard panel.
 *
 * @author HuberB1
 */
public interface ObserverablePanel {
    /**
     * Bind ui components to the change observer.
     */
    void bind(ChangeObserverOfWizardPanel changeObserver);
    
    /**
     * Test validity of user input
     */
    ValidUserEntryResult isValidUserEntry();
    
    static class ValidUserEntryResult {
        private boolean isValid;
        private String invalidMessage;
        public ValidUserEntryResult( ) {
            this.isValid = true;
        }
        public ValidUserEntryResult( boolean isValid, String invalidMessage ) {
            this.isValid = isValid;
            this.invalidMessage = invalidMessage;
        }
        public boolean isValid() {
            return this.isValid;
        }
        public void setIsValid( boolean isValid ) {
            this.isValid = isValid;
        }
        public String getInvalidMessage() {
            return this.invalidMessage;
        }
        public void setInvalidMessage( String invalidMessage ) {
            this.invalidMessage = invalidMessage;
            if (this.invalidMessage != null && this.invalidMessage.length() > 0) {
                this.setIsValid( false );
            } else {
                this.setIsValid( true );
            }
        }
    }
    
    /**
     * Reset the visual wizard panel.
     */
    void reset();
}
