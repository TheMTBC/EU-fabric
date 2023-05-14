package com.yellowfire.extratarultimate;

import com.yellowfire.extratarultimate.blocks.Blocks;
import com.yellowfire.extratarultimate.effects.Effects;
import com.yellowfire.extratarultimate.entities.Entities;
import com.yellowfire.extratarultimate.items.Items;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MiningToolItem;
import net.minecraft.server.command.ParticleCommand;
import net.minecraft.util.Identifier;

import java.util.logging.Logger;

public class ExtratarUltimate implements ModInitializer {
    public static ItemGroup ITEMS = FabricItemGroup.builder(id("items"))
            .icon(() -> Items.EARTH_CRYSTAL.getDefaultStack())
            .build();
    public static ItemGroup BLOCKS = FabricItemGroup.builder(id("blocks"))
            .icon(() -> Blocks.MECHANICAL_STRUCTURE.asItem().getDefaultStack())
            .build();
    public static ExtratarUltimate INSTANCE;
    private final Logger logger = Logger.getLogger("extratar_ultimate");

    public Logger getLogger() {
        return logger;
    }

    @Override
    public void onInitialize() {
        INSTANCE = this;

        Blocks.register();
        ItemGroupEvents.modifyEntriesEvent(BLOCKS).register(entries -> {
            for (var item : Blocks.items) {
                entries.add(item);
            }
        });
        Sounds.register();
        Entities.register();
        Effects.register();
        Items.register();
        ItemGroupEvents.modifyEntriesEvent(ITEMS).register(entries -> {
            for (var item : Items.items) {
                entries.add(item);
            }
        });
    }

    public static Identifier id(String path) {
        return new Identifier("extratar_ultimate", path);
    }
}
