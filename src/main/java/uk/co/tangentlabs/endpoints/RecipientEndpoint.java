// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) space 
// Source File Name:   MemberEndpoint.java

package uk.co.tangentlabs.endpoints;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import com.json.JSONArray;
import com.json.JSONObject;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.co.tangentlabs.crm.actors.Importer;
import uk.co.tangentlabs.entities.Contact;
import uk.co.tangentlabs.entities.ContactList;
import uk.co.tangentlabs.services.IContactService;

// Referenced classes of package uk.co.tangentlabs.endpoints:
//            Endpoint

@Component
@Path("/recipient/")
public class RecipientEndpoint extends Endpoint
{
	@Autowired
    private SessionFactory sessionFactory;
    
	@Autowired
    IContactService contactService;

	
	@GET 
	@Path("/import")
	public String runImport(@Context UriInfo uriInfo){
		int count = 10000;
		boolean supportsbatchinserts_updates = false;
		try {
			Class.forName("org.postgresql.Driver");
			java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost/crm","postgres","postgres");
			supportsbatchinserts_updates = con.getMetaData().supportsBatchUpdates();
			
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String counter = uriInfo.getQueryParameters().getFirst("count");
		if (counter != null){
			count = Integer.parseInt(counter);
		}
		Importer.run(sessionFactory, count);
		return "Done"+supportsbatchinserts_updates;
	}
	
	@GET 
	public String listRecipients(@Context UriInfo uriInfo)
	{
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Contact.class);
        List<Contact> results = c.list();
        JSONArray result = new JSONArray(results);
		return result.toString();
	}
	
	@GET 
	@Path("/{recipient_id}")
	public String getRecipient(@Context UriInfo uriInfo, @PathParam("recipient_id") int recipientId){
		
		return null;
	}
	
	@GET
	@Path("/list/")
	public String listLists(@Context UriInfo uriInfo){
		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
		try {
			Criteria c = sessionFactory.getCurrentSession().createCriteria(ContactList.class);
			List results = c.list();
			
			JSONArray result = new JSONArray(results);
			return result.toString();
		} finally {
			tx.commit();
		}
	}
	
	@GET
	@Path("/list/{list_id}")
	public String getList(@Context UriInfo uriInfo, @PathParam("list_id") int listId){
		return null;
	}
	
	@POST
	@Path("/list/")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
	public Response createList(){
		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
		try {
			ContactList contact = new ContactList();
			sessionFactory.getCurrentSession().persist(contact);
			return Response.ok().build();
		} finally {
			tx.commit();
		}
	}
	
	@POST
	@Path("/list/{list_id}/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		
		return Response.ok().build();
	}
	
//    @POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    
//    public String updatePollingStation(@FormParam("name") String name, @FormParam("uuid") String uuid)
//    {
//        Member member = new Member();
//        member.setName(name);
//        member.setUuid(uuid);
//        member.setVoted(false);
//        Transaction t = sessionFactory.getCurrentSession().getTransaction();
//        try {
//        	if (t.isActive()) t.rollback();
//        } catch (org.hibernate.TransactionException te){
//        	t = sessionFactory.getCurrentSession().beginTransaction();
//        }
//        t.begin();
//        java.io.Serializable id = (Integer)sessionFactory.getCurrentSession().save(member);
//        sessionFactory.getCurrentSession().flush();
//        Member s = (Member)sessionFactory.getCurrentSession().get(uk.co.tangentlabs.entities.Member.class, id);
//        t.commit();
//        JSONObject object = new JSONObject(s);
//        return object.toString();
//    }
//
//    @GET
//    public String listPollingStations(@Context UriInfo uriInfo)
//    {
//        Transaction t = sessionFactory.getCurrentSession().getTransaction();
//        try {
//        	if (t.isActive()) t.rollback();
//        } catch (org.hibernate.TransactionException te){
//        	t = sessionFactory.getCurrentSession().beginTransaction();
//        }
//        t.begin();
//        Criteria c = sessionFactory.getCurrentSession().createCriteria(uk.co.tangentlabs.entities.Member.class);
//        java.util.List results = c.list();
//        t.commit();
//        JSONArray array = new JSONArray(results);
//        return array.toString();
//    }


}
