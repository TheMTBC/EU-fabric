package com.yellowfire.extratarultimate;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class Tags {
    public static TagKey<Block> SOAKERS = TagKey.of(RegistryKeys.BLOCK, ExtratarUltimate.id("soakers"));
}
