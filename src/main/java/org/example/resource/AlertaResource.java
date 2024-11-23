package org.example.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.model.Alerta;
import org.example.service.AlertaService;

import java.util.List;

@Path("/alertas")
public class AlertaResource {
    private AlertaService service;

    public AlertaResource() {
        this.service = new AlertaService();
    }

    @POST
    @Path("cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Alerta alerta) {
        try {
            service.cadastrar(alerta);
            return Response.status(201).build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        try {
            List<Alerta> alertas = service.listar();
            return Response.ok(alertas).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("atualizar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") int id, Alerta alerta) {
        try {
            alerta.setId(id);
            service.atualizar(alerta);
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
