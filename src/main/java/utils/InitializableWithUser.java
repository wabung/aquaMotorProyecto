package utils;

import database.model.User;

/**
 * Interface for controllers that require a User to be passed after loading.
 * Used by UtilsProject.openScreen to inject user data automatically.
 */
public interface InitializableWithUser {
    void initData(User user);
}
