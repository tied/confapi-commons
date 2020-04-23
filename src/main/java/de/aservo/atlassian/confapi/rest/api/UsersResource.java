package de.aservo.atlassian.confapi.rest.api;

import de.aservo.atlassian.confapi.constants.ConfAPI;
import de.aservo.atlassian.confapi.model.ErrorCollection;
import de.aservo.atlassian.confapi.model.UserBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface UsersResource {

    /**
     * Get a user.
     *
     * @param userName the user name
     * @return the user
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            tags = { ConfAPI.USERS },
            summary = "Retrieves user information",
            description = "Upon successful request, returns a `UserBean` object containing user details",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserBean.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorCollection.class)))
            }
    )
    public Response getUser(
            @NotNull @QueryParam("userName") final String userName);

    /**
     * Update a user.
     *
     * @param userName the user name of the user to update
     * @param userBean the user bean to update
     * @return the response
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            tags = { ConfAPI.USERS },
            summary = "Updates user details",
            description = "Upon successful request, returns the updated `UserBean` object (user name cannot be updated)",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserBean.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorCollection.class)))
            }
    )
    public Response updateUser(
            @NotNull @QueryParam("userName") final String userName,
            @NotNull final UserBean userBean);

    /**
     * Update the user password.
     *
     * @param userName the user name
     * @param password the new password
     * @return the response
     */
    @PUT
    @Path(ConfAPI.USER_PASSWORD)
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            tags = { ConfAPI.USERS },
            summary = "Updates the user password",
            description = "Upon successful request, returns the updated `UserBean` object.",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserBean.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorCollection.class)))
            }
    )
    public Response setUserPassword(
            @NotNull @QueryParam("userName") final String userName,
            @NotNull final String password);

}
