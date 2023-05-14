package com.yellowfire.extratarultimate.items;

import com.yellowfire.extratarultimate.ExtratarUltimate;
import com.yellowfire.extratarultimate.Sounds;
import com.yellowfire.extratarultimate.effects.Effects;
import com.yellowfire.extratarultimate.entities.Entities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;

public class Items {
    public static ArrayList<Item> items = new ArrayList<>();

    public static Item YELLOW_PEACH = new Item(new FabricItemSettings().food(new FoodComponent.Builder()
            .hunger(5)
            .snack()
            .build())
    );

    public static Item TRIANGLE = new Item(new FabricItemSettings().food(new FoodComponent.Builder()
            .hunger(7)
            .snack()
            .build())
    );

    public static Item GOLDEN_TRIANGLE = new Item(new FabricItemSettings().food(new FoodComponent.Builder()
                    .hunger(7)
                    .snack()
                    .alwaysEdible()
                    .statusEffect(new StatusEffectInstance(
                            StatusEffects.ABSORPTION,
                            60 * 20,
                            1
                    ), 1)
                    .statusEffect(new StatusEffectInstance(
                            StatusEffects.REGENERATION,
                            10 * 20
                    ), 1)
                    .build())
    );

    public static Item ENCHANTED_GOLDEN_TRIANGLE = new EnchantedGoldenAppleItem(new FabricItemSettings().food(new FoodComponent.Builder()
                    .hunger(7)
                    .snack()
                    .alwaysEdible()
                    .statusEffect(new StatusEffectInstance(
                            StatusEffects.ABSORPTION,
                            90 * 20,
                            1
                    ), 1)
                    .statusEffect(new StatusEffectInstance(
                            StatusEffects.RESISTANCE,
                            3 * 60 * 20
                    ), 1)
                    .statusEffect(new StatusEffectInstance(
                            StatusEffects.REGENERATION,
                            20 * 20,
                            1
                    ), 1)
                    .statusEffect(new StatusEffectInstance(
                            StatusEffects.FIRE_RESISTANCE,
                            3 * 60 * 20
                    ), 1)
                    .build())
    );

    public static Item CHUK_CHUK = new Item(new FabricItemSettings().food(new FoodComponent.Builder()
                    .hunger(4)
                    .snack()
                    .build())
    );

