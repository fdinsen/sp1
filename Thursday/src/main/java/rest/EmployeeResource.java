/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Employee;
import facades.EmployeeFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author gamma
 */
@Path("employee")
public class EmployeeResource {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    EmployeeFacade facade =  EmployeeFacade.getEmployeeFacade(emf);
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmployeeResource
     */
    public EmployeeResource() {
    }
    
        
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }
    
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        return Response.ok().entity(gson.toJson(facade.getAllEmployees())).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id") int id) {
        return Response.ok().entity(gson.toJson(facade.getEmployeeById(id))).build();
    }
    
    @GET
    @Path("/highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHighestPaid() {
        return Response.ok().entity(gson.toJson(facade.getEmployeesWithHighestSalary())).build();
    }
    
    @GET
    @Path("/name")
    public Response getEmployeeByName(@DefaultValue("Guest") @QueryParam("name") String name) {
        return Response.ok().entity(gson.toJson(facade.getEmployeesByName(name))).build();
    }
    
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Employee entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
