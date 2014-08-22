package org.jacpfx.gui.perspective;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.lifecycle.OnShow;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.annotations.perspective.Perspective;
import org.jacpfx.api.message.Message;
import org.jacpfx.dto.FragmentNavigation;
import org.jacpfx.gui.configuration.BaseConfig;
import org.jacpfx.gui.fragment.*;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.componentLayout.PerspectiveLayout;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.perspective.FXPerspective;

import java.util.ResourceBundle;

/**
 * Created by Andy Moncsek on 13.12.13.
 * A JacpFX perspective.
 *
 * @author <a href="mailto:amo.ahcp@gmail.com"> Andy Moncsek</a>
 */
@Perspective(id = BaseConfig.DRAWING_PERSPECTIVE, name = "drawingPerspective",
        components = {
                BaseConfig.CANVAS_COMPONENT,
                BaseConfig.COLOR_PICKER_COMPONENT,
                BaseConfig.WEBSOCKET_COMPONENT,
                BaseConfig.VERTX_COMPONENT,
                BaseConfig.MQTT_COMPONENT},
        viewLocation = "/fxml/DrawingPerspective.fxml",
        resourceBundleLocation = "bundles.languageBundle",
        localeID = "en_US")
public class DrawingPerspective implements FXPerspective {

    @Resource
    private Context context;

    @FXML
    private HBox top;
    @FXML
    private HBox bottom;

    @Override
    public void handlePerspective(Message<Event, Object> message, PerspectiveLayout perspectiveLayout) {
        if (message.isMessageBodyTypeOf(FragmentNavigation.class)) {
            // coordinate between fragments
            FragmentNavigation navigation = message.getTypedMessageBody(FragmentNavigation.class);
            switch (navigation) {
                case CONNECT_VERTX:
                    ManagedFragmentHandler<VertxConnectFragment> handler = context.getManagedFragmentHandler(VertxConnectFragment.class);
                    context.showModalDialog(handler.getFragmentNode());
                    break;
                case CREATE_VERTX:
                    ManagedFragmentHandler<VertxCreateFragment> create = context.getManagedFragmentHandler(VertxCreateFragment.class);
                    create.getController().init();
                    context.showModalDialog(create.getFragmentNode());
                    break;
                case BACK_VERTX:
                    ManagedFragmentHandler<ServerConfigFragment> handlerMain = context.getManagedFragmentHandler(ServerConfigFragment.class);
                    context.showModalDialog(handlerMain.getFragmentNode());
                    break;
                case SHOW_VERTX:
                    ManagedFragmentHandler<ServerConfigFragment> handlerMainShow = context.getManagedFragmentHandler(ServerConfigFragment.class);
                    context.showModalDialog(handlerMainShow.getFragmentNode());
                    break;
                case SHOW_MQTT:
                    ManagedFragmentHandler<MQTTConnectFragment> handlerMQTTMainShow = context.getManagedFragmentHandler(MQTTConnectFragment.class);
                    handlerMQTTMainShow.getController().init();
                    context.showModalDialog(handlerMQTTMainShow.getFragmentNode());
                    break;
                default:
                    context.hideModalDialog();

            }
        }


    }

    @OnShow
    /**
     * @OnShow will be executed when perspective is switched to foreground
     * @param layout, The layout object gives you access to menu bar and tool bar
     * @param resourceBundle, The resource bundle defined in Perspective annotation
     */
    public void onShow(final FXComponentLayout layout,
                       final ResourceBundle resourceBundle) {

    }

    @PostConstruct
    /**
     * @PostConstruct annotated method will be executed when component is activated.
     * @param perspectiveLayout, allows you to access the JavaFX root node of the current perspective and to register target areas
     * @param layout, The layout object gives you access to menu bar and tool bar
     * @param resourceBundle, The resource bundle defined in Perspective annotation
     */
    public void onStartPerspective(final PerspectiveLayout perspectiveLayout,
                                   final FXComponentLayout layout,
                                   final ResourceBundle resourceBundle) {
        perspectiveLayout.registerTargetLayoutComponent("vMain", bottom);
        perspectiveLayout.registerTargetLayoutComponent("top", top);
        startConnectDialog();

    }

    @PreDestroy
    /**
     * @PreDestroy annotated method will be executed when component is deactivated.
     * @param layout, The layout object gives you access to menu bar and tool bar
     */
    public void onTearDownPerspective(final FXComponentLayout layout) {
        // remove toolbars and menu entries when close perspective

    }

    private void startConnectDialog() {
        ManagedFragmentHandler<SelectConfigFragment> handlerMain = context.getManagedFragmentHandler(SelectConfigFragment.class);
        context.showModalDialog(handlerMain.getFragmentNode());
    }


}
