/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.event;

import net.wurstclient.event.IListener;
import net.wurstclient.events.ICancellable;

/**
 * Manages event listeners.
 */
public interface IEventBus {
    /**
     * Posts an event to all subscribed event listeners.
     * @param event Event to post
     * @param <T> Type of the event
     * @return Event passed in
     */
    <T> T post(T event);

    /**
     * Posts a cancellable event to all subscribed event listeners. Stops after the event was cancelled.
     * @param event Event to post
     * @param <T> Type of the event
     * @return Event passed in
     */
    <T extends ICancellable> T post(T event);

    /**
     * Finds all correct (static and non-static) methods with {@link EventHandler} annotation and subscribes them.
     * @param obje@Override
	ct The object to scan for methods
     */
    void subscribe(Object object);

    /**
     * Finds all correct (static only) methods with {@link EventHandler} annotation and subscribes them.
     * @param klass The class to scan for methods
     */
    void subscribe(Class<?> klass);

    /**
     * Subscribes the listener (both static and non-static).
     * @param listener Listener to subscribe
     */
    void subscribe(IListener listener);

    /**
     * Finds all correct (static and non-static) methods with {@link EventHandler} annotation and unsubscribes them.
     * @param object The object to scan for methods
     */
    void unsubscribe(Object object);

    /**
     * Finds all correct (static only) methods with {@link EventHandler} annotation and unsubscribes them.
     * @param klass The class to scan for methods
     */
    void unsubscribe(Class<?> klass);

    /**
     * Unsubscribes the listener (both static and non-static).
     * @param listener Listener to unsubscribe
     */
    void unsubscribe(IListener listener);
}