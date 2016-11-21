package hex.ui.jfx.layout

import com.jfoenix.controls.JFXDecorator
import groovyx.javafx.SceneGraphBuilder
import javafx.event.ActionEvent
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.control.TextInputControl
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

/**
 * Created by poundex on 16/11/16.
 */
@Component
class MainStage
{
	@Autowired
	SceneGraphBuilder sceneGraphBuilder

	@Autowired
	Stage stage

	@Autowired
	PageManager pageManager

	private TextInputControl input

	@PostConstruct
	void init()
	{
		stage.title = 'Hex'
		stage.icons << new Image(getClass().getResource('/cheese_small.png').newInputStream(), 64, 64, true, true)

		JFXDecorator decorator = new JFXDecorator(stage, buildMainLayout(), false, false, true)

		Scene scene = new Scene(decorator, 350, 550)
		scene.stylesheets.add(getClass().getResource("/resources/css/jfoenix-fonts.css").toExternalForm())
		scene.stylesheets.add(getClass().getResource("/resources/css/jfoenix-design.css").toExternalForm())
		scene.stylesheets.add(getClass().getResource("/hex.css").toExternalForm())
		stage.scene = scene

		input.requestFocus()
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
