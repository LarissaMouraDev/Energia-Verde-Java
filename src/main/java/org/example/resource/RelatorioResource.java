package org.example.resource;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.model.Relatorio;
import org.example.service.RelatorioService;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

@Path("/relatorios")
public class RelatorioResource {
    private RelatorioService service;

    public RelatorioResource() {
        this.service = new RelatorioService();
    }

    @POST
    @Path("cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Relatorio relatorio) {
        try {
            service.cadastrar(relatorio);
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
            List<Relatorio> relatorios = service.listar();
            return Response.ok(relatorios).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("buscarPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            Relatorio relatorio = service.buscarPorId(id);
            return Response.ok(relatorio).build();
        } catch (Exception e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("buscarPorPeriodo/periodo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorPeriodo(
            @QueryParam("dataInicio") String dataInicio,
            @QueryParam("dataFim") String dataFim) {
        try {
            List<Relatorio> relatorios = service.buscarPorPeriodo(dataInicio, dataFim);
            return Response.ok(relatorios).build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("atualizar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") int id, Relatorio relatorio) {
        try {
            relatorio.setId(id);
            service.atualizar(relatorio);
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