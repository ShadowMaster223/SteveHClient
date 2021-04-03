/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.commands.commands;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import minegame159.meteorclient.commands.Command;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.packet.c2s.play.BookUpdateC2SPacket;

public final class Dupe extends Command
{
	public Dupe()
	{
		super("dupe", "Duplicates items using a book & quill.");
	}
	
	
	
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(context -> {
           
            
           
		
		if(mc.player.inventory.getMainHandStack()
			.getItem() != Items.WRITABLE_BOOK)
		{
			ChatUtils.error("You must hold a book & quill in your main hand.");
			
		}
		
		ListTag listTag = new ListTag();
		
		StringBuilder builder1 = new StringBuilder();
		for(int i = 0; i < 21845; i++)
			builder1.append((char)2077);
		
		listTag.addTag(0, StringTag.of(builder1.toString()));
		
		StringBuilder builder2 = new StringBuilder();
		for(int i = 0; i < 32; i++)
			builder2.append("Steve Hamish");
		
		String string2 = builder2.toString();
		for(int i = 1; i < 40; i++)
			listTag.addTag(i, StringTag.of(string2));
		
		ItemStack bookStack = new ItemStack(Items.WRITABLE_BOOK, 1);
		bookStack.putSubTag("title",
			StringTag.of("No Worky"));
		bookStack.putSubTag("pages", listTag);
		
		mc.player.networkHandler.sendPacket(new BookUpdateC2SPacket(bookStack,
			true, mc.player.inventory.selectedSlot));
	
		return SINGLE_SUCCESS;
    });

}
}