/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client/).
 * Copyright (c) 2021 Meteor Development.
 */

package net.wurstclient;

import minegame159.meteorclient.gui.GuiConfig;
import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.renderer.Region;

public class WVerticalSeparator extends WWidget {
    @Override
    protected void onCalculateSize(GuiRenderer renderer) {
        width = 1;
        height = 0;
    }

    @Override
    protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
        renderer.quad(Region.FULL, x, y, width, height, GuiConfig.get().separator);
    }

	@Override
	protected void move(minegame159.meteorclient.gui.widgets.WWidget widget, double deltaX, double deltaY,
			boolean callMouseMoved) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onRenderWidget(minegame159.meteorclient.gui.widgets.WWidget widget, GuiRenderer renderer,
			double mouseX, double mouseY, double delta) {
		// TODO Auto-generated method stub
		
	}
}
