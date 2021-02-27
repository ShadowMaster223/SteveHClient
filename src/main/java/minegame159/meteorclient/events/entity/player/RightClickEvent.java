/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.events.entity.player;

public class RightClickEvent {
    private static final RightClickEvent INSTANCE = new RightClickEvent();

    public static RightClickEvent get() {
        return INSTANCE;
    }
}
