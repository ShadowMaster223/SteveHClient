/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.modules.misc;

//Created by squidoodly 06/07/2020 AT FUCKING 12:00AM KILL ME

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.TextHandlerAccessor;
import minegame159.meteorclient.mixininterface.IClientPlayerInteractionManager;
import minegame159.meteorclient.modules.Category;
import minegame159.meteorclient.modules.Module;
import minegame159.meteorclient.settings.*;
import minegame159.meteorclient.utils.player.ChatUtils;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.Items;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.packet.c2s.play.BookUpdateC2SPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Style;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.stream.IntStream;



public class PopBob extends Module {
    private static final int LINE_WIDTH = 113;

    public enum Mode{ // Edna Mode
        PopBob
    }

    public PopBob(){
        super(Category.Misc, "popbob", "Support for .d"); //Grammar who? / too ez.
    }

    private final SettingGroup sgGeneral = settings.getDefaultGroup();//Obligatory comment.

    private final Setting<Mode> mode = sgGeneral.add(new EnumSetting.Builder<Mode>() //WEEEEEEEEEEEEEEEEEEEE (Wanted to add a comment on everything but nothing to say so fuck you.)
            .name("mode")
            .description("The mode of the book bot.")
            .defaultValue(Mode.PopBob)
            .build()
    );
    //Idk how to add the name into the book so you're gonna have to do it or tell me.
    private final Setting<String> name = sgGeneral.add(new StringSetting.Builder()
            .name("name")
            .description("The name you want to give your books.")
            .defaultValue("Mr Hamish!") 
            .build()
    );

    private final Setting<String> fileName = sgGeneral.add(new StringSetting.Builder()
            .name("file-name")
            .description("The name of the text file (.txt NEEDED)") //Some retard will do it without and complain like a tard.
            .defaultValue("book.txt")
            .build()
    );

    private final Setting<Integer> noOfPages = sgGeneral.add(new IntSetting.Builder()
            .name("no-of-pages")
            .description("The number of pages to write per book.") //Fuck making it individual per book.
            .defaultValue(100)
            .min(1)
            .max(100)
            .sliderMax(100) // Max number of pages possible.
            .build()
    );

    private final Setting<Integer> noOfBooks = sgGeneral.add(new IntSetting.Builder()
            .name("no-of-books")
            .description("The number of books to make (or until the file runs out).")
            .defaultValue(1)
            .build()
    );

    private final Setting<Integer> delay = sgGeneral.add(new IntSetting.Builder()
            .name("delay")
            .description("The amount of delay between writing books in milliseconds.")
            .defaultValue(300)
            .min(75)
            .sliderMin(75)
            .sliderMax(600)
            .build()
    );

    //Please don't ask my why they are global. I have no answer for you.
    private static final Random RANDOM = new Random();
    private ListTag pages = new ListTag();
    private int booksLeft;
    private int ticksLeft = 0;
    private boolean firstTime;

    private PrimitiveIterator.OfInt stream;
    private boolean firstChar;
    private int nextChar;
    private final StringBuilder pageSb = new StringBuilder();
    private final StringBuilder lineSb = new StringBuilder();
    private String fileString;

    @Override
    public void onActivate() { //WHY THE FUCK DOES OnActivate NOT CORRECT TO onActivate? Fucking retard.
        //We need to enter the loop somehow. ;)
        booksLeft = noOfBooks.get();
        firstTime = true;
    }

    @Override
    public void onDeactivate() {
        // Reset everything for next time. Don't know if it's needed but we're gonna do it anyway.
        booksLeft = 0;
        pages = new ListTag();
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        // Make sure we aren't in the inventory.
        if(mc.currentScreen instanceof HandledScreen<?>) return;
        // If there are no books left to write we are done.
        if(booksLeft <= 0){
            toggle();
            return;
        }
        //If the player isn't holding a book
        if(mc.player.getMainHandStack().getItem() != Items.WRITABLE_BOOK){
            // Find one
            InvUtils.FindItemResult itemResult = InvUtils.findItemWithCount(Items.WRITABLE_BOOK);
            // If it's in their hotbar then just switch to it (no need to switch back later)
            if (itemResult.slot <= 8 && itemResult.slot != -1) {
                mc.player.inventory.selectedSlot = itemResult.slot;
                ((IClientPlayerInteractionManager) mc.interactionManager).syncSelectedSlot2();
            } else if (itemResult.slot > 8){ //Else if it's in their inventory then swap their current item with the writable book
                InvUtils.clickSlot(InvUtils.invIndexToSlotId(itemResult.slot), 0, SlotActionType.PICKUP);
                InvUtils.clickSlot(InvUtils.invIndexToSlotId(mc.player.inventory.selectedSlot), 0, SlotActionType.PICKUP);
                InvUtils.clickSlot(InvUtils.invIndexToSlotId(itemResult.slot), 0, SlotActionType.PICKUP);
            } else { // Otherwise we are out and we can just wait for more books.
                // I'm always waiting. Watching. Get more books. I dare you. :))))
                return;
            }
        }
        if(ticksLeft <= 0){
            ticksLeft = delay.get();
        }else{
            ticksLeft -= 50;
            return;
        }

            
        if(mode.get() == Mode.PopBob){
                
                String charGenerator = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
                firstChar = true;
                writeBook();
                
      
        
                }
             else {
                if (stream != null) {
                    writeBook();
                } else if (booksLeft > 0) {
                    stream = fileString.chars().iterator();
                    writeBook();
                }
            }
        }
    

    private void writeBook() {
        pages.clear();

        if (firstChar) {
            readChar();
            firstChar = false;
        }

        for (int pageI = 0; pageI < noOfPages.get(); pageI++) {
            pageSb.setLength(0);
            boolean endOfStream = false;

            for (int lineI = 0; lineI < 13; lineI++) {
                lineSb.setLength(0);
                float width = 0;
                boolean endOfStream2 = false;

                while (true) {
                    float charWidth = ((TextHandlerAccessor) mc.textRenderer.getTextHandler()).getWidthRetriever().getWidth(nextChar, Style.EMPTY);
                    if (nextChar == '\n') {
                        if (!readChar()) endOfStream2 = true;
                        break;
                    }
                    if (width + charWidth < LINE_WIDTH) {
                        lineSb.appendCodePoint(nextChar);
                        width += charWidth;

                        if (!readChar()) {
                            endOfStream2 = true;
                            break;
                        }
                    } else break;
                }

                pageSb.append(lineSb).append('\n');
                if (endOfStream2) {
                    endOfStream = true;
                    break;
                }
            }

            pages.add(StringTag.of(pageSb.toString()));
            if (endOfStream) break;
        }

        mc.player.getMainHandStack().putSubTag("pages", pages);
        mc.player.getMainHandStack().putSubTag("author", StringTag.of("squidoodly"));
        mc.player.getMainHandStack().putSubTag("title", StringTag.of(name.get()));
        mc.player.networkHandler.sendPacket(new BookUpdateC2SPacket(mc.player.getMainHandStack(), true, mc.player.inventory.selectedSlot));
        booksLeft--;
    }

    private boolean readChar() {
        if (!stream.hasNext()) {
            stream = null;
            return false;
        }

        nextChar = stream.nextInt();
        return true;
    }
} 