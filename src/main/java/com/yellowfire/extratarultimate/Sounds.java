package com.yellowfire.extratarultimate;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class Sounds {
    public static SoundEvent TELDER_SAMOVAR_USE = SoundEvent.of(ExtratarUltimate.id("telder_samovar_use"));
    public static SoundEvent YELLOW_PEACH_RECORD = SoundEvent.of(ExtratarUltimate.id("yellow_peach_record"));
    public static SoundEvent TRIANGLE_RECORD = SoundEvent.of(ExtratarUltimate.id("triangle_record"));

    public static void register() {
        registerSound(TELDER_SAMOVAR_USE);
        registerSound(YELLOW_PEACH_RECORD);
        registerSound(TRIANGLE_RECORD);
    }

    public static void registerSound(SoundEvent soundEvent) {
        Registry.register(Registries.SOUND_EVENT, soundEvent.getId(), soundEvent);
    }
}
