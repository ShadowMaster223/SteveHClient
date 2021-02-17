/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.event;

/**
 * Cancellable events need to implement this interface.
 */
public interface ICancellable {
    /**
     * Sets if this event is cancelled.
     * @param cancelled Is cancelled
     */
    void setCancelled(boolean cancelled);

    /**
     * Cancels this event.
     */
    default void cancel() { setCancelled(true); }

    /**
     * @return True if this event is cancelled.
     */
    boolean isCancelled();
}