/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.commands;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.util.math.Vec3d;
import net.wurstclient.command.CmdException;
import net.wurstclient.command.CmdSyntaxError;
import net.wurstclient.command.Command;
import net.wurstclient.util.MathUtils;




public final class HClipCmd extends Command
{
	public HClipCmd()
	{
		super("hclip", "Lets you clip through blocks horizontally.\n"
			+ "Face the direction you want to clip", ".hclip <distance>");
	}
	
	@Override
	public void call(String[] args) throws CmdException {
	
	{
		if(args.length != 1)
			throw new CmdSyntaxError();
		
		if(!MathUtils.isInteger(args[0]))
			throw new CmdSyntaxError();
		
		
		ClientPlayerEntity player = MC.player;

		
		
	
		 Vec3d forward = Vec3d.fromPolar(0, player.yaw).normalize();
		 player.updatePosition(player.getX() + forward.x * Integer.parseInt(args[0]), player.getY(), player.getZ() + forward.z * Integer.parseInt(args[0]));
	}
}

	
	}