    public static Item BOILED_WATER_BOTTLE = new BottleFoodItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(4)
                    .snack()
                    .build())
            .maxCount(16)
    );

    public static Item TEA_WITH_MILK_BOTTLE = new BottleFoodItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(4)
                    .snack()
                    .build())
            .maxCount(16)
    );

    public static Item ASTRA_SYRUP_BOTTLE = new BottleFoodItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(4)
                    .snack()
                    .statusEffect(new StatusEffectInstance(Effects.INVULNERABILITY, 30 * 20), 1)
                    .build())
            .maxCount(16),
            true
    );

    public static Item PANACEA = new Panacea(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(1)
                    .snack()
                    .alwaysEdible()
                    .build())
    );

    public static Item LUMINOUS_STEW = new StewItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(3)
                    .snack()
                    .statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 5*20), 1)
                    .build())
            .maxCount(1)
    );

    public static Item RAW_TELDER_STEEL = new Item(new FabricItemSettings());
    public static Item EARTH_CRYSTAL = new Item(new FabricItemSettings());
    public static Item NETHER_CRYSTAL = new Item(new FabricItemSettings());
    public static Item END_CRYSTAL = new Item(new FabricItemSettings());
    public static Item TELDER_PULVERIS = new TelderPulveris(new FabricItemSettings());
    public static Item TELDER_STEEL_INGOT = new Item(new FabricItemSettings());
    public static Item TELDER_STEEL_NUGGET = new Item(new FabricItemSettings());
    public static Item TELDER_STEEL_GEAR = new Item(new FabricItemSettings());
    public static Item TELDER_PICKAXE = new PickaxeItem(TelderToolMaterial.TELDER, 1, -2.8F, new FabricItemSettings());
    public static Item TELDER_AXE = new AxeItem(TelderToolMaterial.TELDER, 6, -3.2F, new FabricItemSettings());
    public static Item TELDER_SWORD = new SwordItem(ToolMaterials.NETHERITE, 3, -1.6f, new FabricItemSettings());
    public static Item TELDER_SHOVEL = new ShovelItem(TelderToolMaterial.TELDER, 1.5F, -3.0F, new FabricItemSettings());
    public static Item TELDER_HOE = new HoeItem(ToolMaterials.NETHERITE, 1, 4, new FabricItemSettings());
    public static Item WOODEN_PICK = new PickaxeItem(ToolMaterials.WOOD, 1, -2.8F, new FabricItemSettings());
    public static Item STONE_PICK = new PickaxeItem(ToolMaterials.STONE, 1, -2.8F, new FabricItemSettings());
    public static Item IRON_PICK = new PickaxeItem(ToolMaterials.IRON, 1, -2.8F, new FabricItemSettings());
    public static Item GOLD_PICK = new PickaxeItem(ToolMaterials.GOLD, 1, -2.8F, new FabricItemSettings());
    public static Item DIAMOND_PICK = new PickaxeItem(ToolMaterials.DIAMOND, 1, -2.8F, new FabricItemSettings());
    public static Item NETHERITE_PICK = new PickaxeItem(ToolMaterials.NETHERITE, 1, -2.8F, new FabricItemSettings());
    public static Item TELDER_PICK = new PickaxeItem(ToolMaterials.NETHERITE, 1, -2.8F, new FabricItemSettings());
    public static Item TELDER_STEEL_HELMET = new HelmetTypeA(TelderArmorMaterial.TELDER, new FabricItemSettings());
    public static Item TELDER_STEEL_CHESTPLATE = new ArmorItem(TelderArmorMaterial.TELDER, EquipmentSlot.CHEST, new FabricItemSettings());
    public static Item TELDER_STEEL_LEGGINGS = new ArmorItem(TelderArmorMaterial.TELDER, EquipmentSlot.LEGS, new FabricItemSettings());
    public static Item TELDER_STEEL_BOOTS = new ArmorItem(TelderArmorMaterial.TELDER, EquipmentSlot.FEET, new FabricItemSettings());
    public static Item TELDER_CLOTH = new ClothItem(new FabricItemSettings());
    public static Item TELDER_MAGIC_CLOTH = new MagicClothItem(new FabricItemSettings());
    public static Item TELDER_FLEECE = new Item(new FabricItemSettings());
    public static Item ASTRA_PETAL = new Item(new FabricItemSettings());
    public static Item ANCHOR_LINKER = new AnchorLinker(new FabricItemSettings()
            .maxCount(1)
            .maxDamage(32));
    public static Item YELLOW_PEACH_DISC = new MusicDiscItem(15, Sounds.YELLOW_PEACH_RECORD, new FabricItemSettings().maxCount(1), 3 * 60 + 15);
    public static Item TRIANGLE_DISC = new MusicDiscItem(15, Sounds.TRIANGLE_RECORD, new FabricItemSettings().maxCount(1), 2 * 60 + 54);
    public static Item FENNEC_SPAWN_EGG = new SpawnEggItem(Entities.FENNEC, 0x8a5741, 0xdbd0c2, new FabricItemSettings());

    public static void register() {
        registerItem(YELLOW_PEACH, "yellow_peach");
        registerItem(TRIANGLE, "triangle");
        registerItem(GOLDEN_TRIANGLE, "golden_triangle");
        registerItem(ENCHANTED_GOLDEN_TRIANGLE, "enchanted_golden_triangle");
        registerItem(CHUK_CHUK, "chuk_chuk");
        registerItem(BOILED_WATER_BOTTLE, "boiled_water_bottle");
        registerItem(TEA_WITH_MILK_BOTTLE, "tea_with_milk_bottle");
        registerItem(ASTRA_SYRUP_BOTTLE, "astra_syrup_bottle");
        registerItem(PANACEA, "panacea");
        registerItem(LUMINOUS_STEW, "luminous_stew");
        registerItem(RAW_TELDER_STEEL, "raw_telder_steel");
        registerItem(EARTH_CRYSTAL, "earth_crystal");
        registerItem(NETHER_CRYSTAL, "nether_crystal");
        registerItem(END_CRYSTAL, "end_crystal");
        registerItem(TELDER_PULVERIS, "telder_pulveris");
        registerItem(TELDER_STEEL_INGOT, "telder_steel_ingot");
        registerItem(TELDER_STEEL_NUGGET, "telder_steel_nugget");
        registerItem(TELDER_STEEL_GEAR, "telder_steel_gear");
        registerItem(TELDER_PICKAXE, "telder_pickaxe");
        registerItem(TELDER_AXE, "telder_axe");
        registerItem(TELDER_SWORD, "telder_sword");
        registerItem(TELDER_SHOVEL, "telder_shovel");
        registerItem(TELDER_HOE, "telder_hoe");
        registerItem(WOODEN_PICK, "wooden_pick");
        registerItem(STONE_PICK, "stone_pick");
        registerItem(IRON_PICK, "iron_pick");
        registerItem(GOLD_PICK, "gold_pick");
        registerItem(DIAMOND_PICK, "diamond_pick");
        registerItem(NETHERITE_PICK, "netherite_pick");
        registerItem(TELDER_PICK, "telder_pick");
        registerItem(TELDER_STEEL_HELMET, "telder_steel_helmet");
        registerItem(TELDER_STEEL_CHESTPLATE, "telder_steel_chestplate");
        registerItem(TELDER_STEEL_LEGGINGS, "telder_steel_leggings");
        registerItem(TELDER_STEEL_BOOTS, "telder_steel_boots");
        registerItem(TELDER_CLOTH, "telder_cloth");
        registerItem(TELDER_MAGIC_CLOTH, "telder_magic_cloth");
        registerItem(TELDER_FLEECE, "telder_fleece");
        registerItem(ASTRA_PETAL, "astra_petal");
        registerItem(ANCHOR_LINKER, "anchor_linker");
        registerItem(YELLOW_PEACH_DISC, "yellow_peach_disc");
        registerItem(TRIANGLE_DISC, "triangle_disc");
        registerItem(FENNEC_SPAWN_EGG, "fennec_spawn_egg");
    }

    private static void registerItem(Item item, String path) {
        items.add(item);
        Registry.register(Registries.ITEM, ExtratarUltimate.id(path), item);
    }

    public static Vec3d getItemVelocity(World world) {
        return new Vec3d(
                world.random.nextTriangular(0.0, 0.11485),
                world.random.nextTriangular(0.2, 0.11485),
                world.random.nextTriangular(0.0, 0.11485)
        );
    }
}
