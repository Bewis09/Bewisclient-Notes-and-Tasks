package bewis09.bc_notes_and_tasks

import bewis09.bc_notes_and_tasks.elements.NotesAndTasksOptionsElement
import bewis09.bc_notes_and_tasks.elements.NotesElement
import bewis09.bewisclient.api.KotlinAPIEntryPoint
import bewis09.bewisclient.drawable.InfoElement
import bewis09.bewisclient.drawable.MainOptionsElement
import net.fabricmc.api.ClientModInitializer
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object BewisclientNotesAndTasks : ClientModInitializer {

	const val MOD_ID = "bc-notes-and-tasks"

	private val notes_tasks: (String)->ArrayList<MainOptionsElement> = {
		arrayListOf(
			NotesAndTasksOptionsElement("notes") {
				arrayListOf(
						NotesElement()
				)
			},
			NotesAndTasksOptionsElement("tasks") {
				arrayListOf(

				)
			},
			NotesAndTasksOptionsElement("timers") {
				arrayListOf(

				)
			},
		)
	}

	private val elements: ArrayList<MainOptionsElement> = arrayListOf(
			MainOptionsElement("notes_and_tasks.global","notes_and_tasks.description.global", notes_tasks("global"), Identifier(MOD_ID,"textures/global.png")),
			MainOptionsElement("notes_and_tasks.local","notes_and_tasks.description.local", notes_tasks("local"), Identifier(MOD_ID,"textures/local.png")),
			InfoElement("notes_and_tasks.local_global_info")
	)

	override fun onInitializeClient() {
		KotlinAPIEntryPoint.addMainOptionsElement(MainOptionsElement("$MOD_ID.element","$MOD_ID.description.element", elements, Identifier(MOD_ID,"element")))
	}

	fun getTranslationText(key: String): MutableText {
		return Text.translatable("bewisclient.$key")
	}
}