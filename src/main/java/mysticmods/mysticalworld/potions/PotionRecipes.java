package mysticmods.mysticalworld.potions;

import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.init.ModBlocks;
import mysticmods.mysticalworld.init.ModEffects;
import mysticmods.mysticalworld.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import noobanidus.libs.noobutil.types.LazyIngredient;

public class PotionRecipes {
  public static void registerRecipes() {

    BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.MUNDANE)), new LazyIngredient(() -> Ingredient.fromItems(ModItems.ANTLERS.get())), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.STRENGTH));
    BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.AWKWARD)), new LazyIngredient(() -> Ingredient.fromTag(MWTags.Items.AMETHYST_GEM)), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), ModEffects.SERENDIPITY_POTION.get()));
    BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.MUNDANE)), new LazyIngredient(() -> Ingredient.fromItems(ModBlocks.STONEPETAL.get())), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.LEAPING));
  }
}