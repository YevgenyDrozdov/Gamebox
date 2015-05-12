package com.epam.jmp.gamebox.loader;

import com.epam.jmp.gamebox.GameDescriptor;
import com.epam.jmp.gamebox.annotations.Controller;
import com.epam.jmp.gamebox.deploy.meta.GameDescriptorImpl;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import java.util.Set;

public class AnnotationGameDescriptorLoadAssistant implements GameDescriptorLoadAssistant {

    private ClassLoader classLoader;

    public AnnotationGameDescriptorLoadAssistant(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public GameDescriptor loadDescriptorAttributes(GameDescriptor descriptor) {

        Reflections reflections = new Reflections(classLoader, "");
        Set<Class<?>> controllerClasses = reflections.getTypesAnnotatedWith(Controller.class);

        if (controllerClasses.size() > 1) {
            throw new RuntimeException("More than one controller found.");
        }

        String gameName = null;
        String gameVersion = null;
        String controllerClassName = null;
        String miniaturePath = null;

        for (Class controllerClass : controllerClasses) {
            controllerClassName = controllerClass.getName();
            Controller annotation = (Controller)controllerClass.getAnnotation(Controller.class);

            if (StringUtils.isNotBlank(annotation.gameName())) {
                gameName = annotation.gameName();
            }

            if (StringUtils.isNotBlank(annotation.gameVersion())) {
                gameVersion = annotation.gameVersion();
            }

            if (StringUtils.isNotBlank(annotation.miniaturePath())) {
                miniaturePath = annotation.miniaturePath();
            }
        }

        GameDescriptorImpl gameDescriptor = new GameDescriptorImpl(descriptor);
        gameDescriptor.setControllerClass(controllerClassName);
        gameDescriptor.setGameName(gameName);
        gameDescriptor.setGameVersion(gameVersion);
        gameDescriptor.setMiniaturePath(miniaturePath);

        return gameDescriptor;
    }

}
