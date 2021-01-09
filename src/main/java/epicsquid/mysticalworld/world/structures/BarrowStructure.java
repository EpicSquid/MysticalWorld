package epicsquid.mysticalworld.world.structures;

import com.mojang.serialization.Codec;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.*;
import noobanidus.libs.noobutil.world.gen.structure.SimpleStructure;

import javax.annotation.Nullable;
import java.util.Random;

public class BarrowStructure extends SimpleStructure {
  public BarrowStructure(Codec<NoFeatureConfig> codec) {
    super(codec);
  }

  @Override
  public IStartFactory<NoFeatureConfig> getStartFactory() {
    return BarrowStructure.Start::new;
  }

  @Override
  protected boolean func_230363_a_(ChunkGenerator chunkGenerator, BiomeProvider biomeProvider, long seed, SharedSeedRandom sharedSeed, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig config) {
    int startX = chunkX << 4;
    int startZ = chunkZ << 4;
    int min = Integer.MAX_VALUE;
    int max = 0;
    for (int x = 0; x < 16; x++) {
      for (int z = 0; z < 16; z++) {
        int height = chunkGenerator.getHeight(startX + x, startZ + z, Heightmap.Type.WORLD_SURFACE_WG);
        if (height < min) {
          min = height;
        }
        if (height > max) {
          max = height;
        }
        if (max - min > 3) {
          return false;
        }
      }
    }

    return true;
  }

  public static class Start extends SimpleStart {
    public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
      super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
    }

    public static ResourceLocation POOL = new ResourceLocation(MysticalWorld.MODID, "barrow_pool/start_pool");

    @Override
    protected ResourceLocation getPoolLocation() {
      return POOL;
    }

    @Override
    protected int getPoolSize() {
      return 50;
    }

    @Override
    protected void modifyStructure(DynamicRegistries dynamicRegistries, ChunkGenerator chunkGenerator, TemplateManager templateManager, Biome biome, NoFeatureConfig noFeatureConfig, BlockPos blockPos) {
    }
  }
}