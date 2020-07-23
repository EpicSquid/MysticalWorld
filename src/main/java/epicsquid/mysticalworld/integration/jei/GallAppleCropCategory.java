package epicsquid.mysticalworld.integration.jei;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.util.RenderUtil;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import noobanidus.libs.noobutil.client.CycleTimer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GallAppleCropCategory implements IRecipeCategory<GallAppleCropCategory.GallAppleCropRecipe> {
  private static final CycleTimer timer = new CycleTimer(10);
  public static ResourceLocation UID = new ResourceLocation(MysticalWorld.MODID, "gall_apple_crop");

  public static IDrawable background;
  public static IDrawable icon;
  public static BlockState finalState;
  public static List<BlockState> appleStates;
  public static List<BlockState> logStates;

  public GallAppleCropCategory(IGuiHelper helper) {
    icon = helper.createDrawableIngredient(new ItemStack(ModItems.GALL_APPLE.get()));
    background = helper.createDrawable(new ResourceLocation(MysticalWorld.MODID, "textures/gui/jei/gall_apple.png"), 0, 0, 128, 123);
    logStates = BlockTags.OAK_LOGS.getAllElements().stream().map(o -> o.getDefaultState().with(LogBlock.AXIS, Direction.Axis.Y)).collect(Collectors.toList());
    BlockTags.DARK_OAK_LOGS.getAllElements().forEach(o -> logStates.add(o.getDefaultState().with(LogBlock.AXIS, Direction.Axis.Y)));
    appleStates = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      appleStates.add(ModBlocks.GALL_APPLE_CROP.get().getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.EAST).with(BlockStateProperties.AGE_0_3, i));
    }
    finalState = ModBlocks.GALL_APPLE_CROP.get().getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.EAST).with(BlockStateProperties.AGE_0_3, 3);
  }

  @Override
  public ResourceLocation getUid() {
    return UID;
  }

  @Override
  public Class<? extends GallAppleCropRecipe> getRecipeClass() {
    return GallAppleCropRecipe.class;
  }

  @Override
  public String getTitle() {
    return I18n.format("mysticalworld.jei.gall_apple_crop");
  }

  @Override
  public IDrawable getBackground() {
    return background;
  }

  @Override
  public IDrawable getIcon() {
    return icon;
  }

  @Override
  public void setIngredients(GallAppleCropRecipe gallAppleCropRecipe, IIngredients iIngredients) {
    iIngredients.setInput(VanillaTypes.ITEM, new ItemStack(ModItems.WASP_ATTRACTANT.get()));
    iIngredients.setOutput(VanillaTypes.ITEM, new ItemStack(ModItems.GALL_APPLE.get()));
  }

  @Override
  public void setRecipe(IRecipeLayout iRecipeLayout, GallAppleCropRecipe gallAppleCropRecipe, IIngredients iIngredients) {
    IGuiItemStackGroup groups = iRecipeLayout.getItemStacks();
    groups.init(0, true, 1, 20);
    groups.init(1, false, 105, 91);
    groups.set(iIngredients);
  }

  @Override
  public void draw(GallAppleCropRecipe recipe, double mouseX, double mouseY) {
    BlockState log = timer.getCycledItem(logStates);
    BlockState apple = timer.getCycledItem(appleStates);
    timer.onDraw();
    RenderUtil.renderBlock(log, 58, 40, 10, 20f, 0.4f);
    RenderUtil.renderBlock(apple, 65, 45, 10, 20f, 0.4f);
    RenderUtil.renderBlock(log, 58, 110, 10, 20f, 0.4f);
    RenderUtil.renderBlock(finalState, 65, 115, 10, 20f, 0.4f);
  }

  public static GallAppleCropRecipe INSTANCE = new GallAppleCropRecipe();

  public static class GallAppleCropRecipe {
  }
}