package org.huberb.nbgzip.operation;

import java.io.Serializable;
import org.huberb.nbgzip.operation.AskOperation.AskUserOption;

public class ShallWeQuestions implements Serializable {
    private final static long serialVersionUID = 20060420204800L;
    
    private AskOperation.AskUserOption askUserOption;
    public ShallWeQuestions() {
        this.askUserOption = null;
    }
    public boolean shallWeAskQuestion() {
        boolean shallWeAsk = true;
        shallWeAsk = this.askUserOption == null;
        shallWeAsk = shallWeAsk || (this.askUserOption != AskUserOption.YES_TO_ALL && this.askUserOption != AskUserOption.CANCEL);
        return shallWeAsk;
    }
    public boolean shallWeDoOperation() {
        boolean shallWeDoOperation = false;
        
        shallWeDoOperation = this.askUserOption != null;
        shallWeDoOperation = shallWeDoOperation && (this.askUserOption == AskUserOption.YES || this.askUserOption == AskUserOption.YES_TO_ALL);
        return shallWeDoOperation;
    }
    public boolean shallWeTerminateDoOperation() {
        boolean shallWeTerminate = true;
        shallWeTerminate = this.askUserOption != null;
        shallWeTerminate = shallWeTerminate && this.askUserOption == AskUserOption.CANCEL;
        return shallWeTerminate;
    }
    public void setAskUserOption(AskOperation.AskUserOption newAskUserOption) {
        this.askUserOption = newAskUserOption;
    }
    public AskOperation.AskUserOption getAskUserOption() {
        return this.askUserOption;
    }
}