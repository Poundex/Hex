package hex.ui.jfx.layout

import com.jfoenix.controls.JFXDecorator
import groovyx.javafx.SceneGraphBuilder
import javafx.event.ActionEvent
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.control.TextInputControl
import javafx.scene.layout.VBox
import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Autowired

import javax.annotation.PostConstruct

/**
 * Created by poundex on 16/11/16.
 */
class MainStage
{
	@Autowired
	SceneGraphBuilder sceneGraphBuilder

	@Autowired
	Stage stage

	@Autowired
	PageManager pageManager

	@PostConstruct
	void init()
	{
		JFXDecorator decorator = new JFXDecorator(stage, buildMainLayout())

		Scene scene = new Scene(decorator, 350, 550)
		scene.stylesheets.add(getClass().getResource("/resources/css/jfoenix-fonts.css").toExternalForm())
		scene.stylesheets.add(getClass().getResource("/resources/css/jfoenix-design.css").toExternalForm())
		scene.stylesheets.add(getClass().getResource("/hex.css").toExternalForm())
		stage.scene = scene

		stage.show()
	}

	Node buildMainLayout()
	{
		VBox v
		sceneGraphBuilder.borderPane {
			center {
				scrollPane(fitToWidth: true, fitToHeight: true) {
					anchorPane {
						v = vbox(fillWidth: true, bottomAnchor: 0, rightAnchor: 0, leftAnchor: 0, spacing: 18)
						pageManager.init(v.children)
					}
				}
			}

			bottom {
				TextInputControl input
				borderPane {
					input = jfxTextField(onAction: { ActionEvent e -> submitText(e.source) })

					right {
						button(onAction: { submitText(input) })
					}
				}
			}
		}
	}

	private void submitText(TextInputControl textSource)
	{
		pageManager.currentPage.get().submit(textSource.text)
		textSource.text = ''
	}
}
