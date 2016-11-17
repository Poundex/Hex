package hex.ui.jfx.layout

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.Node
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

/**
 * Created by poundex on 16/11/16.
 */
class PageManager implements ApplicationContextAware
{
	ApplicationContext applicationContext
	final ObjectProperty<Page> currentPage = new SimpleObjectProperty<>()

	private javafx.collections.ObservableList<Node> pages

	void init(javafx.collections.ObservableList<Node> children)
	{
		this.pages = children
		addPage()
	}

	void addPage()
	{
		applicationContext.getBean(Page).with { Page p ->
			pages << p
			currentPage.set(p)
		}
	}
}
