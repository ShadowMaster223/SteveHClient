
package minegame159.meteorclient.modules.crash;

import minegame159.meteorclient.modules.Category;
import minegame159.meteorclient.modules.Module;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;
import net.minecraft.text.LiteralText;

public class KillPotion extends Module {
public KillPotion(Category category, String name, String description) {
		super(category, name, description);
}

    public KillPotion() {
        super(Category.Crash, "KillPotion", "Generates a potion that kills everything");
    }

	@Override
	public void onActivate()
	{
		// check gamemode
		if(!mc.player.abilities.creativeMode)
		{
			ChatUtils.error("Creative mode only.");
			toggle(false);
			return;
		}
		
		// generate potion
		ItemStack stack = new ItemStack(Items.SPLASH_POTION);
		CompoundTag effect = new CompoundTag();
		effect.putInt("Amplifier", 125);
		effect.putInt("Duration", 2000);
		effect.putInt("Id", 6);
		ListTag effects = new ListTag();
		effects.add(effect);
		CompoundTag nbt = new CompoundTag();
		nbt.put("CustomPotionEffects", effects);
		stack.setTag(nbt);
		String name = "\u00a7rSplash Potion of \u00a74\u00a7lINSTANT DEATH";
		stack.setCustomName(new LiteralText(name));
		
		// give potion
		if(placeStackInHotbar(stack))
			ChatUtils.info("Potion created.");
		else
			ChatUtils.error("Please clear a slot in your hotbar.");
		
		toggle(false);
	}
	
	private boolean placeStackInHotbar(ItemStack stack)
	{
		for(int i = 0; i < 9; i++)
		{
			if(!mc.player.inventory.getStack(i).isEmpty())
				continue;
			
			mc.player.networkHandler.sendPacket(
				new CreativeInventoryActionC2SPacket(36 + i, stack));
			return true;
		}
		
		return false;
	}
}
