/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.events.meteor;

public class ActiveModulesChangedEvent {
    private static final ActiveModulesChangedEvent INSTANCE = new ActiveModulesChangedEvent();

    public static ActiveModulesChangedEvent get() {
        return INSTANCE;
    }
}
