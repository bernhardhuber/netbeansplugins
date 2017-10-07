package org.huberb.httppost.actions;

import org.huberb.httppost.HttpPostTopComponent;
import org.huberb.httppost.model.HttpPostForm;
import org.huberb.httppost.util.ConstantsHelper;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.Task;
import org.openide.util.TaskListener;
import org.openide.util.actions.CallableSystemAction;

/**
 * Action for posting to an http url
 */
public final class HttpPostAction extends CallableSystemAction {

    @Override
    public void performAction() {
        final HttpPostTopComponent win = HttpPostTopComponent.findInstance();

        final HttpPostForm httpPostForm = win.getHttpPostForm();
        win.resetHttpPostFormView(httpPostForm);

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final String phDisplayName = NbBundle.getMessage(HttpPostAction.class, "PRG_HttpPost");
                final ProgressHandle ph = ProgressHandleFactory.createHandle(phDisplayName);
                ph.start();

                final HttpPostProcessor hpp = new HttpPostProcessor();
                httpPostForm.setStartDate(System.currentTimeMillis());
                try {
                    hpp.post(httpPostForm);
                } finally {
                    httpPostForm.setEndDate(System.currentTimeMillis());

                    ph.finish();
                }
            }
        };

        final RequestProcessor rp = RequestProcessor.getDefault();
        final RequestProcessor.Task task = rp.post(runnable);
        final MyTaskListener myTaskListener = new MyTaskListener(win, httpPostForm);
        task.addTaskListener(myTaskListener);
    }

    static class MyTaskListener implements TaskListener {

        private final HttpPostTopComponent win;
        private final HttpPostForm httpPostForm;

        MyTaskListener(HttpPostTopComponent win, HttpPostForm httpPostForm) {
            this.win = win;
            this.httpPostForm = httpPostForm;
        }

        @Override
        public void taskFinished(Task task) {
            if (this.win != null && this.httpPostForm != null) {
                this.win.publishHttpPostFormView(this.httpPostForm);
            }
        }

    }

    @Override
    public String getName() {
        return NbBundle.getMessage(HttpPostAction.class, "CTL_HttpPostAction");
    }

    @Override
    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() javadoc for more details
        putValue("noIconInMenu", Boolean.TRUE);
    }

    @Override
    public HelpCtx getHelpCtx() {
        return ConstantsHelper.getHelpCtx();
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }

}
