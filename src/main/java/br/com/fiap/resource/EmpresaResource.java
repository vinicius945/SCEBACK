package br.com.fiap.resource;

import br.com.fiap.bo.EmpresaBO;
import br.com.fiap.to.EmpresaTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;


@Path("/sce/empresa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class EmpresaResource {
    private EmpresaBO empresaBO;

    public EmpresaResource() {
        empresaBO = new EmpresaBO();
    }


    @GET
    @Path("/listar")
    public Response listarEmpresas() {
        ArrayList<EmpresaTO> empresas = empresaBO.listarEmpresas();
        if (empresas.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Nenhuma empresa encontrada").build();
        }
        return Response.ok(empresas).build();
    }


    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") long idEmpresa) {
        EmpresaTO empresa = empresaBO.buscarPorId(idEmpresa);  // Chama o BO para buscar a empresa
        if (empresa == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Empresa não encontrada").build();
        }
        return Response.ok(empresa).build();
    }


    @POST
    @Path("/salvar")
    public Response salvarEmpresa(EmpresaTO empresa) {
        EmpresaTO empresaSalva = empresaBO.salvarEmpresa(empresa);
        if (empresaSalva != null) {
            return Response.status(Response.Status.CREATED).entity(empresaSalva).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao salvar empresa").build();
    }


    @PUT
    @Path("/atualizar")
    public Response atualizarEmpresa(EmpresaTO empresa) {
        EmpresaTO empresaAtualizada = empresaBO.atualizarEmpresa(empresa);
        if (empresaAtualizada != null) {
            return Response.ok(empresaAtualizada).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao atualizar empresa").build();  // Retorna erro se não atualizar
    }


    @DELETE
    @Path("/excluir/{id}")
    public Response excluirEmpresa(@PathParam("id") long idEmpresa) {
        boolean sucesso = empresaBO.excluirEmpresa(idEmpresa);
        if (sucesso) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao excluir empresa").build();  // Retorna erro se não excluir
    }
}
