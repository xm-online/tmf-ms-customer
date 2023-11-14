package com.icthh.xm.tmf.ms.customer.api.v31;

import com.icthh.xm.tmf.ms.customer.model.v31.Customer;
import com.icthh.xm.tmf.ms.customer.model.v31.Error;
import java.util.List;
import com.icthh.xm.tmf.ms.customer.model.v31.PatchOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link CustomerV31ApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.814388773+02:00[Europe/Kyiv]")
public interface CustomerV31ApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * PATCH /customer/{id} : Updates partially a &#39;Customer&#39;
     *
     * @param id Identifier of the Customer (required)
     * @param operations A list of Customer patch operations (required)
     * @return Updated (status code 200)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     *         or Method Not allowed (status code 405)
     *         or Conflict (status code 409)
     *         or Internal Server Error (status code 500)
     * @see CustomerV31Api#patchCustomer
     */
    default ResponseEntity<Customer> patchCustomer(String id,
        List<PatchOperation> operations) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"contactMedium\" : [ { \"validFor\" : { \"startDateTime\" : \"2000-01-23T04:56:07.000+00:00\", \"endDateTime\" : \"2000-01-23T04:56:07.000+00:00\" }, \"schemaLocation\" : \"schemaLocation\", \"type\" : \"type\", \"preferred\" : true, \"characteristic\" : { \"country\" : \"country\", \"emailAddress\" : \"emailAddress\", \"phoneNumber\" : \"phoneNumber\", \"stateOrProvince\" : \"stateOrProvince\", \"city\" : \"city\", \"faxNumber\" : \"faxNumber\", \"postCode\" : \"postCode\", \"street1\" : \"street1\", \"street2\" : \"street2\", \"type\" : \"type\" }, \"baseType\" : \"baseType\" }, { \"validFor\" : { \"startDateTime\" : \"2000-01-23T04:56:07.000+00:00\", \"endDateTime\" : \"2000-01-23T04:56:07.000+00:00\" }, \"schemaLocation\" : \"schemaLocation\", \"type\" : \"type\", \"preferred\" : true, \"characteristic\" : { \"country\" : \"country\", \"emailAddress\" : \"emailAddress\", \"phoneNumber\" : \"phoneNumber\", \"stateOrProvince\" : \"stateOrProvince\", \"city\" : \"city\", \"faxNumber\" : \"faxNumber\", \"postCode\" : \"postCode\", \"street1\" : \"street1\", \"street2\" : \"street2\", \"type\" : \"type\" }, \"baseType\" : \"baseType\" } ], \"creditProfile\" : [ { \"creditProfileDate\" : \"2000-01-23T04:56:07.000+00:00\", \"creditScore\" : 6, \"validFor\" : { \"startDateTime\" : \"2000-01-23T04:56:07.000+00:00\", \"endDateTime\" : \"2000-01-23T04:56:07.000+00:00\" }, \"schemaLocation\" : \"schemaLocation\", \"creditRiskRating\" : 0, \"type\" : \"type\", \"baseType\" : \"baseType\" }, { \"creditProfileDate\" : \"2000-01-23T04:56:07.000+00:00\", \"creditScore\" : 6, \"validFor\" : { \"startDateTime\" : \"2000-01-23T04:56:07.000+00:00\", \"endDateTime\" : \"2000-01-23T04:56:07.000+00:00\" }, \"schemaLocation\" : \"schemaLocation\", \"creditRiskRating\" : 0, \"type\" : \"type\", \"baseType\" : \"baseType\" } ], \"agreement\" : [ { \"referredType\" : \"referredType\", \"name\" : \"name\", \"href\" : \"href\", \"id\" : \"id\" }, { \"referredType\" : \"referredType\", \"name\" : \"name\", \"href\" : \"href\", \"id\" : \"id\" } ], \"validFor\" : { \"startDateTime\" : \"2000-01-23T04:56:07.000+00:00\", \"endDateTime\" : \"2000-01-23T04:56:07.000+00:00\" }, \"schemaLocation\" : \"schemaLocation\", \"type\" : \"type\", \"relatedParty\" : [ { \"referredType\" : \"referredType\", \"role\" : \"role\", \"name\" : \"name\", \"href\" : \"href\", \"id\" : \"id\" }, { \"referredType\" : \"referredType\", \"role\" : \"role\", \"name\" : \"name\", \"href\" : \"href\", \"id\" : \"id\" } ], \"characteristic\" : [ { \"name\" : \"name\", \"value\" : \"value\" }, { \"name\" : \"name\", \"value\" : \"value\" } ], \"baseType\" : \"baseType\", \"statusReason\" : \"statusReason\", \"name\" : \"name\", \"paymentMethod\" : [ { \"referredType\" : \"referredType\", \"name\" : \"name\", \"href\" : \"href\", \"id\" : \"id\" }, { \"referredType\" : \"referredType\", \"name\" : \"name\", \"href\" : \"href\", \"id\" : \"id\" } ], \"href\" : \"href\", \"id\" : \"id\", \"engagedParty\" : { \"referredType\" : \"referredType\", \"name\" : \"name\", \"description\" : \"description\", \"href\" : \"href\", \"id\" : \"id\" }, \"account\" : [ { \"referredType\" : \"referredType\", \"name\" : \"name\", \"description\" : \"description\", \"href\" : \"href\", \"id\" : \"id\" }, { \"referredType\" : \"referredType\", \"name\" : \"name\", \"description\" : \"description\", \"href\" : \"href\", \"id\" : \"id\" } ], \"status\" : \"status\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
