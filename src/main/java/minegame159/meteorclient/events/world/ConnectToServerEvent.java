/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.events.world;

public class ConnectToServerEvent {
    private static final ConnectToServerEvent INSTANCE = new ConnectToServerEvent();

    public static ConnectToServerEvent get() {
        return INSTANCE;
    }
}
