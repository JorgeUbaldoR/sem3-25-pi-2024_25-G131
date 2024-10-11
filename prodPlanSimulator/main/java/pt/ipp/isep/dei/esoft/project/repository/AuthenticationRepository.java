package pt.ipp.isep.dei.esoft.project.repository;

import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

/**
 * Repository class that handles user authentication operations.
 * This class interacts with the AuthFacade to perform login, logout,
 * and manage user sessions and roles.
 */
public class AuthenticationRepository {
    private final AuthFacade authenticationFacade;

    /**
     * Constructs an AuthenticationRepository instance.
     * Initializes the AuthFacade used for handling authentication operations.
     */
    public AuthenticationRepository() {
        authenticationFacade = new AuthFacade();
    }

    /**
     * Attempts to log in a user with the provided email and password.
     *
     * @param email the email of the user attempting to log in
     * @param pwd   the password of the user attempting to log in
     * @return true if the login is successful, false otherwise
     */
    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }


    /**
     * Logs out the currently logged-in user.
     */
    public void doLogout() {
        authenticationFacade.doLogout();
    }

    /**
     * Retrieves the current user session.
     *
     * @return the current UserSession, or null if no user is logged in
     */
    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    /**
     * Adds a role to a user with the specified ID and description.
     *
     * @param id          the ID of the user to whom the role will be added
     * @param description the description of the role
     * @return true if the role was added successfully, false otherwise
     */
    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    /**
     * Adds a new user with the specified role.
     *
     * @param name     the name of the new user
     * @param email    the email of the new user
     * @param pwd      the password of the new user
     * @param roleId   the ID of the role to assign to the new user
     * @return true if the user was added successfully, false otherwise
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
    }
}