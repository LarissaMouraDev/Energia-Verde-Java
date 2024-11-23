package org.example.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.model.Residuos;
import org.example.service.ResiduosService;

import java.util.List;

@Path("/residuos")
public class ResiduosResource {
    private ResiduosService service;

    public ResiduosResource() {
        this.service = new ResiduosService();
    }

    @Path("cadastrar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Residuos residuos) {
        try {
            service.cadastrar(residuos);
            return Response.status(201).build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Path("listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        try {
            List<Residuos> residuos = service.listar();
            return Response.ok(residuos).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("buscarPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            Residuos residuos = service.buscarPorId(id);
            return Response.ok(residuos).build();
        } catch (Exception e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("atualizar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") int id, Residuos residuo) {
        try {
            residuo.setId(id);
            service.atualizar(residuo);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("excluir/{id}")
    public Response excluir(@PathParam("id") int id) {
        try {
            service.excluir(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
}
