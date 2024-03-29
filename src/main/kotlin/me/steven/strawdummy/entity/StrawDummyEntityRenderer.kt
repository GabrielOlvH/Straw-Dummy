package me.steven.strawdummy.entity

import me.steven.strawdummy.identifier
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.render.entity.feature.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayers
import net.minecraft.client.render.entity.model.PlayerEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class StrawDummyEntityRenderer(ctx: EntityRendererFactory.Context, model: PlayerEntityModel<StrawDummyEntity>)
    : LivingEntityRenderer<StrawDummyEntity, PlayerEntityModel<StrawDummyEntity>>(ctx, model, 0.5f) {

    init {
        addFeature(
            ArmorFeatureRenderer(
                this,
                BipedEntityModel(ctx.getPart(EntityModelLayers.PLAYER_INNER_ARMOR)),
                BipedEntityModel(ctx.getPart(EntityModelLayers.PLAYER_OUTER_ARMOR)),
                ctx.modelManager
            )
        )
        addFeature(HeldItemFeatureRenderer(this, ctx.heldItemRenderer))
        addFeature(StuckArrowsFeatureRenderer(ctx, this))
        addFeature(HeadFeatureRenderer(this, ctx.modelLoader, ctx.heldItemRenderer))
        addFeature(ElytraFeatureRenderer(this, ctx.modelLoader))
        addFeature(TridentRiptideFeatureRenderer(this, ctx.modelLoader))
        addFeature(StuckStingersFeatureRenderer(this))
    }

    override fun renderLabelIfPresent(entity: StrawDummyEntity?, text: Text?, matrices: MatrixStack?, vertexConsumers: VertexConsumerProvider?, light: Int) {}

    override fun getTexture(entity: StrawDummyEntity): Identifier {
        return TEXTURE_IDENTIFIERS[entity.id % 9]
    }

    companion object {
        val TEXTURE_IDENTIFIERS = (1..9).map { identifier("textures/entity/dummy$it.png") }
    }
}