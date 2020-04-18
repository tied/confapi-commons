package de.aservo.atlassian.confapi.rest.api;

import de.aservo.atlassian.confapi.constants.ConfAPI;
import de.aservo.atlassian.confapi.model.ErrorCollection;
import de.aservo.atlassian.confapi.model.LicensesBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The License resource interface.
 */
@Path(ConfAPI.LICENSE)
@Produces(MediaType.APPLICATION_JSON)
public interface LicenseResource {

    /**
     * Returns all licenses.
     *
     * @return the licenses with entity type {@link de.aservo.atlassian.confapi.model.LicensesBean}.
     */
    @GET
    @Operation(
            tags = { ConfAPI.LICENSES },
            summary = "Get all licenses information",
            description = "Upon successful request, returns a `LicensesBean` object containing license details",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = LicensesBean.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorCollection.class)))
            }
    )
    Response getLicenses();

    /**
     * Sets license.
     *
     * @param clear      true, if licenses shall be cleared before setting the new license
     * @param licenseKey the license key to set
     * @return the added license of type {@link de.aservo.atlassian.confapi.model.LicenseBean}.
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Operation(
            tags = { ConfAPI.LICENSES },
            summary = "Set a new license",
            description = "Existing license details are overwritten. Upon successful request, returns a `LicensesBean` object containing license details",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = LicensesBean.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorCollection.class)))
            }
    )
    Response setLicense(
            @Parameter(description="Clears license details before updating (Jira only).") final boolean clear,
            @NotNull final String licenseKey);

}
