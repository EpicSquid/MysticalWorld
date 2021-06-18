package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.init.ModEntities;
import mysticmods.mysticalworld.init.ModItems;
import mysticmods.mysticalworld.init.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SproutEntity extends AnimalEntity {
  public static final DataParameter<Integer> variant = EntityDataManager.createKey(SproutEntity.class, DataSerializers.VARINT);

  public SproutEntity(EntityType<? extends SproutEntity> type, World world) {
    super(type, world);
    this.experienceValue = 3;
  }

  @Override
  @Nonnull
  public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
    SproutEntity entity = ModEntities.SPROUT.get().create(ageable.world);
    if (entity != null) {
      entity.setVariant(entity.getVariant());
    }

    return entity;
  }

  @Override
  protected float getSoundVolume() {
    return 0.2f;
  }

  @Nullable
  @Override
  protected SoundEvent getAmbientSound() {
    if (rand.nextInt(45) == 0) {
      return ModSounds.SPROUT_AMBIENT.get();
    }

    return null;
  }

  @Override
  protected void registerData() {
    super.registerData();
    this.getDataManager().register(variant, rand.nextInt(4));
  }

  private void setVariant(int variant) {
    this.getDataManager().set(SproutEntity.variant, variant);
  }

  private int getVariant() {
    return this.getDataManager().get(variant);
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new SwimGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.5));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromTag(MWTags.Items.AUBERGINE), false));
    goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0));
    goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0f));
    goalSelector.addGoal(7, new LookRandomlyGoal(this));
  }

  public static AttributeModifierMap.MutableAttribute attributes() {
    return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 8.0d).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2d);
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return new ResourceLocation("mysticalworld:entities/sprout");
  }

  @Override
  public boolean isBreedingItem(ItemStack stack) {
    return stack.getItem() == ModItems.AUBERGINE.get();
  }


  @Override
  public float getStandingEyeHeight(Pose pose, EntitySize size) {
    return isChild() ? getHeight() : 1.3F;
  }

  @Override
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    getDataManager().set(variant, compound.getInt("variant"));
  }

  @Override
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    compound.putInt("variant", getDataManager().get(variant));
  }

  public static int StringToVariant(String color) {
    switch (color) {
      case "tan":
        return 1;
      case "red":
        return 2;
      case "purple":
        return 3;
      case "green":
        return 0;
      default:
        return 0;
    }
  }

  public static String VariantToString(int variant) {
    switch (variant) {
      case 0:
        return "green";
      case 1:
        return "tan";
      case 2:
        return "red";
      case 3:
        return "purple";
      default:
        return "INVALID";
    }
  }
}