package org.huberb.httppost.node;

import java.awt.Image;
import org.huberb.httppost.model.HttpPostForm;
import org.openide.util.ImageUtilities;

/**
 * A simple HelpClass mapping the httpPostForm to an icon representation
 */
class NodeIconController {

    private static final String BASE_PATH = "org/huberb/httppost/images/";

    private static final Image IMAGE_GET = ImageUtilities.loadImage(BASE_PATH + "get.gif"); // NOI18N
    private static final Image IMAGE_POST = ImageUtilities.loadImage(BASE_PATH + "post.gif"); // NOI18N
    private static final Image IMAGE_OTHER = ImageUtilities.loadImage(BASE_PATH + "other.gif"); // NOI18N

    private static final Image INFO_BADGE = ImageUtilities.loadImage(BASE_PATH + "infoBadge.gif"); // NOI18N
    private static final Image WARNING_BADGE = ImageUtilities.loadImage(BASE_PATH + "warningBadge.gif"); // NOI18N
    private static final Image ERROR_BADGE = ImageUtilities.loadImage(BASE_PATH + "errorBadge.gif"); // NOI18N

    private static final Image IMAGE_GET_INFO = ImageUtilities.mergeImages(IMAGE_GET, INFO_BADGE, 0, 0);
    private static final Image IMAGE_POST_INFO = ImageUtilities.mergeImages(IMAGE_POST, INFO_BADGE, 0, 0);
    private static final Image IMAGE_OTHER_INFO = ImageUtilities.mergeImages(IMAGE_OTHER, INFO_BADGE, 0, 0);

    private static final Image IMAGE_GET_WARNING = ImageUtilities.mergeImages(IMAGE_GET, WARNING_BADGE, 0, 0);
    private static final Image IMAGE_POST_WARNING = ImageUtilities.mergeImages(IMAGE_POST, WARNING_BADGE, 0, 0);
    private static final Image IMAGE_OTHER_WARNING = ImageUtilities.mergeImages(IMAGE_OTHER, WARNING_BADGE, 0, 0);

    private static final Image IMAGE_GET_ERROR = ImageUtilities.mergeImages(IMAGE_GET, ERROR_BADGE, 0, 0);
    private static final Image IMAGE_POST_ERROR = ImageUtilities.mergeImages(IMAGE_POST, ERROR_BADGE, 0, 0);
    private static final Image IMAGE_OTHER_ERROR = ImageUtilities.mergeImages(IMAGE_OTHER, ERROR_BADGE, 0, 0);

    public Image getIcon(HttpPostForm hpf) {
        Image retValue = null;
        final Integer statusCode = hpf.getResponseCode();
        if (hpf.isGetMethod()) {
            // Get icon
            if (statusCode >= 400 || statusCode < 0) {
                retValue = IMAGE_GET_ERROR;
            } else if (statusCode >= 300) {
                retValue = IMAGE_GET_WARNING;
            } else if (statusCode >= 200) {
                retValue = IMAGE_GET;
            } else {
                retValue = IMAGE_GET_INFO;
            }
        } else if (hpf.isPostMethod()) {
            // Post icon
            if (statusCode >= 400 || statusCode < 0) {
                retValue = IMAGE_POST_ERROR;
            } else if (statusCode >= 300) {
                retValue = IMAGE_POST_WARNING;
            } else if (statusCode >= 200) {
                retValue = IMAGE_POST;
            } else {
                retValue = IMAGE_POST_INFO;
            }
        } else {
            // Other icon
            if (statusCode >= 400 || statusCode < 0) {
                retValue = IMAGE_OTHER_ERROR;
            } else if (statusCode >= 300) {
                retValue = IMAGE_OTHER_WARNING;
            } else if (statusCode >= 200) {
                retValue = IMAGE_OTHER;
            } else {
                retValue = IMAGE_OTHER_INFO;
            }
        }
        return retValue;
    }
}
