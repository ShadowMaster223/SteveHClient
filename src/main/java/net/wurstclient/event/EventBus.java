/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.event;


import net.wurstclient.event.IListener;
import net.wurstclient.event.LambdaListener;
import net.wurstclient.events.ICancellable;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
;

/**
 * Default implementation of {@link IEventBus}.
 */
public class EventBus implements IEventBus {
    private final Map<Object, List<IListener>> listenerCache = new ConcurrentHashMap<>();
    private final Map<Class<?>, List<IListener>> staticListenerCache = new ConcurrentHashMap<>();

    private final Map<Class<?>, List<IListener>> listenerMap = new ConcurrentHashMap<>();

    @Override
    public <T> T post(T event) {
        List<IListener> listeners = listenerMap.get(event.getClass());

        if (listeners != null) {
            for (IListener listener : listeners) listener.call(event);
        }

        return event;
    }

    @Override
    public <T extends ICancellable> T post(T event) {
        List<IListener> listeners = listenerMap.get(event.getClass());

        if (listeners != null) {
            event.setCancelled(false);

            for (IListener listener : listeners) {
                listener.call(event);
                if (event.isCancelled()) break;
            }
        }

        return event;
    }

    @Override
    public void subscribe(Object object) {
        subscribe(getListeners(object.getClass(), object), false);
    }

    @Override
    public void subscribe(Class<?> klass) {
        subscribe(getListeners(klass, null), true);
    }

    @Override
    public void subscribe(IListener listener) {
        subscribe(listener, false);
    }

    private void subscribe(List<IListener> listeners, boolean onlyStatic) {
        for (IListener listener : listeners) subscribe(listener, onlyStatic);
    }

    private void subscribe(IListener listener, boolean onlyStatic) {
        if (onlyStatic) {
            if (listener.isStatic()) insert(listenerMap.computeIfAbsent(listener.getTarget(), aClass -> new CopyOnWriteArrayList<>()), listener);
        }
        else {
            insert(listenerMap.computeIfAbsent(listener.getTarget(), aClass -> new CopyOnWriteArrayList<>()), listener);
        }
    }

    private void insert(List<IListener> listeners, IListener listener) {
        int i = 0;
        for (; i < listeners.size(); i++) {
            if (listener.getPriority() > listeners.get(i).getPriority()) break;
        }

        listeners.add(i, listener);
    }

    @Override
    public void unsubscribe(Object object) {
        unsubscribe(getListeners(object.getClass(), object), false);
    }

    @Override
    public void unsubscribe(Class<?> klass) {
        unsubscribe(getListeners(klass, null), true);
    }

    @Override
    public void unsubscribe(IListener listener) {
        unsubscribe(listener, false);
    }

    private void unsubscribe(List<IListener> listeners, boolean staticOnly) {
        for (IListener listener : listeners) unsubscribe(listener, staticOnly);
    }

    private void unsubscribe(IListener listener, boolean staticOnly) {
        List<IListener> l = listenerMap.get(listener.getTarget());

        if (l != null) {
            if (staticOnly) {
                if (listener.isStatic()) l.remove(listener);
            }
            else l.remove(listener);
        }
    }

    private List<IListener> getListeners(Class<?> klass, Object object) {
        Function<Object, List<IListener>> func = o -> {
            List<IListener> listeners = new CopyOnWriteArrayList<>();

            getListeners(listeners, klass, object);

            return listeners;
        };

        if (object == null) return staticListenerCache.computeIfAbsent(klass, func);
        return listenerCache.computeIfAbsent(object, func);
    }

    private void getListeners(List<IListener> listeners, Class<?> klass, Object object) {
        for (Method method : klass.getDeclaredMethods()) {
            if (isValid(method)) {
                listeners.add(new LambdaListener(klass, object, method));
            }
        }

        if (klass.getSuperclass() != null) getListeners(listeners, klass.getSuperclass(), object);
    }

    private boolean isValid(Method method) {
        if (!method.isAnnotationPresent(EventHandler.class)) return false;
        if (method.getReturnType() != void.class) return false;
        if (method.getParameterCount() != 1) return false;

        return !method.getParameters()[0].getType().isPrimitive();
    }
}