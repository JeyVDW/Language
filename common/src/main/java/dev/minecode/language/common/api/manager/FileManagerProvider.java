package dev.minecode.language.common.api.manager;

import dev.minecode.core.api.CoreAPI;
import dev.minecode.core.api.object.FileObject;
import dev.minecode.language.api.LanguageAPI;
import dev.minecode.language.api.manager.FileManager;

public class FileManagerProvider implements FileManager {
    private FileObject config;

    public FileManagerProvider() {
        makeInstances();
    }

    private void makeInstances() {
        config = CoreAPI.getInstance().getFileManager().getFileObject(LanguageAPI.getInstance().getThisCorePlugin(), "config.yml");
    }

    @Override
    public FileObject getConfig() {
        return config;
    }
}
