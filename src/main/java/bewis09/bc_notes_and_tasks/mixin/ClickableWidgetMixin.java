package bewis09.bc_notes_and_tasks.mixin;

import bewis09.bc_notes_and_tasks.HoveredInterface;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ClickableWidget.class)
public class ClickableWidgetMixin implements HoveredInterface {
    @Unique
    @Mutable
    @Final
    public boolean hovered;

    @Override
    public void bewisclientNotesAndTasks$setHovered(boolean hovered) {
        this.hovered = hovered;
    }
}
