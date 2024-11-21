package br.com.fiap.resource;

import br.com.fiap.bo.AvaliacaoBO;
import br.com.fiap.to.AvaliacaoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;


@Path("/avaliacoes")
public class AvalicaoResource {
    private AvaliacaoBO avaliacaoBO = new AvaliacaoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<AvaliacaoTO> resultado = avaliacaoBO.findAll();

        if (resultado != null && !resultado.isEmpty()) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma avaliação encontrada.")
                    .build();
        }
    }

    @GET
    @Path("/{idAvaliacao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("idAvaliacao") Long idAvaliacao) {
        AvaliacaoTO resultado = avaliacaoBO.findById(idAvaliacao);

        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Avaliação com ID " + idAvaliacao + " não encontrada.")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid AvaliacaoTO avaliacao) {
        AvaliacaoTO resultado = avaliacaoBO.save(avaliacao);

        if (resultado != null) {
            return Response.status(Response.Status.CREATED).entity(resultado).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao criar a avaliação. Verifique os dados enviados.")
                    .build();
        }
    }

    @PUT
    @Path("/{idAvaliacao}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid AvaliacaoTO avaliacao, @PathParam("idAvaliacao") Long idAvaliacao) {
        avaliacao.setIdAvaliacao(idAvaliacao);

        AvaliacaoTO resultado = null;
        try {
            resultado = avaliacaoBO.update(avaliacao);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao atualizar a avaliação: " + e.getMessage())
                    .build();
        }

        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao atualizar a avaliação. Verifique os dados enviados.")
                    .build();
        }
    }

    @DELETE
    @Path("/{idAvaliacao}")
    public Response delete(@PathParam("idAvaliacao") Long idAvaliacao) {
        if (avaliacaoBO.delete(idAvaliacao)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Avaliação com ID " + idAvaliacao + " não encontrada.")
                    .build();
        }
    }

}
