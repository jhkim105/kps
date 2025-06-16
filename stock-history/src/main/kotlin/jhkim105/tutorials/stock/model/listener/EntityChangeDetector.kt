package jhkim105.tutorials.stock.model.listener

object EntityChangeDetector {

    fun detectChangedFields(
        propertyNames: Array<String>,
        oldState: Array<Any?>?,
        newState: Array<Any?>?
    ): List<String> {
        if (oldState == null || newState == null) return emptyList()

        val changed = mutableListOf<String>()
        for (i in propertyNames.indices) {
            val oldVal = oldState[i]
            val newVal = newState[i]
            if (oldVal != newVal) {
                changed.add(propertyNames[i])
            }
        }
        return changed
    }
}