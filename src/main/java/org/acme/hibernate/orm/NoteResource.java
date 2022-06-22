package org.acme.hibernate.orm;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@Path("notes")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class NoteResource {

    private static final Logger LOGGER = Logger.getLogger(NoteResource.class.getName());

    @Inject
    EntityManager entityManager;

    @GET
    public List<Note> get() {
        return entityManager.createNamedQuery("Note.findAll", Note.class)
                .getResultList();
    }

    @GET
    @Path("{id}")
    public Note getSingle(Integer id) {
        Note entity = entityManager.find(Note.class, id);
        if (entity == null) {
            throw new WebApplicationException("Note with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Note note) {
        if (note.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        entityManager.persist(note);
        return Response.ok(note).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Note update(Integer id, Note note) {
        if (note.getText() == null) {
            throw new WebApplicationException("Note Name was not set on request.", 422);
        }

        Note entity = entityManager.find(Note.class, id);

        if (entity == null) {
            throw new WebApplicationException("Note with id of " + id + " does not exist.", 404);
        }

        entity.setText(note.getText());

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(Integer id) {
        Note entity = entityManager.getReference(Note.class, id);
        if (entity == null) {
            throw new WebApplicationException("Note with id of " + id + " does not exist.", 404);
        }
        entityManager.remove(entity);
        return Response.status(204).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", exception.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", exception.getMessage());
            }

            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
        }

    }
}