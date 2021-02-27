/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.events.meteor;

public class MiddleMouseButtonEvent {
    private static final MiddleMouseButtonEvent INSTANCE = new MiddleMouseButtonEvent();

    public static MiddleMouseButtonEvent get() {
        return INSTANCE;
    }
}
