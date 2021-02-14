/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.hacks;



import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.wurstclient.Category;
import net.wurstclient.SearchTags;
import net.wurstclient.events.UpdateListener;
import net.wurstclient.hack.Hack;
import net.wurstclient.settings.CheckboxSetting;
import net.wurstclient.settings.SliderSetting;
import net.wurstclient.settings.SliderSetting.ValueDisplay;

@SearchTags({"BoatFlight", "boat fly", "boat flight"})
public final class BoatFlyHack extends Hack implements UpdateListener
{
	
	


	
	private final SliderSetting speedZ = new SliderSetting("Speed Z",
			"\u00a74\u00a7lWARNING:\u00a7r High speeds can be fast!",
			.5, -10, 10, 1, ValueDisplay.INTEGER);
	
	private final SliderSetting speedX = new SliderSetting("Speed X",
			"\u00a74\u00a7lWARNING:\u00a7r High speeds can be fast!",
			.5, -10, 10, 1, ValueDisplay.INTEGER);
	
	private final CheckboxSetting ManualSpeed = new CheckboxSetting(
			"Manual", "Just normal boat fly.", false);
			
	private final CheckboxSetting AutoSpeed = new CheckboxSetting(
			"Fast Rise", "Goes up fast, what does it sound like", false);
			
	private final CheckboxSetting BoatFloat = new CheckboxSetting(
			"Float", "Float da Boat", false);
	public BoatFlyHack()
	{
		super("BoatFly", "Allows you to fly with boats");
		setCategory(Category.MOVEMENT);
		
		addSetting(speedZ);
		addSetting(speedX);
		addSetting(ManualSpeed);
		addSetting(AutoSpeed);
		addSetting(BoatFloat);
		
	}
	{
	
	}
	
	@Override
	public void onEnable()
	{
		EVENTS.add(UpdateListener.class, this);
	}
	
	@Override
	public void onDisable()
	{
		EVENTS.remove(UpdateListener.class, this);
	}	
	
	@Override
	public void onUpdate()
	{
		// check if riding
		if(!MC.player.hasVehicle())
			return;
		
		Entity vehicle = MC.player.getVehicle();
		Vec3d velocity = vehicle.getVelocity();
		double motionY = MC.options.keyJump.isPressed() ? 0.3 : 0 ;
		vehicle.setVelocity(new Vec3d( speedZ.getValue(), motionY, speedX.getValue()));

		{
	if((ManualSpeed.isChecked() && MC.player.hasVehicle()))	
		vehicle.setVelocity(new Vec3d( velocity.x, motionY, velocity.z));
		
	else { vehicle.setVelocity(new Vec3d( speedZ.getValue(), motionY, speedX.getValue()));
	
	


	}
	}if ((AutoSpeed.isChecked() && MC.player.hasVehicle()))
			vehicle.setVelocity(new Vec3d( velocity.x, motionY+2, velocity.z));
		} 
			
		

	{
}}