package bewis09.bc_notes_and_tasks.elements

import bewis09.bc_notes_and_tasks.BewisclientNotesAndTasks
import bewis09.bc_notes_and_tasks.HoveredInterface
import bewis09.bc_notes_and_tasks.mixin.EditBoxWidgetMixin
import bewis09.bewisclient.drawable.MainOptionsElement
import bewis09.bewisclient.screen.MainOptionsScreen
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.EditBoxWidget
import net.minecraft.text.Text
import net.minecraft.util.Identifier

open class NotesElement: MainOptionsElement("","", Identifier("")) {

    private var editBoxWidget: EditBoxWidget? = null

    override fun render(context: DrawContext, x: Int, y: Int, width: Int, mouseX: Int, mouseY: Int, alphaModifier: Long): Int {
        if(editBoxWidget==null)
            editBoxWidget = EditBoxWidget(MinecraftClient.getInstance().textRenderer,0,0,0,100,Text.translatable("bewisclient.${BewisclientNotesAndTasks.MOD_ID}.notes.placeholder"), Text.empty())

        val height = 200

        (editBoxWidget as HoveredInterface).`bewisclientNotesAndTasks$setHovered`((mouseX >= editBoxWidget!!.x && mouseY >= editBoxWidget!!.y && mouseX < editBoxWidget!!.x + editBoxWidget!!.width) && mouseY < editBoxWidget!!.y + editBoxWidget!!.height)

        editBoxWidget!!.x = x
        editBoxWidget!!.y = 4
        editBoxWidget!!.width = width
        editBoxWidget!!.height = height

        context.fill(x,y,x+width,y+height, alphaModifier.toInt())
        context.drawBorder(x,y,width,height, (alphaModifier+if(editBoxWidget?.isFocused == true) (0xFFFFFF) else (0x808080)).toInt())

        var text = editBoxWidget!!.text

        val cursor = (editBoxWidget!! as EditBoxWidgetMixin).editBox.cursor

        if (cursor==text.length) {
            if (System.currentTimeMillis() % 1000 < 500) {
                text += "_"
            }
        } else {
            var cursorLine = 0
            var cts = 0
            for (t in text.split("\n")) {
                cts+=t.length
                if(cursor>cts) {
                    cursorLine++
                }
            }
        }

        val descriptionLines = MinecraftClient.getInstance().textRenderer.wrapLines((Text.literal(text)),width-12)

        descriptionLines.iterator().withIndex().forEach { (index, line) ->
            context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, line, x + 6, y+6 + 10 * index, (alphaModifier + 0xFFFFFF).toInt())
        }

        return height
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int, screen: MainOptionsScreen) {
        editBoxWidget?.isFocused = editBoxWidget?.isHovered == true
        editBoxWidget?.mouseClicked(mouseX, mouseY, button)
    }

    override fun onDrag(mouseX: Double, mouseY: Double, deltaX: Double, deltaY: Double, button: Int) {
        editBoxWidget?.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)
    }

    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int) {
        editBoxWidget?.keyPressed(keyCode, scanCode, modifiers)
        MinecraftClient.getInstance().currentScreen?.focused=null
    }

    override fun charTyped(chr: Char, modifiers: Int) {
        editBoxWidget?.charTyped(chr, modifiers)
    }
}