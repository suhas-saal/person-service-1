package com.suhas.controller;

import com.suhas.dto.PersonRequest;
import com.suhas.model.Person;
import com.suhas.service.IPersonService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "/person", description = "REST Controller for handling all CRUD operations for Person entity")
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @ApiOperation(value = "Get All Persons", notes = "API to retrieve all persons", nickname = "getPersons")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "No Content"),
            @ApiResponse(code = 405, message = "Method not allowed"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 200, message = "Success", response = List.class, responseContainer = "List") })
    @GetMapping("/")
    public List<Person> getAllPersons() {
        return personService.getAll();
    }


    @ApiOperation(value = "create", notes = "Create Person", nickname = "createPerson")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "No Content"),
            @ApiResponse(code = 405, message = "Method not allowed"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 200, message = "Success", response = Person.class)})
    @PostMapping("/")
    public Person createPerson(@RequestBody PersonRequest request) {
        return personService.create(request);
    }

    @ApiOperation(value = "Update Person By Id", notes = "API to update a Person", nickname = "updatePerson")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Document not found"),
            @ApiResponse(code = 200, message = "Success",
                    response = List.class, responseContainer = "List") })
    @PutMapping("/{personId}")
    public Person updateDocument(@ApiParam(value = "personId", required = true) @PathVariable(value = "personId") Long personId,
                                   @Valid @RequestBody PersonRequest request) {
        return personService.update(personId, request);
    }

    @ApiOperation(value = "delete", notes = "Delete Person By id", nickname = "deletePerson")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "No Content"),
            @ApiResponse(code = 405, message = "Method not allowed"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 200, message = "Success", response = String.class)})
    @DeleteMapping("/{personId}")
    public String deletePerson(@PathVariable("personId") String personId) {
        personService.delete(Long.parseLong(personId));
        return "success";
    }

    @ApiOperation(value = "Check if person already exists", notes = "API to check if a person already exists", nickname = "checkIfPersonExists")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "No Content"),
            @ApiResponse(code = 405, message = "Method not allowed"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 200, message = "Success")})
    @RequestMapping(method = RequestMethod.HEAD, value = "/{personId}/exists", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> checkIfPersonExists(@ApiParam(value = "personId", required = true) @PathVariable long personId) {
        try {
            personService.checkIfPersonExists(personId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

}