package org.bitionaire.elbombillo.ui.gateway.core;

/**
 * Created by jzinnau on 12.06.2015.
 */
public enum IdeaStatus {
    NEW("new"),
    RUNNING("running"),
    FINISHED("finished")
    ;

    private final String text;

    private IdeaStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static IdeaStatus fromString(String text) {
        if (text != null) {
            for (IdeaStatus b : IdeaStatus.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }
}
